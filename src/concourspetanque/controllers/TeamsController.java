package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import concourspetanque.models.Match;
import concourspetanque.models.Player;
import concourspetanque.models.Team;
import concourspetanque.utils.GoalAverageComparator;
import concourspetanque.utils.RandomGenerators;

public class TeamsController {
    private List<Team> teams;   

    public TeamsController() {
        this.teams = new ArrayList<>();
    }

    /** 
     * @return List<Team> : Returns the list of all teams in that instance
     */
    public List<Team> getTeams() {
        return this.teams;
    }

    public Team getTeam(int teamID) {
        return this.teams.get(teamID);
    }

    /**
     * Construit les équipes à partir de la liste de joueurs et du nombre d'équipes possibles
     * 
     * @param players List of Players
     * @param teamsCount Number of teams to build
     */
    public void buildTeams(List<Player> players, List<Integer> allowedNumberOfTeams) {
        // Récupère le nombre d'équipes
        int teamsCount = computeTeamsCount(players.size(), allowedNumberOfTeams);
        // Pour chaque équipe, sélectionne 2 joueurs aléatoires
        for (int i = 0; i < teamsCount; i++) {
            List<Player> selectedPlayers = select2RandomPlayers(players);
            // Retire les deux joueurs sélectionnés de la liste et crée une nouvelle équipe
            selectedPlayers.forEach(p -> players.remove(p));
            teams.add(new Team(selectedPlayers, i));
        }
        // S'il reste des joueurs dans la liste, les ajoute a des équipes aléatoires
        if (players.size()>0) {
            addRemainingPlayers(players);
        }
    }

    /**
     * Méthode pour calculer le nombre d'équipes en fonction du mode de jeu.
     * Tant qu'il est possible de consituer des équipes de 2, incrémente le compteur. 
     * A chaque itération, si le nombre d'équipes au compteur est autorisé pour ce mode de jeu,
     * met à jour le nombre final d'équipes.
     * 
     * @param playersCount Number of generated players
     * @param allowedNumberOfTeams List with the possible numbers of teams for the selected game mode
     * @return
     */
    public int computeTeamsCount(int playersCount, List<Integer> allowedNumberOfTeams) {
        int finalTeamsCount = 0;
        // starts looping with minimum 2 team
        int teamsCounter = 2;
        while (playersCount / 2 > teamsCounter) {
            teamsCounter++;
            if (allowedNumberOfTeams.contains(teamsCounter)) {
                finalTeamsCount = teamsCounter;
            }
        }
        return finalTeamsCount;
    }

    /**
     * Sélectionne deux joueurs aléatoires dans une liste de joueurs.
     * 
     * @param players List of players
     * @return
     */
    private List<Player> select2RandomPlayers(List<Player> players) {
        List<Player> selectedPlayers = new ArrayList<>();
        // Sélectionne 2 joueurs aléatoires et les ajoute à l'équipe
        for (int i = 0; i < 2; i++) {
            Player p = players.get(RandomGenerators.generateNumberBetween(0, players.size()));
            selectedPlayers.add(p);
            // retire le joueur sélectionné de la liste locale pour qu'il ne 
            // puisse pas être sélectionné une deuxième fois
            players.remove(p); 
        }
        return selectedPlayers;
    }
    
    /** 
     * Tant qu'il reste des joueurs dans la liste passée en paramètre,
     * intègre un joueur dans une team aléatoire de deux joueurs et le retire de la liste.
     * 
     * @param remainingPlayers : The list of players that have not been selected 
     */
    private void addRemainingPlayers(List<Player> remainingPlayers){
        while (remainingPlayers.size() > 0) {
            // Selectionne une Team au hasard
            int randomTeam = RandomGenerators.generateNumberBetween(0, teams.size());
            int teamSize = teams.get(randomTeam).getPlayers().size();
            if (teamSize > 2) { // Vérifie si celle-ci contient 2 joueurs, sinon recommence
                continue;
            }
            // Ajoute le 1er joueur de la liste dans l'équipe sélectionnée
            teams.get(randomTeam).addPlayer(remainingPlayers.get(0));
            remainingPlayers.remove(remainingPlayers.get(0));
        }
    }

    /**
     * Ajoute le match à la liste des matchs joués pour chaque équipe
     * @param match
     */
    public void updateTeamsMatches(Match match) {
        int teamOneId = match.getOpponent1().getId();
        int teamTwoId = match.getOpponent2().getId();
        this.teams.get(teamOneId).addMatch(match);
        this.teams.get(teamTwoId).addMatch(match);
    }

    /**
     * Ajoute les points (positifs et négatifs) à chaque équipe du match
     * @param match
     */
    public void updateTeamsScores(Match match) {
        int teamOneId = match.getOpponent1().getId();
        int teamTwoId = match.getOpponent2().getId();
        this.teams.get(teamOneId).addPoints(match.getOpponent1score());
        this.teams.get(teamOneId).removePoints(match.getOpponent2score());
        this.teams.get(teamTwoId).addPoints(match.getOpponent2score());
        this.teams.get(teamTwoId).removePoints(match.getOpponent1score());
    }

    /**
     * Ajoute une victoire à l'équipe gagnante du match
     * @param match
     */
    public void updateTeamsVictories(Match match) {
        int winnerId = match.getWinner().getId();
        this.teams.get(winnerId).addVictory();
    }

    /**
     * Pour chaque équipe calcule la différence de points (points totaux finaux)
     */
    public void updateTeamsGoalAverage() {
        for (Team team : this.teams) {
            team.setGoalAverage(team.getPositivePoints() + team.getNegativePoints());
        }
    }

    public void updateTeamsRanking() {
        List<Team> finalTeamsSorted = new ArrayList<>();
        // Boucle sur le score des équipes de 4 à 0
        for (int i = 4; i >= 0 ; i--) {
            List<Team> teamsByVictories = teamVictoriesMatch(i);
            finalTeamsSorted.addAll(teamsByVictories);
        }
        setTeamsRank(finalTeamsSorted);
    }

    private List<Team> teamVictoriesMatch(int i) {
        List<Team> teamsByVictories = new ArrayList<>();
        for (Team team : this.teams) {
            if (team.getVictories() == i) {
                teamsByVictories.add(team);
                Collections.sort(teamsByVictories, new GoalAverageComparator());
            }
        }
        return teamsByVictories;
    }

    private void setTeamsRank(List<Team> teams) {
        for (int i = 0 ; i < teams.size() ; i++) {
            teams.get(i).setRanking(i);
        }
    }
}
