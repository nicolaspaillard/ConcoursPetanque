package concourspetanque.views;

import concourspetanque.services.IGame;
import concourspetanque.services.games.Championship;
import concourspetanque.services.games.League;
import concourspetanque.utils.Printer;

public class ConsoleUI {
    private boolean exitStartMenu = false;
    private boolean exitEndMenu = false;
    private IGame game;

    /**
     * While loop pour le menu initial
     */
    public void start() {
        while (!exitStartMenu) {
            Printer.printStartMenu();
            handleStartMenuChoices();
            if (this.game != null) {
                this.game.play();
                this.results();
                // reset global variables
                this.game = null;
                this.exitEndMenu = false;
            }
        }            
    }

    /**
     * Gestion des choix du menu initial
     */
    private void handleStartMenuChoices() {
        switch (getUserInput()) {
            case 0:
                this.exitStartMenu = true;
                break;
            case 1:
                this.game = new League();
                break;
            case 2:
                this.game = new Championship();
                break;
            default:
                System.out.println("Erreur : entrez un nombre valide.");
        }
    }

    /**
     * While loop pour le menu de fin de partie
     */
    public void results() {
        while (!exitEndMenu) {
            Printer.printEndMenu();
            handleEndMenuChoices();
        }
    }

    private void handleEndMenuChoices() {
        switch (getUserInput()) {
            case 0:
                this.exitEndMenu = true;
                break;
            case 1:
                Printer.printPlayers(this.game.getPlayersController().getPlayers());
                break;
            case 2:
                Printer.printTeams(this.game.getTeamsController().getTeams());
                break;
            case 3:
                int teamsCount = this.game.getTeamsController().getTeams().size();
                Printer.printMatches(this.game.getMatchController().getMatchs(), teamsCount);
                break;
            case 4:
                // Il faudra adater ce dernier choix et le wording pour la partie championnat
                Printer.printLeagueResults(this.game.getTeamsController().getTeams());
                break;
            default:
                System.out.println("Erreur : entrez un nombre valide.");
        }
    }
    
    /**
     * Méthode pour gérer la saisie utilisateur
     * @return
     */
    private int getUserInput() {
        System.out.print("Votre choix : ");
        String userInput = System.console().readLine();
        try {
            int i = Integer.parseInt(userInput);
            return i;
        } catch (Exception e) {
            return -1;
        }       
    }
}
