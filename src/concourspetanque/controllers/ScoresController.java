package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.models.MatchScore;
import concourspetanque.models.Team;
import concourspetanque.models.TeamScore;

public class ScoresController {
    private List<MatchScore> matchesScores = new ArrayList<MatchScore>();
    private List<TeamScore> teamsScores = new ArrayList<TeamScore>();
       
    public ScoresController(List<Team> teams) {
        teams.forEach(t -> this.teamsScores.add(new TeamScore(t)));;
    }

    public int addMatch(int teamID1, int teamID2) {
        this.matchesScores.add(new MatchScore(getTeamScore(teamID1), getTeamScore(teamID2)));
        addVictory(this.matchesScores.get(this.matchesScores.size()).getWinner().getId());
        addLoss(this.matchesScores.get(this.matchesScores.size()).getLooser().getId());
        addWin(this.matchesScores.get(this.matchesScores.size()).getWinner().getId());
        removeWin(this.matchesScores.get(this.matchesScores.size()).getLooser().getId());
        return this.matchesScores.get(this.matchesScores.size()).getWinner().getId();
    }

    private void addVictory(int teamID) {
        this.teamsScores.get(teamID-1).addVictory();
    }
    private void addLoss(int teamID) {
        this.teamsScores.get(teamID-1).addLoss();
    }
    private void addWin(int teamID) {
        this.teamsScores.get(teamID-1).addWin();
    }
    private void removeWin(int teamID) {
        this.teamsScores.get(teamID-1).removeWin();
    }

    private TeamScore getTeamScore(int teamID){
        return this.teamsScores.get(teamID-1);
    }
    
    /** 
     * @return List<MatchScore> : A list containing all matches that have been played
     */
    public List<MatchScore> getMatchesScores() {
        return this.matchesScores;
    }

    /** 
     * @return List<TeamScore> : A list containing all teams with their cumulative score
     */
    public List<TeamScore> getTeamsScores(){
        return this.teamsScores;
    }
}
