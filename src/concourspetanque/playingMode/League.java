package concourspetanque.playingMode;

import concourspetanque.Player;
import concourspetanque.Tools.NameGenerator;
import concourspetanque.Tools.Tools;

import java.util.ArrayList;
import java.util.List;

public class League {
    /**
     * Main method for executing different steps of the program
     */
    public void start() {
        List<Player> players = playersRegister();
        printRegisteredPlayers(players);

    }

    // Main steps

    private static List<Player> playersRegister() {
        // Generate a random number of players
        int numberOfPlayers = Tools.random_int(12,36);
        List<Player> players = new ArrayList<>();
        for (int i = 0 ; i < numberOfPlayers ; i++) {
            // Generate random firstName
            String playerFirstName = NameGenerator.generateName();
            // Generate standard lastName
            String playerLastName = "Nom" + i;
            // Generate random age : Players are randomly between 14 & 80 by default
            int playerAge = Tools.random_int(14, 80);
            // Create player and add to the list
            Player newPlayer = new Player(playerFirstName, playerLastName, playerAge);
            players.add(newPlayer);
        }
        return players;
    }

    // Utility methods
    private void printRegisteredPlayers(List<Player> players) {
        for(Player player : players) {
            System.out.println(player);
        }
    }
}
