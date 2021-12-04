package concourspetanque.models.rounds.implementations;

import java.util.Arrays;
import java.util.List;

import concourspetanque.models.Round;
import concourspetanque.models.rounds.ILeagueRounds;

public class EightTeamsRounds implements ILeagueRounds {
    
    public final Round roundOne = new Round(Arrays.asList(
        new int[]{1,2},
        new int[]{3,4},
        new int[]{5,6},
        new int[]{7,8}
    ));
    public final Round roundTwo = new Round(Arrays.asList(
        new int[]{1,3},
        new int[]{2,4},
        new int[]{5,7},
        new int[]{6,8}
    ));
    public final Round roundThree = new Round(Arrays.asList(
        new int[]{1,4},
        new int[]{2,3},
        new int[]{5,8},
        new int[]{6,7}
    ));
    public final Round roundFour = new Round(Arrays.asList(
        new int[]{1,5},
        new int[]{2,8},
        new int[]{3,7},
        new int[]{4,6}
    ));
    
    public List<Round> getRounds(){
        return Arrays.asList(roundOne, roundTwo, roundThree, roundFour);
    }
}
