package concourspetanque.views.menus;

import concourspetanque.services.IGame;
import concourspetanque.utils.Printer;
import static concourspetanque.views.ConsoleUI.getUserInput;

public class ChampionshipEndMenu implements IMenu {
    private Boolean exit = false;
    private IGame game;
    
    public ChampionshipEndMenu(IGame game) {
        this.game = game;
    }

    @Override
    public void showMenu() {
        while (!exit) {
            Printer.printChampionshipEndMenu();
            handleUserChoice();
        }
        
    }

    @Override
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
                // Il faudra adater ce dernier choix et le wording pour la partie championnat
                Printer.printArbo(this.game.getMatchController().getMatchs());
                break;
            default:
                System.out.println("Erreur : entrez un nombre valide.");
        }
        
    }
    
}
