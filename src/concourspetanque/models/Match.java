/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concourspetanque.models;

import java.util.Random;

/**
 *
 * @author nicpa
 */
public class Match {//simples doublettes triplettes
    private Team opponent1;
    private Team opponent2;
    private int opponent1score = 0;
    private int opponent2score = 0;
    private Team winner;
    //private int score;

    public Match(Team opponent1, Team opponent2) {
        this.opponent1 = opponent1;
        this.opponent2 = opponent2;
        this.winner = match();
    }
    public Team match(){
        //remplacer par fonction random ?
        Random r = new Random();
        opponent1score = r.nextInt(14);
        opponent2score = r.nextInt(14);
        while (opponent2score == opponent1score) {
            opponent2score = r.nextInt(14);
        }
        return opponent1score > opponent2score ? opponent1 : opponent2;
    }

    public Team getOpponent1() {
        return opponent1;
    }

    public Team getOpponent2() {
        return opponent2;
    }

    public Team getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        return "Match{" +
                "Team(" + opponent1.getId() + ") score = " + opponent1score +
                ", Team(" + opponent2.getId() + ") score = " + opponent2score +
                ", winner = " + winner.getId() +
                '}';
    }
}

