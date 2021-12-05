package concourspetanque.services.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import concourspetanque.models.Match;
import concourspetanque.models.Round;
import concourspetanque.models.Team;
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
    public void startCompetition() {
        int teamsCount = this.teamsController.getTeams().size();
        this.rounds = RoundsStrategy.getRounds(teamsCount);
        // getRounds(this.teamsController.getTeams().size());
        play4Rounds();
        updateTeams();
    }

    // private void getRounds(int teamsCount) {
    //     ILeagueRounds roundsSetup = null;
    //     switch (teamsCount) {
    //         case 6:
    //             roundsSetup = new SixTeamsRounds();
    //             break;
    //         case 8:
    //             roundsSetup = new EightTeamsRounds();
    //             break;
    //         case 10:
    //             roundsSetup = new TenTeamsRounds();
    //             break;
    //         case 12:
    //             roundsSetup = new TwelveTeamsRounds();
    //     }
    //     this.rounds = roundsSetup.getRounds();
    // }

    private void play4Rounds() {
        // Jouer les 4 rounds
        for (int i = 0 ; i < 4 ; i++) {
            playRound(rounds.get(i));
        }
    }

    private void playRound(Round round) {
        // Jouer les différents matchs du round
        for (int i = 0 ; i < round.getMatchesCount() ; i++) {
            int team1Id = round.getOpponentsIds(i)[0] - 1;
            int team2Id = round.getOpponentsIds(i)[1] - 1;
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
            this.teamsController.updateTeamsMatches(match);
            this.teamsController.updateTeamsScores(match);
            this.teamsController.updateTeamsVictories(match);
            this.teamsController.updateTeamsGoalAverage();
        }
    }
    
}
