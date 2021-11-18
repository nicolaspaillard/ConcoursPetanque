package concourspetanque.playingMode;

import concourspetanque.models.Match;
import concourspetanque.models.Player;
import concourspetanque.models.Team;
import concourspetanque.Tools.RandomGenerators;
import concourspetanque.leagueMatchSetup.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class League {
    List<Player> players;
    List<Team> teams;
    List<Match> matchs;

    /**
     * Main method for executing different steps of the program
     */
    public void start() {
        // Générer les joueurs
        this.players = generatePlayers();
        printPlayers(this.players);
        System.out.println("\nNombre de joueurs inscrits : " + this.players.size());
        // Générer les équipes
        this.teams = generateTeams(this.players);
        this.teams.forEach(t -> System.out.println(t));
        // Jouer les matchs
        this.matchs = playMatchs(this.teams);
        this.matchs.forEach(m -> System.out.println(m));
    }
    //#region TOOLS
    public int GenerateNumberBetween(int min, int max) {
        Random r = new Random();
        return r.nextInt(max-min) + min;
    }
    //#endregion
    //#region PLAYERS
    private List<Player> generatePlayers() {
        // Generate a random number of players
        int numberOfPlayers = GenerateNumberBetween(12,36);
        List<Player> players = new ArrayList<>();
        for (int i = 0 ; i < numberOfPlayers ; i++) {
            Player newPlayer = new Player(
                    RandomGenerators.generateName(),
                    RandomGenerators.generateName(),
                    RandomGenerators.generateNumberBetween(18, 99),
                    i);
            players.add(newPlayer);
        }
        return players;
    }
    private void printPlayers(List<Player> players) {
        for(Player player : players) {
            System.out.println(player);
        }
    }
    //#endregion
    //#region TEAMS
    private List<Team> generateTeams(List<Player> players){
        List<Team> teams = new ArrayList<>();
        if(players.size()<16){
            //12 - 15 joueurs -> 6 teams
            teams = AddPlayers(players, 6);
        }else if(players.size()<20){
            //16 - 19 joueurs -> 8 teams
            teams = AddPlayers(players, 8);
        }else if(players.size()<24){
            //20 - 23 joueurs -> 10 teams
            teams = AddPlayers(players, 10);
        }else {
            //24 joueurs ou plus -> 12 teams
            teams = AddPlayers(players, 12);                
        }
        return teams;       
    }
    public List<Team> AddPlayers(List<Player> players, int teamsCount){
        List<Team> teams = new ArrayList<>();
        // Constitue les équipes avec 2 joueurs aléatoires
        for (int i = 0; i < teamsCount; i++) {
            List<Player> team = selectTeamPlayers(players);
            team.forEach(p -> players.remove(p)); // Retire les deux joueurs sélectionnés de la liste
            teams.add(new Team(team, i));
        }
        // S'il reste des joueurs, les ajoute a des équipes aléatoires
        if (players.size()>0) {
            teams = AddRemainingPlayers(players, teams);
        }
        return teams;
    }

    private List<Player> selectTeamPlayers(List<Player> players) {
        List<Player> team = new ArrayList<>();
        // Sélectionne 2 joueurs aléatoires et les ajoute à l'équipe
        for (int i = 0; i < 2; i++) {
            Player p = players.get(GenerateNumberBetween(0, players.size()));
            team.add(p);
            players.remove(p); // retire le joueur sélectionné de la liste locale
        }
        return team;
    }

    public List<Team> AddRemainingPlayers(List<Player> remainingPlayers, List<Team> teams){
        // Tant qu'il reste des joueurs, boucler pour les caser dans des équipes aléatoires de 2 joueurs
        while (remainingPlayers.size() > 0) {
            int randomTeam = GenerateNumberBetween(0, teams.size());
            int teamSize = teams.get(randomTeam).getPlayers().size();
            if (teamSize > 2) {
                continue;
            }
            teams.get(randomTeam).addPlayer(remainingPlayers.get(0));
            remainingPlayers.remove(remainingPlayers.get(0));
        }
//        int teamNumber = GenerateNumberBetween(0, teams.size());//récupère une 1ere team au hasard
//        List<Integer> prevTeamsNumber = new ArrayList<>();//voir plus bas
//        int playersCount = remainingPlayers.size();
//        for (int i = 0; i < playersCount; i++) {
//            while (prevTeamsNumber.contains(teamNumber)) {//si l'équipe a deja reçu un joueur supplémentaire
//                teamNumber = GenerateNumberBetween(0, teams.size());//récupère une team au hasard
//            }
//            Player p = remainingPlayers.get(0);//récupère le 1er joueur de la liste
//            remainingPlayers.remove(p);//retire le joueur de la liste
//            teams.get(teamNumber).addPlayer(p);//ajoute le joueur à l'équipe
//            prevTeamsNumber.add(teamNumber);//ajoute l'équipe aux équipes déjà selectionnées
//        }
        return teams;
    }
    //#endregion
    //#region MATCHS
    public List<Match> playMatchs(List<Team> teams) {
        RoundsSetup roundsSetup = getTeamsConfrontationSetup(teams.size());
        // Jouer les 4 rounds
        List<Match> matchs = new ArrayList<>();
        for (int i = 0 ; i < 4 ; i++) {
            Map<String, int[]> roundOpponents = getRoundOpponents(i, roundsSetup);
            List<Match> roundMatchs = playRound(roundOpponents);
            matchs.addAll(roundMatchs);
        }
        return matchs;
    }

    private List<Match> playRound(Map<String,int[]> roundOpponents) {
        List<Match> matchs = new ArrayList<>();
        // Jouer les différents matchs du round
        for (int i = 0 ; i < roundOpponents.size() ; i++) {
            String matchKey = String.valueOf(i + 1);
            Match match = playMatch(roundOpponents, matchKey);
            matchs.add(match);
        }
        return matchs;
    }

    private Match playMatch(Map<String,int[]> roundOpponents, String matchKey) {
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


    private RoundsSetup getTeamsConfrontationSetup(int size) {
        // Récupérer le setup des match (selon le nombre d'équipes)
        RoundsSetup roundsSetup;
        switch (size) {
            case 6:
                roundsSetup = new SixTeamsSetup();
                break;
            case 8:
                roundsSetup = new EightTeamsSetup();
                break;
            case 10:
                roundsSetup = new TenTeamsSetup();
                break;
            default:
                roundsSetup = new TwelveTeamsSetup();
        }
        return roundsSetup;
    }

    private Map<String,int[]> getRoundOpponents(int round, RoundsSetup roundsSetup) {
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
    //#endregion
}
