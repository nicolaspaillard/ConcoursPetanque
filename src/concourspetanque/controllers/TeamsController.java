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

        // Compliqué à expliquer... for + tableau = if ... else if ... else if (quand identiques à 1 param)
        int[] parameters = (gameMode == GameMode.LEAGUE ? new int[]{16,6,20,8,24,10,48,12} : new int[]{7,2,13,4,25,8,49,16});
        for (int i = 0; i < 7; i+=2) {
            if(players.size()<parameters[i]){
                addPlayers(players, parameters[i+1]);
                break;
            }
        }
    }
        
    /** 
     * @param tempPlayers : A local temporary list of players
     * @param teamsCount : The quantity of teams to create
     */
    private void addPlayers(List<Player> tempPlayers, int teamsCount){
        // Effectue une boucle par équipe : teamsCount
        for (int i = 1; i < teamsCount+1; i++) { // i = 1; i < teamsCount+1 pour que la 1ere team ait le numéro 1 et non 0
            List<Player> selectedPlayers = new ArrayList<>();// Equipe temporaire 
            // Effectue une boucle par joueur : 2
            for (int j = 0; j < 2; j++) {
                // Récupère un joueur aléatoirement dans la liste locale
                Player p = tempPlayers.get(RandomGenerators.generateNumberBetween(0, tempPlayers.size()));
                selectedPlayers.add(p); // Ajoute le joueur selectionné à l'équipe temporaire
                tempPlayers.remove(p); // retire le joueur sélectionné de la liste locale
            }
            // Ajoute l'équipe temporaire comme nouvelle Team
            teams.add(new Team(selectedPlayers, i)); 
        }
        // S'il reste des joueurs, les ajoute a des équipes aléatoires
        if (tempPlayers.size()>0) {
            addRemainingPlayers(tempPlayers);
        }
    }
    
    /** 
     * @param remainingPlayers : The list of players that have not been selected 
     */
    private void addRemainingPlayers(List<Player> remainingPlayers){
        // Boucle tant qu'il reste des joueurs a placer
        while (remainingPlayers.size() > 0) {
            // Selectionne une Team au hasard
            int randomTeam = RandomGenerators.generateNumberBetween(0, teams.size());
            int teamSize = teams.get(randomTeam).getPlayers().size();
            if (teamSize > 2) {// Vérifie si celle-ci contient 2 joueurs, sinon recommence
                continue;
            }
            // Ajoute le 1er joueur restant dans la liste dans l'équipe sélectionnée
            teams.get(randomTeam).addPlayer(remainingPlayers.get(0));
            // Retire le joueur de la liste des joueurs restants
            remainingPlayers.remove(remainingPlayers.get(0));
        }
    }

    /** 
     * @return List<Team> : Returns the list of all teams in that instance
     */
    public List<Team> getTeams() {
        return teams;
    }
    public Team getTeam(int teamID){
        return teams.get(teamID-1);
    }
}
