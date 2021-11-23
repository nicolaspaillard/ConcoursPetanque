package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.controllers.tools.RandomGenerators;
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

    public GameController(List<Team> teams, int gameMode) {
        teams.forEach(t -> teamsScores.add(new TeamScore(t)) );
        switch (gameMode) {
            case 0:          
                playLeague(teams);                  
                break;
            case 1:
                playOther();
                break;
        }
    }  

    private void playLeague(List<Team> teams) {
        while (teams.size() > 1) {
            List<Round> rounds = new ArrayList<Round>();
            rounds.add(genRound(teams));
            teams = playRounds(rounds);
        }
    }

    public Round genRound(List<Team> teams){
        List<int[]> ret = new ArrayList<int[]>();
        while(teams.size()>0) {
            int[] opponents = {0,0};
            int opponent0 = RandomGenerators.generateNumberBetween(1, teams.size());
            opponents[0] = teams.get(opponent0-1).getId();
            teams.remove(opponent0-1);
            int opponent1 = RandomGenerators.generateNumberBetween(1, teams.size());
            opponents[1] = teams.get(opponent1-1).getId();
            teams.remove(opponent1-1);
            ret.add(opponents);
        }
        return new Round(ret);
    }
    
    private void playOther(){
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

    private List<Team> playRounds(List<Round> rounds){
        List<Team> winners = new ArrayList<Team>();
        for (Round round : rounds) {
            for (int i = 0; i < round.getMatchesCount(); i++) {
                int[] teamsNumbers = round.getTeamsNumbersOfMatch(i);
                winners.add(updateTeamsScore(teamsNumbers));
            }            
        }
        return winners;
    }  

    private Team updateTeamsScore(int[] teamsNumbers) {
        matchesScores.add(new MatchScore(teamsScores.get(teamsNumbers[0]-1), teamsScores.get(teamsNumbers[1]-1)));
        teamsScores.get(teamsScores.indexOf(matchesScores.get(matchesScores.size()-1).getWinner())).addVictory();
        teamsScores.get(teamsScores.indexOf(matchesScores.get(matchesScores.size()-1).getWinner())).addWin();
        teamsScores.get(teamsScores.indexOf(matchesScores.get(matchesScores.size()-1).getLooser())).addLoss();
        teamsScores.get(teamsScores.indexOf(matchesScores.get(matchesScores.size()-1).getLooser())).removeWin();
        return matchesScores.get(matchesScores.size()-1).getWinner();
    }

    public List<MatchScore> getMatchesScores() {
        return matchesScores;
    }

    public List<TeamScore> getTeamsScores(){
        return teamsScores;
    }
}
