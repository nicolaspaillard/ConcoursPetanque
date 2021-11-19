package concourspetanque.models.TeamsDraws;

import java.util.Arrays;
import java.util.List;

import concourspetanque.models.Round;

public class SixTeamsMatch{
    
    private static final Round roundOne = new Round(Arrays.asList(
        new int[]{1,2},
        new int[]{3,4},
        new int[]{5,6}
    ));
    private static final Round roundTwo = new Round(Arrays.asList(
        new int[]{1,3},
        new int[]{2,6},
        new int[]{4,5}
    ));
    private static final Round roundThree = new Round(Arrays.asList(
        new int[]{1,4},
        new int[]{5,2},
        new int[]{3,6}
    ));
    private static final Round roundFour = new Round(Arrays.asList(
        new int[]{1,5},
        new int[]{6,4},
        new int[]{2,3}
    ));

    public static List<Round> getRounds(){
        return Arrays.asList(roundOne, roundTwo, roundThree, roundFour);
    }
}
