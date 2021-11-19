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
    private List<Team> teams = new ArrayList<Team>();

    public MatchesController(List<Team> teams) {
        this.teams = teams;
        switch (teams.size()) {
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
                matches.add(new Match(teams.get(teamsNumbers[0]-1), teams.get(teamsNumbers[1]-1)));
            }
        }
        matches.forEach(m ->{
            teams.get(teams.indexOf(m.getWinner())).addVictory();
            teams.get(teams.indexOf(m.getLooser())).addLoss();
        });
    }  

    public List<Match> getMatches() {
        return matches;
    }

    public List<Team> getTeamsScores(){
        return teams;
    }
}
