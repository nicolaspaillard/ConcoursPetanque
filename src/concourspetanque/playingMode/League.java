package concourspetanque.playingMode;

import concourspetanque.Player;
import concourspetanque.Team;
import concourspetanque.Tools.InvalidNumberException;
import concourspetanque.Tools.NameGenerator;
import concourspetanque.Tools.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class League {
    /**
     * Main method for executing different steps of the program
     */
    public void start() {
        List<Player> players = playersRegister();
        printRegisteredPlayers(players);

    }

    // Main steps

    private static List<Player> playersRegister() {
        // Generate a random number of players
        int numberOfPlayers = Tools.GenerateNumberBetween(12,36);
        List<Player> players = new ArrayList<>();
        for (int i = 0 ; i < numberOfPlayers ; i++) {
            Player newPlayer = generatePlayer();
            players.add(newPlayer);
        }
        return players;
    }
    private static Player generatePlayer(){
        Player p = new Player(NameGenerator.GenerateName(), NameGenerator.GenerateName(), Tools.GenerateNumberBetween(18, 99));
        return p;
    }
    //#region TOOLS
    private void printRegisteredPlayers(List<Player> players) {
        for(Player player : players) {
            System.out.println(player);
        }
    }
    public static int GenerateNumberBetween(int min, int max) {
        Random r = new Random();
        int result = r.nextInt(max-min) + min;
        return result;
    }
    public static List<Team> GenerateTeams(List<Player> players) throws Exception{
        if(players.size()<12 || players.size()>36){
            throw new InvalidNumberException("Erreur : nombre de joueurs insuffisant");
        }else{
            List<Team> teams = new ArrayList<Team>();
            if(players.size()<16){//12 - 15 joueurs -> 6 teams
                teams = AddPlayers(players, 6);
            }else if(players.size()<20){//16 - 19 joueurs -> 8 teams
                teams = AddPlayers(players, 8);
            }else if(players.size()<24){//20 - 23 joueurs -> 10 teams
                teams = AddPlayers(players, 10);
            }else if(players.size()>=24){//24 - 36 joueurs -> 12 teams
                teams = AddPlayers(players, 12);                
            }
           return teams;
        }        
    }
    public static List<Team> AddPlayers(List<Player> players, int teamsCount){
        List<Team> teams = new ArrayList<Team>();
        for (int i = 0; i < teamsCount; i++) {//ajoute 2 joueurs a chaque équipe une par une 
            List<Player> team = new ArrayList<Player>();
            for (int j = 0; j < 2; j++) {//sélectionne 2 joueurs
                Player p = players.get(GenerateNumberBetween(0, players.size()));//récupère un joueur au hasard
                players.remove(p);//retire le joueur de la liste
                team.add(p);//ajoute le joueur a l'equipe
            }
            teams.add(new Team(team));//ajoute l'équipe à la liste d'équipes
        }
        if(players.size()>0) teams = AddRemainingPlayers(players, teams);//si il reste des joueurs, les ajoute a des teams
        return teams;
    }
    public static List<Team> AddRemainingPlayers(List<Player> remainingPlayers, List<Team> teams){

        int teamNumber = GenerateNumberBetween(0, teams.size());//récupère une 1ere team au hasard
        List<Integer> prevTeamsNumber = new ArrayList<Integer>();//voir plus bas
        for (int i = 0; i < remainingPlayers.size(); i++) {//récupère les joueurs un par un et les ajoute à une team
            while (prevTeamsNumber.contains(teamNumber)) {//si l'équipe a deja reçu un joueur supplémentaire
                teamNumber = GenerateNumberBetween(0, teams.size());//récupère une team au hasard
            }        
            Player p = remainingPlayers.get(0);//récupère le 1er joueur de la liste
            remainingPlayers.remove(p);//retire le joueur de la liste
            teams.get(teamNumber).addPlayer(p);//ajoute le joueur à l'équipe
            prevTeamsNumber.add(teamNumber);//ajoute l'équipe aux équipes déjà selectionnées
        }
        return teams;
    }
    //#endregion
}
