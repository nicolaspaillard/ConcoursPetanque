package concourspetanque.models.leagueRounds;

import java.util.HashMap;
import java.util.Map;

import concourspetanque.models.interfaces.LeagueRoundsInterface;

public class SixTeams implements LeagueRoundsInterface{
    public Map<String, int[]> roundOne() {
        Map<String, int[]> round = new HashMap<>();
        round.put("1", new int[]{1, 2});
        round.put("2", new int[]{3, 4});
        round.put("3", new int[]{5, 6});
        return round;
    }
    public Map<String, int[]> roundTwo() {
        Map<String, int[]> round = new HashMap<>();
        round.put("1", new int[]{1, 3});
        round.put("2", new int[]{2, 6});
        round.put("3", new int[]{4, 5});
        return round;
    }
    public Map<String, int[]> roundThree() {
        Map<String, int[]> round = new HashMap<>();
        round.put("1", new int[]{1, 4});
        round.put("2", new int[]{5, 2});
        round.put("3", new int[]{3, 6});
        return round;
    }
    public Map<String, int[]> roundFour() {
        Map<String, int[]> round = new HashMap<>();
        round.put("1", new int[]{1, 5});
        round.put("2", new int[]{6, 4});
        round.put("3", new int[]{2, 3});
        return round;
    }
}
