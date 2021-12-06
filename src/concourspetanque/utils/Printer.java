package concourspetanque.utils;
import java.util.List;

import concourspetanque.models.Match;
import concourspetanque.models.Player;
import concourspetanque.models.Team;

public class Printer {
    /**
     * Affiche l'ASCII art de début de jeu
     */
    public static void printAsciiArt() {
        System.out.println(asciiArt());
    }

    /**
     * Affiche le menu initial
     */
    public static void printStartMenu() {
        Printer.printLine(40);
        System.out.println("\n\tMENU PRINCIPAL\n");
        System.out.println("1 - Jouer en mode ligue");
        System.out.println("2 - Jouer en mode championnat");
        System.out.println("0 - Quitter");
        Printer.printLine(40);
    }

    /**
     * Affiche le menu final en mode League
     */
    public static void printLeagueEndMenu() {
        Printer.printLine(40);
        System.out.println("\n\tMENU DE FIN DE PARTIE\n");
        System.out.println("1 - Afficher la liste des joueurs");
        System.out.println("2 - Afficher la liste des équipes");
        System.out.println("3 - Afficher le déroulé des matchs");
        System.out.println("4 - Afficher le tableau des résultats");
        System.out.println("5 - Trier les joueurs par prénom");
        System.out.println("6 - Trier les joueurs par nom");
        System.out.println("7 - Trier les joueurs par âge");
        System.out.println("0 - Terminer et revenir au menu principal");
        Printer.printLine(40);
    }

    /**
     * Affiche le menu final en mode Championnat
     */
    public static void printChampionshipEndMenu() {
        Printer.printLine(40);
        System.out.println("\n\tMENU DE FIN DE PARTIE\n");
        System.out.println("1 - Afficher la liste des joueurs");
        System.out.println("2 - Afficher la liste des équipes");
        System.out.println("3 - Afficher le déroulé des matchs");
        System.out.println("4 - Afficher l'arborescence des matchs");
        System.out.println("5 - Trier les joueurs par prénom");
        System.out.println("6 - Trier les joueurs par nom");
        System.out.println("7 - Trier les joueurs par âge");
        System.out.println("0 - Terminer et revenir au menu principal");
        Printer.printLine(40);
    }
    
    /**
     * Affiche l'ASCII art "Parties en cours"
     */
    public static void printRunningGames() {
        System.out.println(asciiText());
    }

    /**
     * Affiche les joueurs passées en paramètre
     * @param players
     */
    public static void printPlayers(List<Player> players) {
        // Titre
        printLine(40);
        System.out.println("JOUEURS");
        System.out.println("Nombre de joueurs inscrits : " + players.size());
        // En-têtes du tableau
        printLine(40);
        System.out.printf("%-4s", "Id");
        System.out.printf("%-15s", "Prénom");
        System.out.printf("%-15s", "Nom");
        System.out.printf("%-6s", "Age");
        printLine(40);
        // Lignes avec les données (players)
        for(Player player : players) {
            System.out.printf("%-4s", player.getId());
            System.out.printf("%-15s", player.getFirstName());
            System.out.printf("%-15s", player.getLastName());
            System.out.printf("%-6s", player.getAge());
            System.out.println("");
        }
    }

    /**
     * Affiche les équipes passées en paramètre
     * @param teams
     */
    public static void printTeams(List<Team> teams) {
        // Titre
        printLine(40);
        System.out.println("EQUIPES");
        // En-têtes du tableau
        printLine(111);
        System.out.printf("%-6s", "Team");
        System.out.printf("%-35s", "Joueur 1");
        System.out.printf("%-35s", "Joueur 2");
        System.out.printf("%-35s", "Joueur 3");
        printLine(111);
        // Lignes avec les données (teams)
        for(Team team : teams) {
            System.out.printf("%-6s", (team.getId() + 1));
            for(Player p: team.getPlayers()) {
                System.out.printf("%-35s", p.getFirstName() + " " + p.getLastName() + " (" + p.getId() + ")");
            }
            System.out.println("");
        }
    }

