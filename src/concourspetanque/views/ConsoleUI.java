package concourspetanque.views;

import concourspetanque.services.IGame;
import concourspetanque.services.games.Championship;
import concourspetanque.services.games.League;
import concourspetanque.utils.Printer;
import concourspetanque.views.menus.ChampionshipEndMenu;
import concourspetanque.views.menus.IMenu;
import concourspetanque.views.menus.LeagueEndMenu;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleUI {
    private boolean exit = false;
    private IGame game;
    private IMenu endMenu;

    /**
     * Affiche le menu initial
     */
    public void start() {
        while (!this.exit) {
            Printer.printStartMenu();
            handleStartMenuChoices();
            if (this.game != null) {
                this.game.play();
                Printer.printRunningGames();
                this.results();
                // reset global variables
                this.game = null;
            }
        }      
        Printer.printGameOver();    
    }

    /**
     * Gestion les choix utilisateur du menu initial
     */
    private void handleStartMenuChoices() {
        switch (getUserInput()) {
            case 0:
                this.exit = true;
                break;
            case 1:
                this.game = new League();
                this.endMenu = new LeagueEndMenu(this.game);
                break;
            case 2:
                this.game = new Championship();
                this.endMenu = new ChampionshipEndMenu(this.game);
                break;
            default:
                System.out.println("Erreur : entrez un nombre valide.");
        }
    }
    
    /**
     * Méthode pour gérer la saisie utilisateur
     * @return
     */
    public static int getUserInput() {
        System.out.print("Votre choix : ");
        try {
            InputStreamReader streamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String userInput = bufferedReader.readLine();
            int i = Integer.parseInt(userInput);
            return i;
        } catch (Exception e) {
            return -1;
        }       
    }

    /**
     * Affiche le menu final
     */
    public void results() {
        this.endMenu.showMenu();
    }
}
