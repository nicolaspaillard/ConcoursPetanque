package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import concourspetanque.controllers.tools.RandomGenerators;
import concourspetanque.models.GameMode;
import concourspetanque.models.Round;
import concourspetanque.models.Team;
import concourspetanque.models.rounds.EightTeamsRounds;
import concourspetanque.models.rounds.SixTeamsRounds;
import concourspetanque.models.rounds.TenTeamsRounds;
import concourspetanque.models.rounds.TwelveTeamsRounds;


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
                playRounds(SixTeamsRounds.getRounds(),-1);          
                break;
            case 8:
                playRounds(EightTeamsRounds.getRounds(),-1);
                break;
            case 10:
                playRounds(TenTeamsRounds.getRounds(),-1);
                break;
            case 12:
                playRounds(TwelveTeamsRounds.getRounds(),-1);
                break;
        }
    }

    /** 
     * Call this method to start in championship game mode.
     */
    private void playChampionship() {
        List<Team> teams = new ArrayList<Team>(teamsController.getTeams());
        int roundNumber = 0;
        while (teams.size() > 1) {
            teams = playRounds(Arrays.asList(getRound(teams)), roundNumber);
            roundNumber++;
        }
    }

    /** 
     * @param rounds : The list of rounds (lists of matches) to be played
     */
    private List<Team> playRounds(List<Round> rounds, int roundNumber){
        List<Team> teams = new ArrayList<Team>();
        for (int i = 0; i < rounds.size(); i++) {
            for (int j = 0; j< rounds.get(i).getMatchesCount(); j++) {
                int[] teamsIDs = rounds.get(i).getTeamsIDsOfMatch(j);
                if(roundNumber == -1){
                    teams.add(getMatchWinner(teamsIDs[0]-1, teamsIDs[1]-1, i));//
                }else{
                    teams.add(getMatchWinner(teamsIDs[0], teamsIDs[1], roundNumber));
                }                
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
            for (int i = 0; i < 2; i++) {
                int team = RandomGenerators.generateNumberBetween(0, tempTeams.size()-1);
                opponents[i] = tempTeams.get(team).getId();
                tempTeams.remove(team);
            }
            ret.add(opponents);
        }
        return new Round(ret);
    } 

    /** 
     * @param teamsNumbers : An array containing the id of both opponents teams
     * @return Team : The winning team
     */
    private Team getMatchWinner(int teamID1, int teamID2, int roundNumber) {
        int winnerID = scoresController.addMatch(teamID1, teamID2, roundNumber);        
        return this.teamsController.getTeam(winnerID);
    }

    public ScoresController getScores(){
        return scoresController;
    }
}