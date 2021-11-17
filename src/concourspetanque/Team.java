/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concourspetanque;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicpa
 */
public class Team {//simples doublettes triplettes
    private List<Player> players = new ArrayList<Player>();
    private int id;

    //private int score;
    public Team(List<Player> players) {
        this.players = players;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Team [players=" + players + "]";
    }
    
}
