package concourspetanque.views.menus;

import concourspetanque.services.IGame;
import concourspetanque.utils.Printer;

public class ChampionshipEndMenu extends AbstractMenu implements IMenu {
    
    public ChampionshipEndMenu(IGame game) {
        super(game);
    }

    @Override
    public void printMenu() {
        Printer.printChampionshipEndMenu();
        
    }

    @Override
    public void printResult() {
        Printer.printArbo(this.game.getMatchController().getMatchs());
        
    }
    
}
