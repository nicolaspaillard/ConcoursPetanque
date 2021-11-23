package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.controllers.tools.RandomGenerators;
import concourspetanque.models.GameMode;
import concourspetanque.models.Player;

public class PlayersController {
    private List<Player> players = new ArrayList<Player>();

    public PlayersController(GameMode gameMode) {
        generatePlayers(gameMode);
    }
    
    
    /** 
     * @return List<Player> : A list containing all the players
     */
    public List<Player> getPlayers() {
        return players;
    }

    
    /** 
     * @return int : The quantity of players in the object
     */
    public int getPlayersCount() {
        return this.players.size();
    }

    
    /** 
     * @param gameMode : Defines the ranges and amounts of players to generate  
     */
    private void generatePlayers(GameMode gameMode) {
        // Generate a random number of players
        int numberOfPlayers = 0;
        switch (gameMode) {
            case LEAGUE:                
                numberOfPlayers = RandomGenerators.generateNumberBetween(12,36);
                break;                
            case CHAMPIONSHIP:
                switch (RandomGenerators.generateNumberBetween(0, 4)) {
                    case 0: // 2 teams
                        numberOfPlayers= RandomGenerators.generateNumberBetween(4,6);
                        break;
                    case 1: // 4 teams
                        numberOfPlayers= RandomGenerators.generateNumberBetween(8,12);
                        break;
                    case 2: // 8 teams
                        numberOfPlayers= RandomGenerators.generateNumberBetween(16,24);
                        break;
                    case 3: // 16 teams
                        numberOfPlayers= RandomGenerators.generateNumberBetween(32,48);
                        break;
                    // case 4: // 32 teams
                    //     numberOfPlayers= RandomGenerators.generateNumberBetween(64,96);
                    //     break;
                }
                break;
        }
        for (int i = 0 ; i < numberOfPlayers ; i++) {
            Player newPlayer = new Player(RandomGenerators.generateName(), RandomGenerators.generateName(), RandomGenerators.generateNumberBetween(18, 99), i);
            players.add(newPlayer);
        }
    }
}
