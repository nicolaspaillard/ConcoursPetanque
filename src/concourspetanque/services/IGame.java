package concourspetanque.services;

import concourspetanque.controllers.MatchController;
import concourspetanque.controllers.PlayersController;
import concourspetanque.controllers.TeamsController;

public interface IGame {
    void play();
    void generatePlayers();
    void buildTeams();
    void startCompetition();
    TeamsController getTeamsController();
    PlayersController getPlayersController();
    MatchController getMatchController();
}
