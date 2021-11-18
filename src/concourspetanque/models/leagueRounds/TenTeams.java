package concourspetanque.models.leagueRounds;

import java.util.HashMap;
import java.util.Map;

import concourspetanque.models.interfaces.LeagueRoundsInterface;

public class TenTeams implements LeagueRoundsInterface{
    public Map<String, int[]> roundOne() {
        Map<String, int[]> round = new EightTeams().roundOne();
        round.put("5", new int[]{9, 10});
        return round;
    }
    public Map<String, int[]> roundTwo() {
        Map<String, int[]> round = new HashMap<>();
        round.put("1", new int[]{2, 3});
        round.put("2", new int[]{4, 5});
        round.put("3", new int[]{6, 7});
        round.put("4", new int[]{8, 9});
        round.put("5", new int[]{10, 1});
        return round;
    }
    public Map<String, int[]> roundThree() {
        Map<String, int[]> round = new HashMap<>();
        round.put("1", new int[]{1, 3});
        round.put("2", new int[]{2, 4});
        round.put("3", new int[]{9, 6});
        round.put("4", new int[]{8, 10});
        round.put("5", new int[]{5, 7});
        return round;
    }
    public Map<String, int[]> roundFour() {
        Map<String, int[]> round = new HashMap<>();
        round.put("1", new int[]{6, 8});
        round.put("2", new int[]{7, 9});
        round.put("3", new int[]{10, 3});
        round.put("4", new int[]{5, 2});
        round.put("5", new int[]{4, 1});
        return round;
    }
}
