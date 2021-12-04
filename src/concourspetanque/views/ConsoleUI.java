package concourspetanque.views;

import concourspetanque.controllers.GameController;
import concourspetanque.controllers.ScoresController;
import concourspetanque.models.GameMode;
import concourspetanque.models.scores.MatchScores;
import concourspetanque.models.scores.RoundScores;

public class ConsoleUI {
    GameController gameController = null;
    ScoresController scoresController = null;
    public void start() { 
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        while (true) {
            boolean exit = false;
            switch (printMenu("\n\tMenu principal :\n1 - Simulation mode rencontre\n2 - Simulation mode tournoi\n0 - Quitter")) {
                case 1:
                    gameController= new GameController(GameMode.LEAGUE);
                    break;
                case 2:
                    gameController= new GameController(GameMode.CHAMPIONSHIP);
                    break;
                case 0:
                    exit = true;
                    break;
                default:        
                    System.out.println("Erreur : entrez un nombre valide.");     
                    break;
            }
            if(exit)break;
            if(gameController != null){
                scoresController = gameController.getScores();
                printTeams();
                printTeamsScores();
                printArbo();  
                gameController = null;
            }
        }            
    }
    private void printTeams() {
        scoresController.getTeamsScores().forEach(t -> System.out.println("Team "+ (t.getId()+1) + " " + t.getPlayers()));
        System.out.println();
    }
    private void printTeamsScores() {
        scoresController.getTeamsScores().forEach(t -> System.out.println(t));
        System.out.println();
    }
    private void printArbo() {
        for (RoundScores roundScores : scoresController.getRoundsScores()) {
            for (MatchScores matchScores : roundScores.getMatchesScores()) {
                System.out.print((matchScores.getWinner().getId()+1)+" "+(matchScores.getLooser().getId()+1)+"\t");
                if(roundScores.getMatchesScores().size()==1){
                    System.out.println();
                    System.out.println((matchScores.getWinner().getId()+1));
                }
            }
            System.out.println();
        }
    }
    private int printMenu(String s) {
        System.out.println(s);
        String userInput = System.console().readLine();
        try {
            int i = Integer.parseInt(userInput);
            return i;
        } catch (Exception e) {
            return -1;
        }       
    }
}
