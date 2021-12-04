package concourspetanque.models.rounds.implementations;

import java.util.Arrays;
import java.util.List;

import concourspetanque.models.Round;
import concourspetanque.models.rounds.ILeagueRounds;

public class SixTeamsRounds implements ILeagueRounds {
    
    private final Round roundOne = new Round(Arrays.asList(
        new int[]{1,2},
        new int[]{3,4},
        new int[]{5,6}
    ));
    private final Round roundTwo = new Round(Arrays.asList(
        new int[]{1,3},
        new int[]{2,6},
        new int[]{4,5}
    ));
    private final Round roundThree = new Round(Arrays.asList(
        new int[]{1,4},
        new int[]{5,2},
        new int[]{3,6}
    ));
    private final Round roundFour = new Round(Arrays.asList(
        new int[]{1,5},
        new int[]{6,4},
        new int[]{2,3}
    ));

    public List<Round> getRounds(){
        return Arrays.asList(roundOne, roundTwo, roundThree, roundFour);
    }
}
