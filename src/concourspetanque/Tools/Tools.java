package concourspetanque.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import concourspetanque.Player;
import concourspetanque.Team;

public class Tools {
    public static int GenerateNumberBetween(int min, int max) {
        Random r = new Random();
        int result = r.nextInt(max-min) + min;
        return result;
    }
    public static List<Team> GenerateTeams(List<Player> players) throws Exception{
        List<Team> teams = new ArrayList<Team>();
        if(players.size()<12 || players.size()>36){
            throw new InvalidNumberException("Erreur : nombre de joueurs insuffisant");
        }else{
            if(players.size()<16){//12 - 15 -> 6 teams
                teams = AddPlayers(players, 6);
                if(players.size()>0) teams = AddRemainingPlayers(players, teams);
            }else if(players.size()<20){//16 - 19 -> 8 teams
                teams = AddPlayers(players, 8);
                if(players.size()>0) teams = AddRemainingPlayers(players, teams);
            }else if(players.size()<24){//20 - 23 -> 10 teams
                teams = AddPlayers(players, 10);
                if(players.size()>0) teams = AddRemainingPlayers(players, teams);
            }else if(players.size()>=24){//24 - 36 -> 12 teams
                teams = AddPlayers(players, 12);
                if(players.size()>0) teams = AddRemainingPlayers(players, teams);
            }
           return teams;
        }        
    }
    public static List<Team> AddPlayers(List<Player> players, int teamsCount){
        List<Team> teams = new ArrayList<Team>();
        for (int i = 0; i < 6; i++) {
            List<Player> team = new ArrayList<Player>();
            for (int j = 0; j < 2; j++) {
                Player p = players.get(GenerateNumberBetween(0, players.size()));
                players.remove(p);
                team.add(p);
            }
            teams.add(new Team(team));
        }
        return teams;
    }
    public static List<Team> AddRemainingPlayers(List<Player> remainingPlayers, List<Team> teams){
        int teamNumber = GenerateNumberBetween(0, teams.size()); ; 
        List<Integer> prevTeamsNumber = new ArrayList<Integer>();
        for (int i = 0; i < remainingPlayers.size(); i++) {
            while (prevTeamsNumber.contains(teamNumber)) {
                teamNumber = GenerateNumberBetween(0, teams.size());
            }        
            Player p = remainingPlayers.get(0);
            remainingPlayers.remove(p);
            teams.get(teamNumber).addPlayer(p);
            prevTeamsNumber.add(teamNumber);  
        }
        return teams;
    }
}