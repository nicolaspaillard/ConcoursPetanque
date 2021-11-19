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
    private int victories = 0;
    private int loses = 0;
    private int position = 0;
    private int id;

    public Team(List<Player> players, int id) {
        this.players = players;
        this.id = id;
    }
    
    public List<Player> getPlayers() {
        return players;
    }
    public int getVictories(){
        return this.victories;
    }
    public int getLoses(){
        return this.loses;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }    
    public void addVictory(){
        this.victories++;
    }
    public void addLoss(){
        this.loses++;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Team "+ (id+1) + " [players=" + players + "]";
    }
    
}
