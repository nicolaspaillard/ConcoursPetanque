/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concourspetanque;

/**
 *
 * @author nicpa
 */
public class Match {//simples doublettes triplettes
    private Team opponent1;
    private Team opponent2;

    private Team winner;
    //private int score;

    public Match(Team opponent1, Team opponent2) {
        this.opponent1 = opponent1;
        this.opponent2 = opponent2;
        this.winner = match();
    }
    public Team match(){
    //random 
    return this.opponent1;
    }
}

