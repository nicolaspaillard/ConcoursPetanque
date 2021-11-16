package concourspetanque.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import concourspetanque.Player;
import concourspetanque.Team;

public class Tools {
    public static int random_int(int Min, int Max) {
        int result = (int) (Math.random()*(Max-Min))+Min;
        return result;
    }
    
    public static List<Team> GenerateTeams(List<Player> players) throws Exception{
        List<Team> teams = new ArrayList<Team>();
        if(players.size()<12 || players.size()>36){
            throw new InvalidNumberException("Erreur : nombre de joueurs insuffisant");
        }else{
            if(players.size()<16){//12 - 15 -> 6 teams
                List<Player> team = new ArrayList<Player>();
                for (int i = 0; i < 3; i++) {
                    Player p = players.get(GenerateNumberBetween(0, players.size()));
                    if (!players.remove(p)) {
                        throw new Exception("il existe pas ce joueur wsh.. bon de toute façon normalement ça va jamais throw");
                    }
                    team.add(p);
                }
                teams.add(new Team(team));
            }else if(players.size()<20){//16 - 19 -> 8 teams

            }else if(players.size()<24){//20 - 23 -> 10 teams

            }else if(players.size()>=24){//24 - 36 -> 12 teams

            }
           return teams;
        }        
    }
    public static int GenerateNumberBetween(int low, int high){
        Random r = new Random();
        int result = r.nextInt(high-low) + low;
        return result;
    }
}

// 
// 
// 
// 
// if (players.size() % 2 == 0) {
//     //
// }