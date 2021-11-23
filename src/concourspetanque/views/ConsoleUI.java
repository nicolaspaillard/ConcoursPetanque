package concourspetanque.views;

import concourspetanque.controllers.GameController;
import concourspetanque.controllers.PlayersController;
import concourspetanque.controllers.TeamsController;
import concourspetanque.models.MatchScore;
import concourspetanque.models.TeamScore;

import java.util.List;

public class ConsoleUI {
    public void start() {
        PlayersController playersController = new PlayersController();

        TeamsController teamsController = new TeamsController(playersController.getPlayers());

        GameController gameController = new GameController(teamsController.getTeams(), 1);

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
