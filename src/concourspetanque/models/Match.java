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
    private Team team1;
    private Team team2;
    private int score1 = 0;
    private int score2 = 0;
    private Team winner;
    //private int score;

    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = RandomGenerators.generateNumberBetween(0, 13);
        this.score2 = RandomGenerators.generateNumberBetween(0, 13);
        while (this.score2 == this.score1) {
            this.score1 = RandomGenerators.generateNumberBetween(0, 13);
            this.score2 = RandomGenerators.generateNumberBetween(0, 13);
        }
        this.winner = this.score1 > this.score2 ? this.team1 : this.team2;
    }
    
    @Override
    public String toString() {
        return "Match{" +
                "Team " + team1.getId() + " = " + score1 +
                " - Team " + team2.getId() + " = " + score2 +
                " - Winner : Team " + winner.getId() +
                '}';
    }
}

