package concourspetanque.models.rounds.implementations;

import java.util.Arrays;

import concourspetanque.models.Round;
import concourspetanque.models.rounds.ILeagueRounds;

public class SixTeamsRounds implements ILeagueRounds {
    @Override
    public Round roundOne() {
        return new Round(Arrays.asList(
            new int[]{1,2},
            new int[]{3,4},
            new int[]{5,6}
        ), 0);
    }
    @Override
    public Round roundTwo() {
        return new Round(Arrays.asList(
            new int[]{1,3},
            new int[]{2,6},
            new int[]{4,5}
        ), 1);
    }
    @Override
    public Round roundThree() {
        return new Round(Arrays.asList(
            new int[]{1,4},
            new int[]{5,2},
            new int[]{3,6}
        ), 2);
    }
    @Override
    public Round roundFour() {
        return new Round(Arrays.asList(
            new int[]{1,5},
            new int[]{6,4},
            new int[]{2,3}
        ), 3);
    }
}
