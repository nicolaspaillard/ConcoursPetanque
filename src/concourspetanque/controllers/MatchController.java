package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.models.Match;
import concourspetanque.models.Team;
import concourspetanque.utils.RandomGenerators;

public class MatchController {
    private List<Match> matchs;

    public MatchController () {
        this.matchs = new ArrayList<>();
    }

    public List<Match> getMatchs() {
        return matchs;
    }

    public void playMatch(Team team1, Team team2) {
        // Compute random opponents scores
        int opponent1score = RandomGenerators.generateNumberBetween(0, 14);
        int opponent2score = RandomGenerators.generateNumberBetween(0, 14);
        while (opponent2score == opponent1score) {
            opponent2score = RandomGenerators.generateNumberBetween(0, 14);
        }
        // Instanciate match
        Match match = new Match(team1, team2);
        match.setOpponent1score(opponent1score);
        match.setOpponent2score(opponent2score);
        match.setWinner(opponent1score > opponent2score ? team1 : team2);
        // Add match to List
        this.matchs.add(match);
    }

    
}
