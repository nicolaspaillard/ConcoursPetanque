package concourspetanque.models.rounds;

import java.util.Arrays;
import java.util.List;

import concourspetanque.models.Round;
import concourspetanque.models.rounds.implementations.EightTeamsRounds;
import concourspetanque.models.rounds.implementations.SixTeamsRounds;
import concourspetanque.models.rounds.implementations.TenTeamsRounds;
import concourspetanque.models.rounds.implementations.TwelveTeamsRounds;

public class RoundsStrategy {
    private static ILeagueRounds roundsSetup;

    private static void pickStrategy(int teamsCount) {
        switch (teamsCount) {
            case 6:
                roundsSetup = new SixTeamsRounds();
                break;
            case 8:
                roundsSetup = new EightTeamsRounds();
                break;
            case 10:
                roundsSetup = new TenTeamsRounds();
                break;
            case 12:
                roundsSetup = new TwelveTeamsRounds();
        }
    }
    
    public static List<Round> getRounds(int teamsCount){
        pickStrategy(teamsCount);
        return Arrays.asList(roundsSetup.roundOne(), roundsSetup.roundTwo(), roundsSetup.roundThree(), roundsSetup.roundFour());
    }
}
