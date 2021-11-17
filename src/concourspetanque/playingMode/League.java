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
        int numberOfPlayers = Tools.GenerateNumberBetween(12,36);
        List<Player> players = new ArrayList<>();
        for (int i = 0 ; i < numberOfPlayers ; i++) {
            // Generate random firstName
            String playerFirstName = NameGenerator.GenerateName();
            // Generate standard lastName
            String playerLastName = "Nom" + i;
            // Generate random age : Players are randomly between 14 & 80 by default
            int playerAge = Tools.GenerateNumberBetween(14, 80);
            // Create player and add to the list
            Player newPlayer = GeneratePlayer();
            players.add(newPlayer);
        }
        return players;
    }
    public static Player GeneratePlayer(){
        Player p = new Player(NameGenerator.GenerateName(), NameGenerator.GenerateName(), GenerateNumberBetween(18, 99));
        return p;
    }


    // Utility methods
    private void printRegisteredPlayers(List<Player> players) {
        for(Player player : players) {
            System.out.println(player);
        }
    }
}
