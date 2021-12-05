package concourspetanque.models;

import java.util.ArrayList;
import java.util.List;

public class Team {//simples doublettes triplettes
    private List<Player> players = new ArrayList<>();
    private List<Match> playedMatches = new ArrayList<>();
    private int positivePoints = 0;
    private int negativePoints = 0;
    private int victories = 0;
    private int goalAverage = 0;
    private int id;
    private int ranking;

    //private int score;
    public Team(List<Player> players, int id) {
        this.players = players;
        this.id = id;
    }

    public void addVictory() {
        this.victories += 1;
    }

    public void addPoints(int points) {
        this.positivePoints += points;
    }
    public void removePoints(int points) {
        this.negativePoints -= points;
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

    public List<Match> getPlayedMatchs() {
        return playedMatches;
    }

    public void addMatch(Match match) {
        this.playedMatches.add(match);
    }
    public int getPositivePoints() {
        return positivePoints;
    }

    public void setPositivePoints(int positivePoints) {
        this.positivePoints = positivePoints;
    }

    public int getNegativePoints() {
        return negativePoints;
    }

    public void setNegativePoints(int negativePoints) {
        this.negativePoints = negativePoints;
    }

    public int getVictories() {
        return victories;
    }

    public int getGoalAverage() {
        return goalAverage;
    }

    public void setGoalAverage(int goalAverage) {
        this.goalAverage = goalAverage;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "Team "+ (id+1) + " [players=" + players + "]";
    }
}
