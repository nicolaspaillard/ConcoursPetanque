package concourspetanque.services.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import concourspetanque.models.Match;
import concourspetanque.models.Round;
import concourspetanque.models.rounds.RoundsStrategy;

public class League extends AbstractGame {
    private List<Round> rounds;

    public League() {
        super();
        this.minPlayers = 12;
        this.maxPlayers = 36;
        this.allowedNumberOfTeams = new ArrayList<>(Arrays.asList(6, 8, 10, 12));
    }


    @Override
    public void generatePlayers() {
        this.playersController.generatePlayers(minPlayers, maxPlayers);
    }
    
    @Override
    public void startCompetition() {
        int teamsCount = this.teamsController.getTeams().size();
        this.rounds = RoundsStrategy.getRounds(teamsCount);
        play4Rounds();
        updateTeams();
    }

    private void play4Rounds() {
        // Jouer les 4 rounds
        for (int i = 0 ; i < 4 ; i++) {
            this.playRound(rounds.get(i));
        }
    }

    @Override
    protected int[] getOpponents(Round round, int matchNumber) {
        int[] opponentsIds = {0,0};
        opponentsIds[0] = round.getOpponentsIds(matchNumber)[0] - 1;
        opponentsIds[1] = round.getOpponentsIds(matchNumber)[1] - 1;
        return opponentsIds;
    }

    /**
     * Pour chaque match joué, ajoute les informations (Matchs joués, scores, victoires, 
     * goal average) aux Teams pour faciliter l'affichage des informations finales.
     */
    public void updateTeams() {
        for(Match match : this.matchController.getMatchs()) {
            this.teamsController.updateTeamsMatches(match);
            this.teamsController.updateTeamsScores(match);
            this.teamsController.updateTeamsVictories(match);
            this.teamsController.updateTeamsGoalAverage();
            this.teamsController.updateTeamsRanking();
        }
    }
    
}
