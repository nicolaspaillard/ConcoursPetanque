package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.models.Player;
import concourspetanque.models.Team;
import concourspetanque.tools.RandomGenerators;

public class TeamsController {
    private List<Team> teams;
    
    public TeamsController(List<Player> players) {
        this.teams = generateTeams(players);
    }

    public List<Team> getTeams() {
        return teams;
    }
    // public void setTeams(List<Team> teams) {
    //     this.teams = teams;
    // }
    
    public void printTeams() {
        for(Team team : this.teams) {
            System.out.println(team);
        }
    }

    private List<Team> generateTeams(List<Player> players){
        List<Team> teams = new ArrayList<>();
        if(players.size()<16){
            //12 - 15 joueurs -> 6 teams
            teams = addPlayers(players, 6);
        }else if(players.size()<20){
            //16 - 19 joueurs -> 8 teams
            teams = addPlayers(players, 8);
        }else if(players.size()<24){
            //20 - 23 joueurs -> 10 teams
            teams = addPlayers(players, 10);
        }else {
            //24 joueurs ou plus -> 12 teams
            teams = addPlayers(players, 12);                
        }
        return teams;       
    }

    private List<Team> addPlayers(List<Player> players, int teamsCount){
        List<Team> teams = new ArrayList<>();
        // Constitue les équipes avec 2 joueurs aléatoires
        for (int i = 0; i < teamsCount; i++) {
            List<Player> selectedPlayers = selectPlayers(players);
            selectedPlayers.forEach(p -> players.remove(p)); // Retire les deux joueurs sélectionnés de la liste
            teams.add(new Team(selectedPlayers, i));
        }
        // S'il reste des joueurs, les ajoute a des équipes aléatoires
        if (players.size()>0) {
            teams = addRemainingPlayers(players, teams);
        }
        return teams;
    }

    private List<Player> selectPlayers(List<Player> players) {
        List<Player> selectedPlayers = new ArrayList<>();
        // Sélectionne 2 joueurs aléatoires et les ajoute à l'équipe
        for (int i = 0; i < 2; i++) {
            Player p = players.get(RandomGenerators.generateNumberBetween(0, players.size()));
            selectedPlayers.add(p);
            players.remove(p); // retire le joueur sélectionné de la liste locale
        }
        return selectedPlayers;
    }

    private List<Team> addRemainingPlayers(List<Player> remainingPlayers, List<Team> teams){
        // Tant qu'il reste des joueurs, boucler pour les caser dans des équipes aléatoires de 2 joueurs
        while (remainingPlayers.size() > 0) {
            int randomTeam = RandomGenerators.generateNumberBetween(0, teams.size());
            int teamSize = teams.get(randomTeam).getPlayers().size();
            if (teamSize > 2) {
                continue;
            }
            teams.get(randomTeam).addPlayer(remainingPlayers.get(0));
            remainingPlayers.remove(remainingPlayers.get(0));
        }
        return teams;
    }
}
