package concourspetanque.models.rounds.implementations;

import java.util.Arrays;

import concourspetanque.models.Round;
import concourspetanque.models.rounds.ILeagueRounds;

public class TwelveTeamsRounds implements ILeagueRounds {
    @Override
    public Round roundOne() {
        return new Round(Arrays.asList(
            new int[]{1,2},
            new int[]{3,4},
            new int[]{5,6},
            new int[]{7,8},
            new int[]{9,10},
            new int[]{11,12}
        ), 0);
    }
    @Override
    public Round roundTwo() {
        return new Round(Arrays.asList(
            new int[]{1,12},
            new int[]{2,11},
            new int[]{3,10},
            new int[]{4,9},
            new int[]{5,8},
            new int[]{6,7}
        ), 1);
    }
    @Override
    public Round roundThree() {
        return new Round(Arrays.asList(
            new int[]{1,3},
            new int[]{2,4},
            new int[]{5,7},
            new int[]{6,8},
            new int[]{12,9},
            new int[]{10,11}
        ), 2);
    }
    @Override
    public Round roundFour() {
        return new Round(Arrays.asList(
            new int[]{12,6},
            new int[]{3,8},
            new int[]{11,7},
            new int[]{1,9},
            new int[]{4,10},
            new int[]{5,2}
        ), 3);
    }
}
