package concourspetanque.Tools;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.Player;
import concourspetanque.Team;

public class Tools {
    
    public static List<Team> GenerateTeams(List<Player> players) throws InvalidNumberException{
        if(players.size()<12 || players.size()>36){
            throw new InvalidNumberException("Erreur : nombre de joueurs insuffisant");
        }else{
            if(players.size()<16){//6 teams

            }else if(players.size()<20){//8 teams

            }else if(players.size()<24){//10 teams

            }else if(players.size()>=24){//12 teams

            }
           return new ArrayList<Team>();
        }        
    }
}

// 12 - 15 -> 6
// 16 - 19 -> 8
// 20 - 23 -> 10
// 24 - 36 -> 12
// if (players.size() % 2 == 0) {
//     //
// }