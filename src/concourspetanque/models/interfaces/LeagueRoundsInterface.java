package concourspetanque.models.interfaces;

import java.util.Map;

public interface LeagueRoundsInterface {
    public Map<String, int[]> roundOne();
    public Map<String, int[]> roundTwo();
    public Map<String, int[]> roundThree();
    public Map<String, int[]> roundFour();
}
