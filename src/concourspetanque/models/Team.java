/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concourspetanque.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicpa
 */
public class Team {//simples doublettes triplettes
    private List<Player> players = new ArrayList<>();

    private int id;

    public Team(List<Player> players, int id) {
        this.players = players;
        this.id = id;
    }
    
    public List<Player> getPlayers() {
        return players;
    }
    public void addPlayer(Player player) {
        this.players.add(player);
    }   
    
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Team "+ (id+1) + " [players=" + players + "]";
    }
    
}
