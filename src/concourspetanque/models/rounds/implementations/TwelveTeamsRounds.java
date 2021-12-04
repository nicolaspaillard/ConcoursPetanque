package concourspetanque.models.rounds.implementations;

import java.util.Arrays;
import java.util.List;

import concourspetanque.models.Round;
import concourspetanque.models.rounds.ILeagueRounds;

public class TwelveTeamsRounds implements ILeagueRounds {
    private final Round roundOne = new Round(Arrays.asList(
        new int[]{1,2},
        new int[]{3,4},
        new int[]{5,6},
        new int[]{7,8},
        new int[]{9,10},
        new int[]{11,12}
    ));
    private final Round roundTwo = new Round(Arrays.asList(
        new int[]{1,12},
        new int[]{2,11},
        new int[]{3,10},
        new int[]{4,9},
        new int[]{5,8},
        new int[]{6,7}
    ));
    private final Round roundThree = new Round(Arrays.asList(
        new int[]{1,3},
        new int[]{2,4},
        new int[]{5,7},
        new int[]{6,8},
        new int[]{12,9},
        new int[]{10,11}
    ));
    private final Round roundFour = new Round(Arrays.asList(
        new int[]{12,6},
        new int[]{3,8},
        new int[]{11,7},
        new int[]{1,9},
        new int[]{4,10},
        new int[]{5,2}
    ));

    public List<Round> getRounds(){
        return Arrays.asList(roundOne, roundTwo, roundThree, roundFour);
    }
}
