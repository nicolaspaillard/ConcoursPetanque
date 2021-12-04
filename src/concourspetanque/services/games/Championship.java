package concourspetanque.services.games;

import java.util.ArrayList;
import java.util.Arrays;

public class Championship extends AbstractGame {


    public Championship() {
        super();
        this.minPlayers = 8;
        this.maxPlayers = 48;
        this.allowedNumberOfTeams = new ArrayList<>(Arrays.asList(4, 8, 16));
    }

    @Override
    public void startCompetition() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateTeams() {
        // TODO Auto-generated method stub
        
    }
    
}
