package concourspetanque.models;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private List<int[]> gamesTeamsNumbers = new ArrayList<int[]>();
    private int roundNumber;

    public Round(List<int[]> gamesTeamsNumbers, int roundNumber) {
        this.gamesTeamsNumbers = gamesTeamsNumbers;
        this.roundNumber = roundNumber;
    }
    public Round() {}

    public int getMatchesCount() {
        return gamesTeamsNumbers.size();
    }
    public int[] getOpponentsIds(int index) {
        return gamesTeamsNumbers.get(index);
    }
    public int getRoundNumber(){
        return roundNumber;
    }
}
