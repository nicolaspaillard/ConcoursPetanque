package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.models.Match;
import concourspetanque.models.Round;
import concourspetanque.models.Team;
import concourspetanque.models.TeamScore;
import concourspetanque.models.TeamsDraws.EightTeamsMatch;
import concourspetanque.models.TeamsDraws.SixTeamsMatch;
import concourspetanque.models.TeamsDraws.TenTeamsMatch;
import concourspetanque.models.TeamsDraws.TwelveTeamsMatch;


public class MatchesController {
    private List<Match> matches = new ArrayList<Match>();
    private List<TeamScore> teamsScores = new ArrayList<TeamScore>();
    public MatchesController(List<Team> teams) {
        teams.forEach(t -> teamsScores.add(new TeamScore(t)) );
        switch (teamsScores.size()) {
            case 6:
                playMatches(SixTeamsMatch.getRounds());          
                break;
            case 8:
                playMatches(EightTeamsMatch.getRounds());
                break;
            case 10:
                playMatches(TenTeamsMatch.getRounds());
                break;
            case 12:
                playMatches(TwelveTeamsMatch.getRounds());
                break;
        }
    }  

    private void playMatches(List<Round> rounds){
        for (Round round : rounds) {
            for (int i = 0; i < round.getGamesCount(); i++) {
                int[] teamsNumbers = round.getTeamsNumbersOfGame(i);
                matches.add(new Match(teamsScores.get(teamsNumbers[0]-1), teamsScores.get(teamsNumbers[1]-1)));
                teamsScores.get(teamsScores.indexOf(matches.get(matches.size()-1).getWinner())).addVictory();
                teamsScores.get(teamsScores.indexOf(matches.get(matches.size()-1).getWinner())).addWin();
                teamsScores.get(teamsScores.indexOf(matches.get(matches.size()-1).getLooser())).addLoss();
                teamsScores.get(teamsScores.indexOf(matches.get(matches.size()-1).getLooser())).removeWin();
            }            
        }
    }  

    public List<Match> getMatches() {
        return matches;
    }

    public List<TeamScore> getTeamsScores(){
        return teamsScores;
    }
}