    /**
     * Affiche les matchs passés en paramètre
     * @param matchs
     * @param teamsCount
     */
    public static void printMatches(List<Match> matchs, int teamsCount) {
        // Titre
        printLine(40);
        System.out.println("MATCHS");
        // En-têtes du tableau
        printLine(53);
        System.out.printf("%-8s", "Round");
        System.out.printf("%-35s", "Match");
        System.out.printf("%-10s", "Gagnant");
        printLine(53);
        // Lignes avec les données (Macths)
        for(int i = 0 ; i < matchs.size() ; i++) {
            System.out.printf("%-8s", matchs.get(i).getRoundNumber());
            System.out.printf("%-8s", "Team" + (matchs.get(i).getOpponent1().getId() + 1));
            System.out.printf("%-3s", matchs.get(i).getOpponent1score());
            System.out.print(" - ");
            System.out.printf("%3s", matchs.get(i).getOpponent2score());
            System.out.printf("%8s", "Team" + (matchs.get(i).getOpponent2().getId() + 1));
            System.out.printf("%10s", "");
            System.out.printf("%-10s", "Team " + (matchs.get(i).getWinner().getId() + 1));
            System.out.println("");
        }
    }

    /**
     * Affiche le tableau final de résultats en mode League
     * @param teams
     */
    public static void printLeagueResults(List<Team> teams) {
        // Titre
        printLine(40);
        System.out.println("\nTABLEAU DES RESULTATS");
        // Affiche les en-têtes du tableau
        printLine(136);
        System.out.printf("%-6s", "TEAM");
        System.out.printf("%-45s", "NOMS");
        System.out.printf("%-11s", "PARTIE 1");
        System.out.printf("%-11s", "PARTIE 2");
        System.out.printf("%-11s", "PARTIE 3");
        System.out.printf("%-11s", "PARTIE 4");
        System.out.printf("%6s", "+");
        System.out.printf("%6s", "-");
        System.out.printf("%8s", "TOTAL");
        System.out.printf("%11s", "VICTOIRES");
        System.out.printf("%10s", "PLACE");
        printLine(136);
        // Boucle sur les différentes équipes et affiche les informations ligne par ligne
        for (Team team: teams) {
            // Affiche Team ID
            System.out.printf("%-6s", (team.getId() + 1));
            // Affiche Joueurs
            for(int i = 0 ; i < 3 ; i++) {
                try {
                    // Affiche nom des joueurs de l'équipe
                    Player player = team.getPlayers().get(i);
                    System.out.printf("%-15s", player.getLastName());
                } catch (Exception e) {
                    // Réserve un espace vide si pas de 3ème joueur
                    System.out.printf("%-15s", "");
                }
            }
            // Affiche Parties
            for(Match match : team.getPlayedMatchs()) {
                System.out.printf("%-11s", match.getOpponent1score() + " / " + match.getOpponent2score());
            }
            // Affiche Points et Total
            System.out.printf("%6s", team.getPositivePoints());
            System.out.printf("%6s", team.getNegativePoints());
            System.out.printf("%8s", team.getPositivePoints() + team.getNegativePoints());
            // Affiche Victoire et position
            System.out.printf("%11s", team.getVictories());
            System.out.printf("%10s", (team.getRanking() + 1));
            System.out.println("");
        }
    }

    /**
     * Affiche l'arborescence finale en mode championnat
     * @param matches
     */
    public static void printArbo(List<Match> matches) {
        // Titre
        printLine(40);
        System.out.println("\nARBORESCENCE FINALE\n");

        int totalRounds = matches.get(matches.size() - 1).getRoundNumber();
        
        for (int i = 0; i <= totalRounds ; i++) {
            for(Match match : matches) {
                if (match.getRoundNumber() == i) {
                    System.out.printf("%-6s", "| T" + (match.getOpponent1().getId()+1));
                    System.out.printf("%2s", "vs");
                    System.out.printf("%6s", "T" + (match.getOpponent2().getId()+1) + " |");
                    System.out.printf("%3s", "");
                }
            }
            System.out.println("\n");
        }
        System.out.println("| T" + (matches.get(matches.size() - 1).getWinner().getId() + 1) + " |  Winner !!");
    }

    
    /**
     * Affiche l'ASCII art "Game Over"
     */
    public static void printGameOver() {
        System.out.println(asciiText2());
    }


