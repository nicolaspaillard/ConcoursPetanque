package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.controllers.tools.RandomGenerators;
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
