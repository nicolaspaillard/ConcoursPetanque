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
        this.allowedNumberOfTeams = new ArrayList<>(Arrays.asList(4, 8, 16));
    }

    @Override
    public void generatePlayers() {
        switch (RandomGenerators.generateNumberBetween(0, 3)) {
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
        List<Team> teamsRemaining = new ArrayList<Team>(this.teamsController.getTeams());
        int roundNumber = 0;
        while (teamsRemaining.size() > 1) {
            Round round = buildRound(teamsRemaining, roundNumber);
            teamsRemaining = this.playRound(round);
            roundNumber++;
        }
    }

    private Round buildRound(List<Team> roundTeams, int roundNumber) {
        List<int[]> matches = new ArrayList<int[]>();
        // Loops while there are teams in the roundTeams list 
        while(roundTeams.size() > 0) {
            // Build round opponents
            int[] match = {0,0};
            for (int i = 0; i < 2; i++) {
                int opponentId = RandomGenerators.generateNumberBetween(0, roundTeams.size());
                match[i] = roundTeams.get(opponentId).getId();
                roundTeams.remove(opponentId);
            }
            matches.add(match);
        }
        return new Round(matches, roundNumber);
    } 
    
}
