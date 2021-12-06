import concourspetanque.utils.Printer;
import concourspetanque.views.ConsoleUI;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // ASCII DRAW
        Printer.printAsciiArt();
        
        // Start command line interface
        ConsoleUI UI = new ConsoleUI();
        UI.start();
    }
}
