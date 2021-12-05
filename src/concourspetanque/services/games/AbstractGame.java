package concourspetanque.services.games;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.controllers.MatchController;
import concourspetanque.controllers.PlayersController;
import concourspetanque.controllers.TeamsController;
import concourspetanque.models.Player;
import concourspetanque.services.IGame;

public abstract class AbstractGame implements IGame {
    // private ScoresController scoresController;
    protected int minPlayers;
    protected int maxPlayers;
    protected List<Integer> allowedNumberOfTeams;
    protected TeamsController teamsController;
    protected PlayersController playersController;
    protected MatchController matchController;
    
    public AbstractGame () {
        this.playersController = new PlayersController();
        this.teamsController = new TeamsController();
        this.matchController = new MatchController();
    }

    @Override
    public void play() {
        System.out.println("Inside Play");
        this.generatePlayers();
        this.buildTeams();
        this.startCompetition();
    }

    @Override
    public void generatePlayers() {
        System.out.println("Inside generatePlayers");
        this.playersController.generatePlayers(minPlayers, maxPlayers);
    }

    @Override
    public void buildTeams() {
        // for (int i = minPlayers ; i <= maxPlayers ; i++) {
        //     int t = this.teamsController.computeTeamsCount(i, allowedNumberOfTeams);
        //     System.out.println("nb joueurs : " + i + " nb teams : " + t);
        // }
        System.out.println("Inside buildTeams");
        List<Player> players = new ArrayList<>();
        players.addAll(this.playersController.getPlayers());
        this.teamsController.buildTeams(players, this.allowedNumberOfTeams);

    }

    @Override
    public abstract void startCompetition();

    @Override
    public abstract void updateTeams();

    // Controllers Getters

    public TeamsController getTeamsController() {
        return teamsController;
    }

    public PlayersController getPlayersController() {
        return playersController;
    }

    public MatchController getMatchController() {
        return matchController;
    }
    
}
