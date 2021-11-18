package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import concourspetanque.models.Match;
import concourspetanque.models.Team;
import concourspetanque.models.interfaces.RoundsInterface;
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
    // public void setMatchs(List<Match> matchs) {
    //     this.matchs = matchs;
    // }

    public void printMatchs() {
        for(Match match : this.matchs) {
            System.out.println(match);
        }
    }  
    
    public List<Match> playMatchs(List<Team> teams) {
        RoundsInterface roundsSetup = getTeamsConfrontationSetup(teams.size());
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


    private RoundsInterface getTeamsConfrontationSetup(int size) {
        // Récupérer le setup des match (selon le nombre d'équipes)
        RoundsInterface roundsSetup;
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

    private Map<String,int[]> getRoundOpponents(int round, RoundsInterface roundsSetup) {
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
