package concourspetanque.playingMode;

import concourspetanque.controllers.PlayersController;
import concourspetanque.controllers.TeamsController;

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
        teamsController.getTeams().forEach(System.out::println);
    }
}