package concourspetanque.models.rounds.implementations;

import java.util.Arrays;
import java.util.List;

import concourspetanque.models.Round;
import concourspetanque.models.rounds.ILeagueRounds;

public class EightTeamsRounds implements ILeagueRounds {
    @Override
    public Round roundOne() {
        return new Round(Arrays.asList(
            new int[]{1,2},
            new int[]{3,4},
            new int[]{5,6},
            new int[]{7,8}
        ));
    }
    @Override
    public Round roundTwo() {
        return new Round(Arrays.asList(
            new int[]{1,3},
            new int[]{2,4},
            new int[]{5,7},
            new int[]{6,8}
        ));
    }
    @Override
    public Round roundThree() {
        return new Round(Arrays.asList(
            new int[]{1,4},
            new int[]{2,3},
            new int[]{5,8},
            new int[]{6,7}
        ));
    }
    @Override
    public Round roundFour() {
        return new Round(Arrays.asList(
            new int[]{1,5},
            new int[]{2,8},
            new int[]{3,7},
            new int[]{4,6}
        ));
    }
}
