package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.models.Team;
import concourspetanque.models.scores.MatchScores;
import concourspetanque.models.scores.RoundScores;
import concourspetanque.models.scores.TeamScore;

public class ScoresController {
    private List<RoundScores> roundsScores = new ArrayList<RoundScores>();
    private List<TeamScore> teamsScores = new ArrayList<TeamScore>();
       
    public ScoresController(List<Team> teams) {
        teams.forEach(t -> this.teamsScores.add(new TeamScore(t)));;
    }

    public int addMatch(int teamID1, int teamID2, int roundNumber) {
        MatchScores matchScores = new MatchScores(getTeamScore(teamID1), getTeamScore(teamID2));
        RoundScores roundScores;
        boolean isNewRound = false;

        if(this.roundsScores.size()==roundNumber){
            roundScores = new RoundScores();
            isNewRound = true;
        }else{
            roundScores = this.roundsScores.get(roundNumber);
        }
        roundScores.addMatch(matchScores);
        if(isNewRound)roundsScores.add(roundScores);
        updateTeamsScores(matchScores);
        return matchScores.getWinner().getId();
    }

    private void updateTeamsScores(MatchScores matchScores) {
        this.teamsScores.get(matchScores.getWinner().getId()).addPoints(matchScores.getScore1()>matchScores.getScore2()?matchScores.getScore1():matchScores.getScore2());
        this.teamsScores.get(matchScores.getLooser().getId()).addPoints(matchScores.getScore1()<matchScores.getScore2()?matchScores.getScore1():matchScores.getScore2());
        this.teamsScores.get(matchScores.getWinner().getId()).addVictory();
        this.teamsScores.get(matchScores.getLooser().getId()).addLoss();
        this.teamsScores.get(matchScores.getWinner().getId()).addWin();
        this.teamsScores.get(matchScores.getLooser().getId()).removeWin();
    }
   
    /** 
     * @return List<MatchScore> : A list containing all matches that have been played
     */
    public List<RoundScores> getRoundsScores() {
        return this.roundsScores;
    }

    /** 
     * @return List<TeamScore> : A list containing all teams with their cumulative score
     */
    public List<TeamScore> getTeamsScores(){
        return this.teamsScores;
    }

    /** 
     * @return TeamScore : An object containing a team with it's cumulative score
     */
    private TeamScore getTeamScore(int teamID){
        return this.teamsScores.get(teamID);
    }
}
