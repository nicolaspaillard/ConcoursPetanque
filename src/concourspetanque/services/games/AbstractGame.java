package concourspetanque.services.games;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.controllers.MatchController;
import concourspetanque.controllers.PlayersController;
import concourspetanque.controllers.TeamsController;
import concourspetanque.models.Match;
import concourspetanque.models.Round;
import concourspetanque.models.Team;
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
        this.generatePlayers();
        this.buildTeams();
        this.startCompetition();
    }

    public abstract void generatePlayers();

    /**
     * Contruit les équipes à partir de la liste de joueurs générés
     */
    @Override
    public void buildTeams() {
        this.teamsController.buildTeams(this.playersController.getPlayers(), this.allowedNumberOfTeams);
    }

    public abstract void startCompetition();
    
    /**
     * Reçoit un objet Round, récupère les équipes grâce à leurs identifiants et joue le Match.
     * Renvoie la liste des gagnants qui est utilisée en mode championat pour déterminer les équipes restantes.
     * 
     * @param round
     * @return Liste des gagnants
     */
    protected List<Team> playRound(Round round){
        List<Team> winners = new ArrayList<>();
        for (int matchNumber=0 ; matchNumber<round.getMatchesCount() ; matchNumber++) {
            int[] opponentsIds = getOpponents(round, matchNumber);
            Team team1 = teamsController.getTeam(opponentsIds[0]);
            Team team2 = teamsController.getTeam(opponentsIds[1]);
            Match match = matchController.playMatch(team1, team2, round.getRoundNumber());
            winners.add(match.getWinner());           
        }
        return winners;
    }

    /**
     * Renvoie un tableau avec les identifiants des opposants d'un match dans un round.
     * Cette méthode est surchargée en mode League pour récupérer correctement les équipes à partir des 
     * différentes implémentations de ILeagueRounds.
     * 
     * @param round
     * @param matchNumber
     * @return Les opposants d'un match
     */
    protected int[] getOpponents(Round round, int matchNumber) {
        return round.getOpponentsIds(matchNumber);
    }

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
