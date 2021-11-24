package concourspetanque.views;

import java.util.List;

import concourspetanque.controllers.GameController;
import concourspetanque.controllers.ScoresController;
import concourspetanque.models.GameMode;
import concourspetanque.models.MatchScore;
import concourspetanque.models.TeamScore;

public class ConsoleUI {
    public void start() {
        
        GameController gameController = new GameController(GameMode.CHAMPIONSHIP);
        ScoresController scoresController = gameController.getScores();
        List<MatchScore> matchScores = scoresController.getMatchesScores();
        List<TeamScore> teamScores = scoresController.getTeamsScores();
    }
}
