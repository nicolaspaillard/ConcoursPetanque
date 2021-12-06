package concourspetanque.views.menus;

import concourspetanque.utils.Printer;

import concourspetanque.services.IGame;

public class LeagueEndMenu extends AbstractMenu implements IMenu {

    public LeagueEndMenu(IGame game) {
        super(game);
    }

    @Override
    public void printMenu() {
        Printer.printLeagueEndMenu();
        
    }

    @Override
    public void printResult() {
        Printer.printLeagueResults(this.game.getTeamsController().getTeams());
        
    }

    
    
}