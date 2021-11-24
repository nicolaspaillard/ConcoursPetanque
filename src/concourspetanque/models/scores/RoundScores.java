package concourspetanque.models.scores;

import java.util.ArrayList;
import java.util.List;

public class RoundScores {
    private List<MatchScores> matchesScores = new ArrayList<MatchScores>();
       
    public RoundScores() {
    }

    public void addMatch(MatchScores matchScore) {
        this.matchesScores.add(matchScore);
    }

    public List<MatchScores> getMatchesScores() {
        return this.matchesScores;
    }

    @Override
    public String toString() {
        return "RoundScores [matchesScores=" + matchesScores + "]\n";
    }
    
}
