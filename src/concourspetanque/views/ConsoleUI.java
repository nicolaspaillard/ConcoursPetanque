package concourspetanque.views;

import concourspetanque.controllers.GameController;
import concourspetanque.models.MatchScore;
import concourspetanque.models.TeamScore;
import concourspetanque.models.GameMode;

import java.util.List;

public class ConsoleUI {
    public void start() {
        GameController gameController = new GameController(GameMode.CHAMPIONSHIP);
        
        List<MatchScore> matches = gameController.getMatchesScores();
        List<TeamScore> teamsScores = gameController.getTeamsScores();

        int i = 1;
        for (MatchScore m : matches) {
            System.out.println("Match " + i + " \tEquipe " + m.getTeam1().getId() + " : " + m.getScore1() + " Points\tEquipe " + m.getTeam2().getId() + " : " + m.getScore2() + " Points\tVainqueur : Equipe " + m.getWinner().getId());
            i++;
        }            
        teamsScores.forEach(ts -> System.out.println("Equipe " + ts.getId() + "\tVictoires : " + ts.getVictories() + " - Défaites : " + ts.getLoses()));
        teamsScores.forEach(ts -> System.out.println("Equipe " + ts.getId() + "\tScore : " + ts.getScore()));
    }
}
