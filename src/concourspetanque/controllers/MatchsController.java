package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import concourspetanque.controllers.tools.Utils;
import concourspetanque.models.Match;
import concourspetanque.models.Team;
import concourspetanque.models.interfaces.LeagueRoundsInterface;
import concourspetanque.models.leagueRounds.EightTeams;
import concourspetanque.models.leagueRounds.SixTeams;
import concourspetanque.models.leagueRounds.TenTeams;
import concourspetanque.models.leagueRounds.TwelveTeams;

public class MatchsController {
    List<Match> matchs;

    public MatchsController(List<Team> teams) {
        this.matchs = playMatchs(teams);
    }

    public List<Match> getMatchs() {
        return matchs;
    }

    public void printMatchs(int teamsSize) {
        Utils.printLine(40);
        System.out.println("DEROULEMENT DES MATCHS");
        int matchsPerRound = teamsSize / 2;
        int round = 1;
        for(int i = 0 ; i < this.matchs.size() ; i++) {
            if (i % matchsPerRound == 0) {
                System.out.println("\nPARTIE " + round + "\n");
                round++;
            }
            System.out.printf("%-8s", "Team" + (matchs.get(i).getOpponent1().getId() + 1));
            System.out.printf("%-3s", matchs.get(i).getOpponent1score());
            System.out.print(" - ");
            System.out.printf("%3s", matchs.get(i).getOpponent2score());
            System.out.printf("%8s", "Team" + (matchs.get(i).getOpponent2().getId() + 1));
//            System.out.printf("%6s", "==> ");
//            System.out.printf("%-25s", "Winner : Team" + (matchs.get(i).getWinner().getId() + 1));
            System.out.println("");
        }
    }  
    
    public List<Match> playMatchs(List<Team> teams) {
        LeagueRoundsInterface roundsSetup = getTeamsConfrontationSetup(teams.size());
        // Jouer les 4 rounds
        List<Match> matchs = new ArrayList<>();
        for (int i = 0 ; i < 4 ; i++) {
            Map<String, int[]> roundOpponents = getRoundOpponents(i, roundsSetup);
            List<Match> roundMatchs = playRound(roundOpponents, teams);
            matchs.addAll(roundMatchs);
        }
        return matchs;
    }

    private List<Match> playRound(Map<String,int[]> roundOpponents, List<Team> teams) {
        List<Match> matchs = new ArrayList<>();
        // Jouer les différents matchs du round
        for (int i = 0 ; i < roundOpponents.size() ; i++) {
            String matchKey = String.valueOf(i + 1);
            Match match = playMatch(roundOpponents, matchKey, teams);
            matchs.add(match);
        }
        return matchs;
    }

    private Match playMatch(Map<String,int[]> roundOpponents, String matchKey, List<Team> teams) {
        // Récupérer les index des opposants dans la map
        int[] opponents = roundOpponents.get(matchKey);
        // On retire 1 à l'index (= opponents[0] - 1) car List<teams> commence à 0,
        // et les setup font commencer l'index à 1...
        int teamOneIndex = opponents[0] - 1;
        int teamTwoIndex = opponents[1] - 1;
        // Jouer le match
        Match match = new Match(teams.get(teamOneIndex), teams.get(teamTwoIndex));
        return match;
    }

    private LeagueRoundsInterface getTeamsConfrontationSetup(int size) {
        // Récupérer le setup des match (selon le nombre d'équipes)
        LeagueRoundsInterface roundsSetup;
        switch (size) {
            case 6:
                roundsSetup = new SixTeams();
                break;
            case 8:
                roundsSetup = new EightTeams();
                break;
            case 10:
                roundsSetup = new TenTeams();
                break;
            default:
                roundsSetup = new TwelveTeams();
        }
        return roundsSetup;
    }

    private Map<String,int[]> getRoundOpponents(int round, LeagueRoundsInterface roundsSetup) {
        // Récupérer la Map correspondant aux opposants du round
        Map<String, int[]> opponentsMap;
        switch (round) {
            case 0:
                opponentsMap = roundsSetup.roundOne();
                break;
            case 1:
                opponentsMap = roundsSetup.roundTwo();
                break;
            case 2:
                opponentsMap = roundsSetup.roundThree();
                break;
            default:
                opponentsMap = roundsSetup.roundFour();
        }
        return opponentsMap;
    }
}
