package concourspetanque.playingMode;

import concourspetanque.Tools.RandomGenerators;
import concourspetanque.controllers.PlayersController;
import concourspetanque.controllers.TeamsController;
import concourspetanque.models.Player;
import concourspetanque.models.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class League {
    /**
     * Main method for executing different steps of the program
     */
    public void start() {
        PlayersController playersController = new PlayersController();
        playersController.printPlayers();        
        System.out.println("\nNombre de joueurs inscrits : " + playersController.getPlayersCount());

        TeamsController teamsController = new TeamsController(playersController.getPlayers());
        teamsController.printTeams();

        
        teams.forEach(System.out::println);
    }
    //#region TOOLS

    //#endregion
    //#region PLAYERS
    
    //#endregion
    //#region TEAMS
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
    public List<Team> addPlayers(List<Player> players, int teamsCount){
        List<Team> teams = new ArrayList<>();
        // Constitue les équipes avec 2 joueurs aléatoires
        for (int i = 0; i < teamsCount; i++) {
            List<Player> team = selectPlayers(players);
            team.forEach(p -> players.remove(p)); // Retire les deux joueurs sélectionnés de la liste
            teams.add(new Team(team, i));
        }
        // S'il reste des joueurs, les ajoute a des équipes aléatoires
        if (players.size()>0) {
            teams = addRemainingPlayers(players, teams);
        }
        return teams;
    }

    private List<Player> selectPlayers(List<Player> players) {
        List<Player> team = new ArrayList<>();
        // Sélectionne 2 joueurs aléatoires et les ajoute à l'équipe
        for (int i = 0; i < 2; i++) {
            Player p = players.get(GenerateNumberBetween(0, players.size()));
            team.add(p);
            players.remove(p); // retire le joueur sélectionné de la liste locale
        }
        return team;
    }

    public List<Team> addRemainingPlayers(List<Player> remainingPlayers, List<Team> teams){
        // Tant qu'il reste des joueurs, boucler pour les caser dans des équipes aléatoires de 2 joueurs
        while (remainingPlayers.size() > 0) {
            int randomTeam = GenerateNumberBetween(0, teams.size());
            int teamSize = teams.get(randomTeam).getPlayers().size();
            if (teamSize > 2) {
                continue;
            }
            teams.get(randomTeam).addPlayer(remainingPlayers.get(0));
            remainingPlayers.remove(remainingPlayers.get(0));
        }
//        int teamNumber = GenerateNumberBetween(0, teams.size());//récupère une 1ere team au hasard
//        List<Integer> prevTeamsNumber = new ArrayList<>();//voir plus bas
//        int playersCount = remainingPlayers.size();
//        for (int i = 0; i < playersCount; i++) {
//            while (prevTeamsNumber.contains(teamNumber)) {//si l'équipe a deja reçu un joueur supplémentaire
//                teamNumber = GenerateNumberBetween(0, teams.size());//récupère une team au hasard
//            }
//            Player p = remainingPlayers.get(0);//récupère le 1er joueur de la liste
//            remainingPlayers.remove(p);//retire le joueur de la liste
//            teams.get(teamNumber).addPlayer(p);//ajoute le joueur à l'équipe
//            prevTeamsNumber.add(teamNumber);//ajoute l'équipe aux équipes déjà selectionnées
//        }
        return teams;
    }
    //#endregion
}
