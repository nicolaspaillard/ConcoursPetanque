package concourspetanque.views;

import concourspetanque.controllers.MatchesController;
import concourspetanque.controllers.PlayersController;
import concourspetanque.controllers.TeamsController;
import concourspetanque.models.Match;

import java.util.List;

public class ConsoleUI {
    public void start() {
        PlayersController playersController = new PlayersController();

        TeamsController teamsController = new TeamsController(playersController.getPlayers());

        MatchesController matchesController = new MatchesController(teamsController.getTeams());

        List<Match> matches = matchesController.getMatches();

        matches.forEach(m -> System.out.println(m));// à mettre en forme
    }
}
