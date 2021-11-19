package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.models.Match;
import concourspetanque.models.Round;
import concourspetanque.models.Team;
import concourspetanque.models.TeamsDraws.EightTeamsMatch;
import concourspetanque.models.TeamsDraws.SixTeamsMatch;
import concourspetanque.models.TeamsDraws.TenTeamsMatch;
import concourspetanque.models.TeamsDraws.TwelveTeamsMatch;


public class MatchesController {
    private List<Match> matches = new ArrayList<Match>();

    public MatchesController(List<Team> teams) {
        switch (teams.size()) {
            case 6:
                playMatches(SixTeamsMatch.getRounds(), teams);          
                break;
            case 8:
                playMatches(EightTeamsMatch.getRounds(), teams);
                break;
            case 10:
                playMatches(TenTeamsMatch.getRounds(), teams);
                break;
            case 12:
                playMatches(TwelveTeamsMatch.getRounds(), teams);
                break;
        }
    }  

    private void playMatches(List<Round> rounds, List<Team> teams){
        for (Round round : rounds) {
            for (int i = 0; i < round.getGamesCount(); i++) {
                int[] teamsNumbers = round.getTeamsNumbersOfGame(i);
                matches.add(new Match(teams.get(teamsNumbers[0]-1), teams.get(teamsNumbers[1]-1)));
            }
        }
    }  

    public List<Match> getMatches() {
        return matches;
    }
}
