package concourspetanque.controllers;

import java.util.ArrayList;
import java.util.List;

import concourspetanque.controllers.tools.RandomGenerators;
import concourspetanque.models.GameMode;
import concourspetanque.models.Player;
import concourspetanque.models.Team;

public class TeamsController {
    private PlayersController playersController;
    private List<Team> teams= new ArrayList<Team>();   

    public TeamsController(GameMode gameMode) {
        playersController = new PlayersController(gameMode);
        List<Player> players = playersController.getPlayers();

        int[] parameters = (gameMode == GameMode.LEAGUE ? new int[]{16,6,20,8,24,10,48,12} : new int[]{7,2,13,4,25,8,49,16});
        for (int i = 0; i < 7; i+=2) {
            if(players.size()<parameters[i]){
                //4 - 6 joueurs -> 2 teams
                createTeams(players, parameters[i+1]);
                break;
            }
        }
    }
    
    private void createTeams(List<Player> players, int teamsCount){
        // Constitue les équipes avec 2 joueurs aléatoires
        for (int i = 1; i < teamsCount+1; i++) { // +1 pour que la 1ere team ait le numéro 1 et non 0
            List<Player> selectedPlayers = addPlayers(players);
            selectedPlayers.forEach(p -> players.remove(p)); // Retire les deux joueurs sélectionnés de la liste
            teams.add(new Team(selectedPlayers, i));
        }
        // S'il reste des joueurs, les ajoute a des équipes aléatoires
        if (players.size()>0) {
            addRemainingPlayers(players, teams);
        }
    }

    private List<Player> addPlayers(List<Player> players) {
        List<Player> selectedPlayers = new ArrayList<>();
        // Sélectionne 2 joueurs aléatoires et les ajoute à l'équipe
        for (int i = 0; i < 2; i++) {
            Player p = players.get(RandomGenerators.generateNumberBetween(0, players.size()));
            selectedPlayers.add(p);
            players.remove(p); // retire le joueur sélectionné de la liste locale
        }
        return selectedPlayers;
    }

    private void addRemainingPlayers(List<Player> remainingPlayers, List<Team> teams){
        // Tant qu'il reste des joueurs, boucler pour les caser dans des équipes aléatoires de 2 joueurs
        while (remainingPlayers.size() > 0) {
            int randomTeam = RandomGenerators.generateNumberBetween(0, teams.size());
            int teamSize = teams.get(randomTeam).getPlayers().size();
            if (teamSize > 2) {
                continue;
            }
            teams.get(randomTeam).addPlayer(remainingPlayers.get(0));
            remainingPlayers.remove(remainingPlayers.get(0));
        }
    }

    public List<Team> getTeams() {
        return teams;
    }
}
