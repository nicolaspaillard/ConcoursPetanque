package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.models.Match;
import concourspetanque.models.Team;
import concourspetanque.utils.RandomGenerators;

public class MatchController {
    private List<Match> matchs;

    public MatchController () {
        this.matchs = new ArrayList<Match>();
    }

    /**
     * Renvoie la liste des matchs
     * @return
     */
    public List<Match> getMatchs() {
        return matchs;
    }

    /**
     * Joue un match et ajoute le match à la liste des matchs
     * 
     * @param team1
     * @param team2
     * @param roundNumber
     * @return le dernier match joué
     */
    public Match playMatch(Team team1, Team team2, int roundNumber) {
        // Compute random opponents scores
        int opponent1score = RandomGenerators.generateNumberBetween(0, 14);
        int opponent2score = RandomGenerators.generateNumberBetween(0, 14);
        while (opponent2score == opponent1score) {
            opponent2score = RandomGenerators.generateNumberBetween(0, 14);
        }
        // Instanciate match
        Match match = new Match(team1, team2, opponent1score, opponent2score, roundNumber);
        // Add match to List
        this.matchs.add(match);
        return match;
    }

    
}
