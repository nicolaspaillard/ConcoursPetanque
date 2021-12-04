package concourspetanque.services.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import concourspetanque.models.Match;
import concourspetanque.models.Round;
import concourspetanque.models.Team;
import concourspetanque.models.rounds.ILeagueRounds;
import concourspetanque.models.rounds.implementations.EightTeamsRounds;
import concourspetanque.models.rounds.implementations.SixTeamsRounds;
import concourspetanque.models.rounds.implementations.TenTeamsRounds;
import concourspetanque.models.rounds.implementations.TwelveTeamsRounds;

public class League extends AbstractGame {
    private ILeagueRounds roundsSetup;
    private List<Round> rounds;

    public League() {
        super();
        this.minPlayers = 12;
        this.maxPlayers = 36;
        this.allowedNumberOfTeams = new ArrayList<>(Arrays.asList(6, 8, 10, 12));
    }
    
    @Override
    public void startCompetition() {
        getRoundsStrategy(this.teamsController.getTeams().size());
        play4Rounds();
        updateTeams();
    }

    private void getRoundsStrategy(int teamsCount) {
        switch (teamsCount) {
            case 6:
                this.roundsSetup = new SixTeamsRounds();
                break;
            case 8:
                this.roundsSetup = new EightTeamsRounds();
                break;
            case 10:
                this.roundsSetup = new TenTeamsRounds();
                break;
            case 12:
                this.roundsSetup = new TwelveTeamsRounds();
        }
        this.rounds = this.roundsSetup.getRounds();
    }

    private void play4Rounds() {
        // Jouer les 4 rounds
        for (int i = 0 ; i < 4 ; i++) {
            playRound(rounds.get(i));
        }
    }

    private void playRound(Round round) {
        // Jouer les différents matchs du round
        for (int i = 0 ; i < round.getMatchesCount() ; i++) {
            int team1Id = round.getOpponentsIDs(i)[0] - 1;
            int team2Id = round.getOpponentsIDs(i)[1] - 1;
            // System.out.println(team1Id);
            // System.out.println(team2Id);
            Team team1 = teamsController.getTeam(team1Id);
            Team team2 = teamsController.getTeam(team2Id);
            this.matchController.playMatch(team1, team2);
        }
    }

    /**
     * Pour chaque match joué, ajoute les informations (Matchs joués, scores, victoires, 
     * goal average) aux Teams pour faciliter l'affichage des informations finales.
     */
    @Override
    public void updateTeams() {
        for(Match match : this.matchController.getMatchs()) {
            this.teamsController.updateTeamsMatchs(match);
            this.teamsController.updateTeamsScores(match);
            this.teamsController.updateTeamsVictories(match);
            this.teamsController.updateTeamsGoalAverage();
        }
    }
    
}
