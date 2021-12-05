package concourspetanque.services.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import concourspetanque.models.Round;
import concourspetanque.models.Team;
import concourspetanque.utils.RandomGenerators;

public class Championship extends AbstractGame {

    public Championship() {
        super();
        // this.minPlayers = 8;
        // this.maxPlayers = 48;
        this.allowedNumberOfTeams = new ArrayList<>(Arrays.asList(4, 8, 16));
    }

    @Override
    public void generatePlayers() {
        switch (RandomGenerators.generateNumberBetween(0, 2)) {
            case 0: // 4 teams
                this.playersController.generatePlayers(8, 12);
                break;
            case 1: // 8 teams
                this.playersController.generatePlayers(16, 24);
                break;
            case 2: // 16 teams
                this.playersController.generatePlayers(32, 48);
                break;
        }
    }

    @Override
    public void startCompetition() {
        System.out.println("Championship : inside startCompetition");
        List<Team> tempTeams = new ArrayList<Team>(this.teamsController.getTeams());
        int roundNumber = 0;
        while (tempTeams.size() > 1) {
            System.out.println("Championship : while tempTeams > 1");
            tempTeams = playRound(getRound(tempTeams, roundNumber));
            roundNumber++;
        }
    }

    private Round getRound(List<Team> tempTeams, int roundNumber) {
        System.out.println("Championship : inside startCompetition");
        List<int[]> matches = new ArrayList<int[]>();
        // Loops while there are teams to match in the tempTeams list 
        while(tempTeams.size()>0) {
            int[] match = {0,0};// Must init ?
            for (int opponentIndex = 0; opponentIndex < 2; opponentIndex++) {
                int opponentId = RandomGenerators.generateNumberBetween(0, tempTeams.size()-1);
                match[opponentIndex] = tempTeams.get(opponentId).getId();
                tempTeams.remove(opponentId);
            }
            matches.add(match);
        }
        return new Round(matches, roundNumber);
    } 

    private List<Team> playRound(Round round){
        System.out.println("Championship : inside playRound");
        List<Team> winners = new ArrayList<Team>();
        for (int matchNumber = 0; matchNumber<round.getMatchesCount(); matchNumber++) {
            winners.add(getMatchWinner(round.getOpponentsIds(matchNumber), round.getRoundNumber()));            
        }
        return winners;
    } 

    private Team getMatchWinner(int[] teamIds, int roundNumber) {
        return matchController.playMatch(teamsController.getTeam(teamIds[0]), teamsController.getTeam(teamIds[1]), roundNumber).getWinner();
    }

    @Override
    public void updateTeams() {
        
    }
    
}
