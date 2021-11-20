package concourspetanque.views;

import concourspetanque.controllers.MatchesController;
import concourspetanque.controllers.PlayersController;
import concourspetanque.controllers.TeamsController;
import concourspetanque.models.Match;
import concourspetanque.models.Team;
import concourspetanque.models.TeamScore;

import java.util.List;

public class ConsoleUI {
    public void start() {
        PlayersController playersController = new PlayersController();

        TeamsController teamsController = new TeamsController(playersController.getPlayers());

        MatchesController matchesController = new MatchesController(teamsController.getTeams());

        List<Match> matches = matchesController.getMatches();
        List<TeamScore> teamsScores = matchesController.getTeamsScores();

        int i = 1;
        for (Match m : matches) {
            System.out.println("Match " + i + " \tEquipe " + m.getTeam1().getId() + " : " + m.getScore1() + " Points\tEquipe " + m.getTeam2().getId() + " : " + m.getScore2() + " Points\tVainqueur : Equipe " + m.getWinner().getId());
            i++;
        }            
        teamsScores.forEach(ts -> System.out.println("Equipe " + ts.getId() + "\tVictoires : " + ts.getVictories() + " - Défaites : " + ts.getLoses()));
    }
}
