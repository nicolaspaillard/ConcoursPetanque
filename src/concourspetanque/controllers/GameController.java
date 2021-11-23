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

    /** 
     * Call this method to start in league game mode.
     */
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

    /** 
     * Call this method to start in championship game mode.
     */
    private void playChampionship() {
        while (teams.size() > 1) {
            playRounds(Arrays.asList(getRound(teams)));
        }
    }

    /** 
     * @param rounds : The list of rounds (lists of matches) to be played
     */
    private void playRounds(List<Round> rounds){
        teams = new ArrayList<Team>();
        for (Round round : rounds) {
            for (int i = 0; i < round.getMatchesCount(); i++) {
                int[] teamsNumbers = round.getTeamsNumbersOfMatch(i);
                teams.add(updateTeamsScore(teamsNumbers));
            }            
        }
    } 

    /** 
     * While there are teams in the tempTeams<p>
     * Randomly selects 2 teams in the temporary list<p>
     * Removes them from the list<p>
     * Add their match to the round
     * @param tempTeams : A temporary list of teams for the method to use
     * @return Round : An object to store all matches opponents ids for a round
     */
    public Round getRound(List<Team> tempTeams){
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
    private Team updateTeamsScore(int[] teamsNumbers) {//ça merde ptetre un peu la dedans ( teamsScores.get(teamsNumbers[1]-1) ne récupère pas la donnée voulue mais ça se voit pas)
        MatchScore matchScore = new MatchScore(teamsScores.get(teamsNumbers[0]-1), teamsScores.get(teamsNumbers[1]-1));
        matchesScores.add(matchScore);
        TeamScore lastWinner = teamsScores.get(teamsScores.indexOf(matchScore.getWinner()));
        TeamScore lastLooser = teamsScores.get(teamsScores.indexOf(matchScore.getLooser()));
        lastWinner.addVictory();
        lastWinner.addWin();
        lastLooser.addLoss();
        lastLooser.removeWin();
        return lastWinner;
    }

    /** 
     * @return List<MatchScore> : A list containing all matches that have been played
     */
    public List<MatchScore> getMatchesScores() {
        return matchesScores;
    }

    /** 
     * @return List<TeamScore> : A list containing all teams with their cumulative score
     */
    public List<TeamScore> getTeamsScores(){
        return teamsScores;
    }
}
