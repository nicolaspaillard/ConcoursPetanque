package concourspetanque.Tools;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.Player;
import concourspetanque.Team;
import concourspetanque.Tools.InvalidNumberException;

public class Tools {
    
    public static List<Team> GenerateTeams(List<Player> players){
        if(players.size()<12){
            throw new InvalidNumberException("Erreur : nombre de joueurs insuffisant");
        }else{

        }
        for (int i = 12; i < 36; i+=4) {
            if(players.size()<i){

            }
        }
        if (players.size() % 2 == 0) {
            //
        }
        return new ArrayList<Team>();

    }
}

// 12 - 15 -> 6
// 16 - 19 -> 8
// 20 - 23 -> 10
// 24 - 36 -> 12