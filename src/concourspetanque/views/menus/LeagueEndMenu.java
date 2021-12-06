package concourspetanque.views.menus;

import concourspetanque.utils.Printer;
import static concourspetanque.views.ConsoleUI.getUserInput;

import concourspetanque.services.IGame;

public class LeagueEndMenu implements IMenu {
    private Boolean exit = false;
    private IGame game;

    public LeagueEndMenu(IGame game) {
        this.game = game;
    }

    @Override
    public void showMenu() {
        while (!exit) {
            Printer.printLeagueEndMenu();
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
                Printer.printLeagueResults(this.game.getTeamsController().getTeams());
                break;
            // case 5:
            //     Printer.printArbo(this.game.getMatchController().getMatchs());
            //     break;
            default:
                System.out.println("Erreur : entrez un nombre valide.");
        }
    }
    
}


// "_______  _______  ______    _______  ___   _______  _______    _______  __    _    _______  _______  __   __  ______    _______    __  " +
//         "|       ||   _   ||    _ |  |       ||   | |       ||       |  |       ||  |  | |  |       ||       ||  | |  ||    _ |  |       |  |  |" + 
//         "|    _  ||  |_|  ||   | ||  |_     _||   | |    ___||  _____|  |    ___||   |_| |  |       ||   _   ||  | |  ||   | ||  |  _____|  |  |" + 
//         "|   |_| ||       ||   |_||_   |   |  |   | |   |___ | |_____   |   |___ |       |  |       ||  | |  ||  |_|  ||   |_||_ | |_____   |  |" + 
//         "|    ___||       ||    __  |  |   |  |   | |    ___||_____  |  |    ___||  _    |  |      _||  |_|  ||       ||    __  ||_____  |  |__|" + 
//         "|   |    |   _   ||   |  | |  |   |  |   | |   |___  _____| |  |   |___ | | |   |  |     |_ |       ||       ||   |  | | _____| |   __ " + 
//         "|___|    |__| |__||___|  |_|  |___|  |___| |_______||_______|  |_______||_|  |__|  |_______||_______||_______||___|  |_||_______|  |__|";
