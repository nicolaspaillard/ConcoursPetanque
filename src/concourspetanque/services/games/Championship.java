package concourspetanque.services.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import concourspetanque.models.Round;
import concourspetanque.models.Team;
import concourspetanque.utils.RandomGenerators;

public class Championship extends AbstractGame {


    public Championship() {
        super();
        this.minPlayers = 8;
        this.maxPlayers = 48;
        this.allowedNumberOfTeams = new ArrayList<>(Arrays.asList(4, 8, 16));
    }

    @Override
    public void startCompetition() {
        List<Team> tempTeams = new ArrayList<Team>(this.teamsController.getTeams());
        int roundNumber = 0;
        while (tempTeams.size() > 1) {
            tempTeams = playRound(getRound(tempTeams, roundNumber));
            roundNumber++;
        }
    }

    private Round getRound(List<Team> tempTeams, int roundNumber){
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
        return new Round(ret, roundNumber);
    } 

    private List<Team> playRound(Round round){
        List<Team> teams = new ArrayList<Team>();
        for (int j = 0; j< round.getMatchesCount(); j++) {
            int[] teamsIDs = round.getOpponentsIds(j);
            teams.add(getMatchWinner(teamsIDs[0], teamsIDs[1], round.getRoundNumber()));            
        }
        return teams;
    } 

    private Team getMatchWinner(int teamID1, int teamID2, int roundNumber) {
        return matchController.playMatch(teamsController.getTeam(teamID1), teamsController.getTeam(teamID2), roundNumber).getWinner();
    }

    @Override
    public void updateTeams() {
        
    }
    
}
