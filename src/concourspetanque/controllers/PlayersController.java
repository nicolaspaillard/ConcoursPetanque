package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.controllers.tools.RandomGenerators;
import concourspetanque.controllers.tools.Utils;
import concourspetanque.models.Player;

public class PlayersController {
    private List<Player> players;
    private int playersCount;  


    public PlayersController() {
        this.players = generatePlayers();
        this.playersCount = this.players.size();
    }
    
    public List<Player> getPlayers() {
        return players;
    }
    // public void setPlayers(List<Player> players) {
    //     this.players = players;
    // }

    public int getPlayersCount() {
        return playersCount;
    }
    // public void setPlayersCount(int playersCount) {
    //     this.playersCount = playersCount;
    // }

    public void printPlayers() {
        Utils.printLine(40);
        System.out.println("INSCRIPTION DES JOUEURS");

        // En-têtes du tableau
        Utils.printLine(40);
        System.out.printf("%-4s", "Id");
        System.out.printf("%-15s", "Prénom");
        System.out.printf("%-15s", "Nom");
        System.out.printf("%-6s", "Age");
        Utils.printLine(40);
        // Boucle sur les players
        for(Player player : this.players) {
            System.out.printf("%-4s", player.getId());
            System.out.printf("%-15s", player.getFirstName());
            System.out.printf("%-15s", player.getLastName());
            System.out.printf("%-6s", player.getAge());
            System.out.println("");
        }
        System.out.println("\nNombre de joueurs inscrits : " + players.size());
    }    

    private List<Player> generatePlayers() {
        // Generate a random number of players
        int numberOfPlayers = RandomGenerators.generateNumberBetween(12,36);
        List<Player> players = new ArrayList<>();
        for (int i = 0 ; i < numberOfPlayers ; i++) {
            Player newPlayer = new Player(RandomGenerators.generateName(), RandomGenerators.generateName(), RandomGenerators.generateNumberBetween(18, 99), i);
            players.add(newPlayer);
        }
        return players;
    }
}
