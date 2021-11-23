package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import concourspetanque.controllers.tools.RandomGenerators;
import concourspetanque.models.GameMode;
import concourspetanque.models.MatchScore;
import concourspetanque.models.Round;
import concourspetanque.models.Team;
import concourspetanque.models.TeamScore;
import concourspetanque.models.RoundsDraws.EightTeamsRounds;
import concourspetanque.models.RoundsDraws.SixTeamsRounds;
import concourspetanque.models.RoundsDraws.TenTeamsRounds;
import concourspetanque.models.RoundsDraws.TwelveTeamsRounds;


public class GameController {
    private TeamsController teamsController;
    private List<Team> teams;
    private List<MatchScore> matchesScores = new ArrayList<MatchScore>();
    private List<TeamScore> teamsScores = new ArrayList<TeamScore>();

    public GameController(GameMode gameMode) {
        this.teamsController = new TeamsController(gameMode);        
        this.teams = this.teamsController.getTeams();
        this.teams.forEach(t -> teamsScores.add(new TeamScore(t)) );
        switch (gameMode) {
            case LEAGUE:      
                playLeague();
                break;
            case CHAMPIONSHIP:
                playChampionship();   
                break;
        }
    }  

    private void playLeague(){
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

    private void playChampionship() {
        while (teams.size() > 1) {
            teams = playRounds(Arrays.asList(getRound(teams)));
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

    public Round getRound(List<Team> teams){
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

    private Team updateTeamsScore(int[] teamsNumbers) {
        MatchScore lastMatch = new MatchScore(teamsScores.get(teamsNumbers[0]-1), teamsScores.get(teamsNumbers[1]-1));
        matchesScores.add(lastMatch);
        TeamScore lastWinner = teamsScores.get(teamsScores.indexOf(lastMatch.getWinner()));
        TeamScore lastLooser = teamsScores.get(teamsScores.indexOf(lastMatch.getLooser()));
        lastWinner.addVictory();
        lastWinner.addWin();
        lastLooser.addLoss();
        lastLooser.removeWin();
        return lastWinner;
    }

    public List<MatchScore> getMatchesScores() {
        return matchesScores;
    }

    public List<TeamScore> getTeamsScores(){
        return teamsScores;
    }
}
