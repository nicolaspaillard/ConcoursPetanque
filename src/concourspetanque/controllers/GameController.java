package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import concourspetanque.controllers.tools.RandomGenerators;
import concourspetanque.models.GameMode;
import concourspetanque.models.Round;
import concourspetanque.models.Team;
import concourspetanque.models.RoundsDraws.EightTeamsRounds;
import concourspetanque.models.RoundsDraws.SixTeamsRounds;
import concourspetanque.models.RoundsDraws.TenTeamsRounds;
import concourspetanque.models.RoundsDraws.TwelveTeamsRounds;


public class GameController {
    private ScoresController scoresController;
    private TeamsController teamsController;

    public GameController(GameMode gameMode) {
        this.teamsController = new TeamsController(gameMode);
        this.scoresController = new ScoresController(this.teamsController.getTeams());

        switch (gameMode) {
            case LEAGUE:      
                playLeague();
                break;
            case CHAMPIONSHIP:
                playChampionship();   
                break;
        }
    }  

    /** 
     * Call this method to start in league game mode.
     */
    private void playLeague(){
        switch (teamsController.getTeams().size()) {
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

    /** 
     * Call this method to start in championship game mode.
     */
    private void playChampionship() {
        List<Team> teams = teamsController.getTeams(); 
        while (teams.size() > 1) {
            teams = playRounds(Arrays.asList(getRound(teams)));
        }
    }

    /** 
     * @param rounds : The list of rounds (lists of matches) to be played
     */
    private List<Team> playRounds(List<Round> rounds){
        List<Team> teams = new ArrayList<Team>();
        for (Round round : rounds) {
            for (int i = 0; i < round.getMatchesCount(); i++) {
                int[] teamsIDs = round.getTeamsIDsOfMatch(i);
                teams.add(getMatchWinner(teamsIDs[0], teamsIDs[1]));
            }            
        }
        return teams;
    } 

    /** 
     * While there are teams in the tempTeams list,
     * randomly selects 2,
     * removes them from it and
     * add their match to the round
     * @param tempTeams : A temporary list of teams for the method to use
     * @return Round : An object that stores all opponents ids for each match of a round
     */
    private Round getRound(List<Team> tempTeams){
        List<int[]> ret = new ArrayList<int[]>();
        // Loops while there are teams to match in the tempTeams list 
        while(tempTeams.size()>0) {
            int[] opponents = {0,0};// Must init ?
            int opponent0 = RandomGenerators.generateNumberBetween(1, tempTeams.size());
            opponents[0] = tempTeams.get(opponent0-1).getId();
            tempTeams.remove(opponent0-1);
            int opponent1 = RandomGenerators.generateNumberBetween(1, tempTeams.size());
            opponents[1] = tempTeams.get(opponent1-1).getId();
            tempTeams.remove(opponent1-1);
            ret.add(opponents);
        }
        return new Round(ret);
    } 

    /** 
     * @param teamsNumbers : An array containing the id of both opponents teams
     * @return Team : The winning team
     */
    private Team getMatchWinner(int teamID1, int teamID2) {
        int winner = scoresController.addMatch(teamID1, teamID2);        
        return teamsController.getTeam(winner);
    }

    public ScoresController getScores(){
        return scoresController;
    }
}