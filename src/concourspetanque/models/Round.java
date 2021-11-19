package concourspetanque.models;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private List<int[]> gamesTeamsNumbers = new ArrayList<int[]>();

    public Round(List<int[]> gamesTeamsNumbers) {
        this.gamesTeamsNumbers = gamesTeamsNumbers;
    }
    public Round() {}

    public int getGamesCount() {
        return gamesTeamsNumbers.size();
    }
    public int[] getTeamsNumbersOfGame(int index) {
        return gamesTeamsNumbers.get(index);
    }
}
