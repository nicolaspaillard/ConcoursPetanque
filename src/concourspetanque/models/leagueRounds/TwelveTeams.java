package concourspetanque.models.leagueRounds;

import java.util.HashMap;
import java.util.Map;

import concourspetanque.models.interfaces.LeagueRoundsInterface;

public class TwelveTeams implements LeagueRoundsInterface{
    public Map<String, int[]> roundOne() {
        Map<String, int[]> round = new TenTeams().roundOne();
        round.put("6", new int[]{11, 12});
        return round;
    }
    public Map<String, int[]> roundTwo() {
        Map<String, int[]> round = new HashMap<>();
        round.put("1", new int[]{1, 12});
        round.put("2", new int[]{2, 11});
        round.put("3", new int[]{3, 10});
        round.put("4", new int[]{4, 9});
        round.put("5", new int[]{5, 8});
        round.put("6", new int[]{6, 7});
        return round;
    }
    public Map<String, int[]> roundThree() {
        Map<String, int[]> round = new EightTeams().roundTwo();
        round.put("5", new int[]{12, 9});
        round.put("6", new int[]{10, 11});
        return round;
    }
    public Map<String, int[]> roundFour() {
        Map<String, int[]> round = new HashMap<>();
        round.put("1", new int[]{12, 6});
        round.put("2", new int[]{3, 8});
        round.put("3", new int[]{11, 7});
        round.put("4", new int[]{1, 9});
        round.put("5", new int[]{4, 10});
        round.put("6", new int[]{5, 2});
        return round;
    }
}
