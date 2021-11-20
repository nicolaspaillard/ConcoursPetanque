package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.models.MatchScore;
import concourspetanque.models.Round;
import concourspetanque.models.Team;
import concourspetanque.models.TeamScore;
import concourspetanque.models.RoundsDraws.EightTeamsRounds;
import concourspetanque.models.RoundsDraws.SixTeamsRounds;
import concourspetanque.models.RoundsDraws.TenTeamsRounds;
import concourspetanque.models.RoundsDraws.TwelveTeamsRounds;


public class GameController {
    private List<MatchScore> matchesScores = new ArrayList<MatchScore>();
    private List<TeamScore> teamsScores = new ArrayList<TeamScore>();

    public GameController(List<Team> teams) {
        teams.forEach(t -> teamsScores.add(new TeamScore(t)) );
        switch (teamsScores.size()) {
            case 6:
                playRounds(SixTeamsRounds.getRounds());          
                break;
            case 8:
                playRounds(EightTeamsRounds.getRounds());
                break;
            case 10:
                playRounds(TenTeamsRounds.getRounds());
                break;
            case 12:
                playRounds(TwelveTeamsRounds.getRounds());
                break;
        }
    }  

    private void playRounds(List<Round> rounds){
        for (Round round : rounds) {
            for (int i = 0; i < round.getMatchesCount(); i++) {
                int[] teamsNumbers = round.getTeamsNumbersOfMatch(i);
                updateTeamScore(teamsNumbers);
            }            
        }
    }  

    private void updateTeamScore(int[] teamsNumbers) {
        matchesScores.add(new MatchScore(teamsScores.get(teamsNumbers[0]-1), teamsScores.get(teamsNumbers[1]-1)));
        teamsScores.get(teamsScores.indexOf(matchesScores.get(matchesScores.size()-1).getWinner())).addVictory();
        teamsScores.get(teamsScores.indexOf(matchesScores.get(matchesScores.size()-1).getWinner())).addWin();
        teamsScores.get(teamsScores.indexOf(matchesScores.get(matchesScores.size()-1).getLooser())).addLoss();
        teamsScores.get(teamsScores.indexOf(matchesScores.get(matchesScores.size()-1).getLooser())).removeWin();
    }

    public List<MatchScore> getMatchesScores() {
        return matchesScores;
    }

    public List<TeamScore> getTeamsScores(){
        return teamsScores;
    }
}
