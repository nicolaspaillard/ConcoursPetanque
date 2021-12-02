package concourspetanque.views;

import java.util.Scanner;

import concourspetanque.controllers.GameController;
import concourspetanque.controllers.ScoresController;
import concourspetanque.models.GameMode;
import concourspetanque.models.scores.MatchScores;
import concourspetanque.models.scores.RoundScores;

public class ConsoleUI {
    GameController gameController = null;
    ScoresController scoresController = null;
    public void start() {        
            int choice = 2;
            switch (choice) {
                case 1:
                    gameController= new GameController(GameMode.LEAGUE);
                    break;
                case 2:
                    gameController= new GameController(GameMode.CHAMPIONSHIP);
                    break;
                default:                    
                    break;
            }
            scoresController = gameController.getScores();

            printTeams();
            printTeamsScores();
            printArbo();  
            gameController = null;
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
        Scanner sc = new Scanner(System.in);
        int i = -1;
        try {
            i = sc.nextInt();
        } catch (Exception e) {
            System.out.println("erreur catch");
        }
        sc.close();
        return i;
    }
}
