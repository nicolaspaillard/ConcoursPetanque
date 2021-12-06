package concourspetanque.views.menus;

import concourspetanque.services.IGame;
import concourspetanque.utils.AgeSorting;
import concourspetanque.utils.FirstNameSorting;
import concourspetanque.utils.LastNameSorting;
import concourspetanque.utils.Printer;

import static concourspetanque.views.ConsoleUI.getUserInput;

import java.util.Collections;

public abstract class AbstractMenu {
    protected Boolean exit = false;
    protected IGame game;

    public AbstractMenu(IGame game) {
        this.game = game;
    }

    /**
     * Affiche le menu final
     */
    public void showMenu() {
        while (!exit) {
            printMenu();
            handleUserChoice();
        }
    }

    /**
     * Gère les choix utilisateur
     */
    public void handleUserChoice() {
        switch (getUserInput()) {
            case 0:
                this.exit = true;
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
                printResult();
                break;
            case 5:
                Collections.sort(this.game.getPlayersController().getPlayers(), new FirstNameSorting());
                Printer.printPlayers(this.game.getPlayersController().getPlayers());
                break;
            case 6:
                Collections.sort(this.game.getPlayersController().getPlayers(), new LastNameSorting());
                Printer.printPlayers(this.game.getPlayersController().getPlayers());
                break;
            case 7:
                Collections.sort(this.game.getPlayersController().getPlayers(), new AgeSorting());
                Printer.printPlayers(this.game.getPlayersController().getPlayers());
                break;
            default:
                System.out.println("Erreur : entrez un nombre valide.");
        }
        
    }

    public abstract void printMenu();

    public abstract void printResult();
    
}
