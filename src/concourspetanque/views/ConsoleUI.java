package concourspetanque.views;

import java.util.List;

import concourspetanque.controllers.GameController;
import concourspetanque.controllers.ScoresController;
import concourspetanque.models.GameMode;
import concourspetanque.models.scores.MatchScores;
import concourspetanque.models.scores.RoundScores;
import concourspetanque.models.scores.TeamScore;

public class ConsoleUI {
    public void start() {
        
        GameController gameController = new GameController(GameMode.CHAMPIONSHIP);
        ScoresController scoresController = gameController.getScores();

        List<RoundScores> roundsScores = scoresController.getRoundsScores();
        List<TeamScore> teamScores = scoresController.getTeamsScores();

        printTeams(teamScores);
        printTeamsScores(teamScores);
        printArbo(roundsScores);        
    }
    private void printTeams(List<TeamScore> teamScores) {
        teamScores.forEach(t -> System.out.println("Team "+ (t.getId()+1) + " " + t.getPlayers()));
        System.out.println();
    }
    private void printTeamsScores(List<TeamScore> teamScores) {
        teamScores.forEach(t -> System.out.println(t));
        System.out.println();
    }
    private void printArbo(List<RoundScores> roundsScores) {
        for (RoundScores roundScores : roundsScores) {
            for (MatchScores matchScores : roundScores.getMatchesScores()) {
                System.out.print((matchScores.getWinner().getId()+1)+" "+(matchScores.getLooser().getId()+1)+"\t");
                if(roundScores.getMatchesScores().size()==1){
                    System.out.println();
                    System.out.println((matchScores.getWinner().getId()+1));
                }
            }
            System.out.println();
        }
    }
}
