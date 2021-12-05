package concourspetanque.services.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Championship extends AbstractGame {


    public Championship() {
        super();
        this.minPlayers = 8;
        this.maxPlayers = 48;
        this.allowedNumberOfTeams = new ArrayList<>(Arrays.asList(4, 8, 16));
    }

    @Override
    public void startCompetition() {
        List<Team> teams = new ArrayList<Team>(this.teamsController.getTeams());
        int roundNumber = 0;
        while (teams.size() > 1) {
            teams = playRounds(Arrays.asList(getRound(teams)), roundNumber);
            roundNumber++;
        }
    }

    @Override
    public void updateTeams() {
        // TODO Auto-generated method stub
        
    }
    
}
