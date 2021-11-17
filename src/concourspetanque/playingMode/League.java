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
            Player newPlayer = generatePlayer();
            players.add(newPlayer);
        }
        return players;
    }
    private static Player generatePlayer(){
        Player p = new Player(NameGenerator.GenerateName(), NameGenerator.GenerateName(), Tools.GenerateNumberBetween(18, 99));
        return p;
    }


    // Utility methods
    private void printRegisteredPlayers(List<Player> players) {
        for(Player player : players) {
            System.out.println(player);
        }
    }
}
