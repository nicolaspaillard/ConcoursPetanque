package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.models.Player;
import concourspetanque.utils.RandomGenerators;

public class PlayersController {
    private List<Player> players;

    public PlayersController() {
        this.players = new ArrayList<Player>();
    }
    
    /** 
     * @return List<Player> : A list containing all the players
     */
    public List<Player> getPlayers() {
        return this.players;
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
    public void generatePlayers(int min, int max) {
        // Generate a random number of players
        int numberOfPlayers = RandomGenerators.generateNumberBetween(min, max+1);
        // Instanciate players (FirstName, Name, Age, ID)
        for (int i = 0 ; i < numberOfPlayers ; i++) {
            Player newPlayer = new Player(
                RandomGenerators.generateName(), 
                RandomGenerators.generateName(), 
                RandomGenerators.generateNumberBetween(18, 100), // WARNING
                i
            );
            this.players.add(newPlayer);
        }
    }
}
