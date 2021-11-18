package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.Tools.RandomGenerators;
import concourspetanque.models.Player;

public class PlayersController {
    List<Player> players;

    public PlayersController() {
        this.players = generatePlayers();
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
    private void printPlayers(List<Player> players) {
        for(Player player : players) {
            System.out.println(player);
        }
    }
}
