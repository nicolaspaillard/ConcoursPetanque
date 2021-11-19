/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concourspetanque.models;

import concourspetanque.controllers.tools.RandomGenerators;

/**
 *
 * @author nicpa
 */
public class Match {//simples doublettes triplettes
    private Team opponent1;
    private Team opponent2;
    private int score1 = 0;
    private int score2 = 0;
    private Team winner;
    //private int score;

    public Match(Team opponent1, Team opponent2) {
        this.opponent1 = opponent1;
        this.opponent2 = opponent2;
        score1 = RandomGenerators.generateNumberBetween(0, 13);
        score2 = RandomGenerators.generateNumberBetween(0, 13);
        while (score2 == score1) {
            score2 = RandomGenerators.generateNumberBetween(0, 13);
        }
        this.winner = score1 > score2 ? opponent1 : opponent2;
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

    public int getScore1() {
        return score1;
    }

    public int getScore2() {
        return score2;
    }

    @Override
    public String toString() {
        return "Match{" +
                "Team(" + opponent1.getId() + ") score = " + score1 +
                ", Team(" + opponent2.getId() + ") score = " + score2 +
                ", winner = " + winner.getId() +
                '}';
    }
}

