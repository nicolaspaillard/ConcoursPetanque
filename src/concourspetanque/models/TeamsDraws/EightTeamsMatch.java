package concourspetanque.models.TeamsDraws;

import java.util.Arrays;
import java.util.List;

import concourspetanque.models.Round;

public class EightTeamsMatch{
    
    private static final Round roundOne = new Round(Arrays.asList(
        new int[]{1,2},
        new int[]{3,4},
        new int[]{5,6},
        new int[]{7,8}
    ));
    private static final Round roundTwo = new Round(Arrays.asList(
        new int[]{1,3},
        new int[]{2,4},
        new int[]{5,7},
        new int[]{6,8}
    ));
    private static final Round roundThree = new Round(Arrays.asList(
        new int[]{1,4},
        new int[]{2,3},
        new int[]{5,8},
        new int[]{6,7}
    ));
    private static final Round roundFour = new Round(Arrays.asList(
        new int[]{1,5},
        new int[]{2,8},
        new int[]{3,7},
        new int[]{4,6}
    ));
    
    public static List<Round> getRounds(){
        return Arrays.asList(roundOne, roundTwo, roundThree, roundFour);
    }
}
