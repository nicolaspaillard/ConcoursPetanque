package concourspetanque.models;

import concourspetanque.controllers.tools.RandomGenerators;

public class Match {
    private Team team1;
    private Team team2;
    private int score1 = 0;
    private int score2 = 0;

    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = RandomGenerators.generateNumberBetween(0, 13);
        this.score2 = RandomGenerators.generateNumberBetween(0, 13);
        while (this.score2 == this.score1) {
            this.score1 = RandomGenerators.generateNumberBetween(0, 13);
            this.score2 = RandomGenerators.generateNumberBetween(0, 13);
        }
    }
    
    public Team getWinner(){
        return score1 > score2 ? team1 : team2;
    }
    public Team getLooser(){
        return score1 > score2 ? team2 : team1;
    }

    public Team getTeam1(){
        return team1;
    }
    public Team getTeam2(){
        return team2;
    }

    public int getScore1(){
        return score1;
    }
    public int getScore2(){
        return score2;
    }

    @Override
    public String toString() {
        return "Match{" +
                "Team " + team1.getId() + " = " + score1 +
                " - Team " + team2.getId() + " = " + score2 +
                " - Winner : Team " + getWinner().getId() +
                '}';
    }
}