    /**
     * Fonction utilitaire pour print une ligne de séparation
     * @param size : taille de la ligne
     */
    public static void printLine(int size) {
        System.out.print("\n");
        for (int i = 0 ; i < size ; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }
    
    /**
     * Retourne une string avec ASCII art "Partie en cours"
     * @return
     */
    private static final String asciiText() {
        return  "\n    ,------.                  ,--.  ,--.                                                                            |   | \n" +
                "    |  .--. ' ,--,--.,--.--.,-'  '-.`--' ,---.  ,---.      ,---. ,--,--,      ,---. ,---. ,--.,--.,--.--. ,---.     |  .' \n" +
                "    |  '--' |' ,-.  ||  .--''-.  .-',--.| .-. :(  .-'     | .-. :|      \\    | .--'| .-. ||  ||  ||  .--'(  .-'     |  |  \n" +
                "    |  | --' \\ '-'  ||  |     |  |  |  |\\   --..-'  `)    \\   --.|  ||  |    \\ `--.' '-' ''  ''  '|  |   .-'  `)    `--'  \n" +
                "    `--'      `--`--'`--'     `--'  `--' `----'`----'      `----'`--''--'     `---' `---'  `----' `--'   `----'     .--.  \n" +
                "                                                                                                                    '--'  \n";
    }

    /**
     * Retourne une string avec ASCII art "GAME OVER"
     * @return
     */
    private static final String asciiText2() {
        return "_______  _______  __   __  _______    _______  __   __  _______  ______    \n" +
                "|       ||   _   ||  |_|  ||       |  |       ||  | |  ||       ||    _ |  \n" +
                "|    ___||  |_|  ||       ||    ___|  |   _   ||  |_|  ||    ___||   | ||  \n" +
                "|   | __ |       ||       ||   |___   |  | |  ||       ||   |___ |   |_||_ \n" +
                "|   ||  ||       ||       ||    ___|  |  |_|  ||       ||    ___||    __  |\n" +
                "|   |_| ||   _   || ||_|| ||   |___   |       | |     | |   |___ |   |  | |\n" +
                "|_______||__| |__||_|   |_||_______|  |_______|  |___|  |_______||___|  |_|\n" +
                "\n\nAuthors : Nicolas Paillard & Victor Matheron\n\n";
    }

    /**
     * Retourne une string avec ASCII art "Bouliste"
     * @return
     */
    private static final String asciiArt() {
        return "                                                (@@@@@@@@@%,                                        \n" +
                "                                                   @@@@@@@@@@@%.                                    \n" +
                "                                    %(.   .(##       .&@@@@@@@@@@@&                                 \n" +
                "                                  @.,,,,,,,,.,,%        (@@@@@@@@@@@@@#                             \n" +
                "                  (#              %,,,,,,,,,,,.&%%.*&%(...#@@@@@@@@@@@@@@@                          \n" +
                "                % (/(# .*_,_       .(/,,,,,,,......,........*@@@@@@@@@@@@@@                         \n" +
                "              .#././*./,,,,.,#%        /.,...................,(@@@@@@@@@@@@@.                       \n" +
                "            .@ &. ..(/,,,,,,,, %%,@.*@ ,................,.%#.,..@@@@@@@@@@@@@.                      \n" +
                "           (#(...*&*/*,.,,,,.,,(&....,,...............#*...(#,.../@@@@@@@@@@@@                      \n" +
                "         *(,../@@@@((***,,,,.,.#/....................,,,.,.(....,(/@@@@@@@@@@.                      \n" +
                "         (,..,..,...*#/*/_,_,#/*,%/(%.................,...,.&%.( .,&@@@@@@@@%                       \n" +
                "          @,...#.,*,,/%@&@(.       #.,.....................&..,..,#*..&@#*                          \n" +
                "           &,.......#/..,           *,,......................,..&*. @#(                             \n" +
                "             %(,...../(.             ,&.......................,# .**%*                              \n" +
                "              &......(/                  *#&@((.,...............&*.                                 \n" +
                "             @...,...%                           .@(............ ,@#@,                              \n" +
                "           %/........%                              @(....,..*#    (&#%                             \n" +
                "         /#.........,#                            ,&(@ .,,#.   .%,      #/                          \n" +
                "        &,...........,#    ,                   /&     .%     (*    ,/%&%&#&@,                       \n" +
                "      /&..............#, (* #(&/*/#@&%,  #@& /@/@@, #@ @.  ( .@%*//(//(#%&@&#@/                     \n" +
                "     .@................@@.*# .@             @/@@*. @(@# #.@%///((%&*          .&.                   \n" +
                "     #*................@,@ ,%  @            #@@@@@@@    %#///(&*                ((                  \n" +
                "     #*..................%* //  &          (@@@@&   .*@,,((&.               *@&   %.                \n" +
                "     ,@.,................#/  @  /(       *@@@@((/.*,&#,,,* ,.            # /*      %                \n" +
                "       (&................#*  (*  &     #@@@@@@ .*(.,*******,,,.        %            ,,              \n" +
                "        ,@&&@@#,....*/#@ &*  ((  (, ,@@@@@@@@# ,(%,**/&%(/..                        .(@#/.          \n" +
                "                      #( @   */   #@@@@@@@@@@,/#,..*,,,,*,,,.     ,           ,#/         ,(.       \n" +
                "                       (&    *# /@@@@@@@@@@@#*%/((@/*,,,.,/,,       #    .@,       ,@@,             \n" +
                "                                /@@@@@@@@@@(/((/(&.,(,.              ,%.     #&/.        /&@.       \n" +
                "                              .@. &@@@@(   (#((#&                      ,#%,     *&%%*,,,&*          \n" +
                "                             (/    ,      /(/(/@                    (.   *%&*..,........,,#         \n" +
                "                            (*          .*%///@.                         &...............#          \n" +
                "                           /*            &/(/#&                           &..............,#         \n" +
                "                          .%            @////@.                            &..............&         \n" +
                "                          %     &*     (///(#@                              %,...........,@         \n" +
                "                          &&/((%(/(&@@*%(//(##                              #,...........,%         \n" +
                "                         .%//%%//////(#%#(%(#&///((((#@@&@@&&&&%&((/#&&&&%&&&@...........%          \n" +
                "                          ##/////////(/%&(%&&/(/(////////////////////////////@...........%          \n" +
                "                           %/(///////////(///////////////////////////////////@.........,&.          \n" +
                "                            &/(//////(//////(///(///////((/////////////(//(/%,.......,,@            \n" +
                "                            *#////////////////////////////////////////////#/..........,&.           \n" +
                "                             (#(////////////////////////////////////////((..............%           \n" +
                "                              /%//(/////////////(///////((///////////(((%.............,@            \n" +
                "                                &//////////////////(/////(//((////////(@,........,....&.            \n" +
                "                                 *&/(////(//((///////////(/////////////(% ..%*.../*,....%&%*./.     \n" +
                "                                   *@/(//(@%///////////////////////////(/&...*&.....&,,....,/*      \n" +
                "                                     /&//(///(#%&#/(/////(//(////////////&,*%,,.&#.....#            \n" +
                "                                      ,@(///(////&(////////////////(/////&.    ,**                  \n" +
                "                                       /@//((/////@/(((///////////////((/&.                         \n" +
                "                                        %&/(/////(#&/////////(/////(///((&                          \n" +
                "                                         @/(///////@/////(/(///////////((&                          \n" +
                "                                        .*%(///////#(///////((/////(////(@                          \n" +
                "                                          @////(//((&//////////////////(/&                          \n" +
                "                                          *&*/(/////@/(//////////////////&                          \n" +
                "                                          .@*(//(/((#&(/////////(//(/////&                          \n" +
                "                                           (&((((//(/&//////((//////////(%                          \n" +
                "                                            @///(//(/&//((//////////////((                          \n" +
                "                                            *%///////#&(/(//////////////(/                          \n" +
                "                                            ,&//////((&//((/(///(/////(/((                          \n" +
                "                                             (((//////@//////(/(///////(((                          \n" +
                "                                             *%///(///#(////////////////(*                          \n" +
                "                                              @///////#%////////(//////(((                          \n" +
                "                                              @///////(&/(/////((///(///(&                          \n" +
                "                                           *,.@//////((&(#(#((///(((//(%@@@@#                       \n" +
                "                                           (.,,,,,,..@,.,,,,..,,,,,...,,.,,,#                       \n" +
                "     .****    *(##(*                       &,,,,,,,,,/%.,.,,,,,,,,,,.,,,,,,&,                       \n" +
                "  *(*..,,,,,/#,.,,,,,.(                    @@@@@(//*//@/#/%/(((//***,,,/**/&                        \n" +
                " (*/*.,,,,,,,.&/.,,,,,.*              .,@@@@@@@@@@&%.%@@@@@@@@@@@@@@@@@@@@,                         \n" +
                " %**,,,,,,,...#...,,,,.*          (@@@@@@#*%@&/&@@@#,  /@@@@@@@@@@@@@@@@@@@%                        \n" +
                " .##(,.,,,,,,/((#,,..#         .(%@@@@@@@@@.@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,                       \n" +
                "   .%&@*.,*#..(////#            *&/ (&@@@@(@@@@@@@@@@@@@@@@@@@@@&@(/%* .,&&,                        ";
    }

}

