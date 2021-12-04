package concourspetanque.utils;
import java.util.List;

import concourspetanque.models.Match;
import concourspetanque.models.Player;
import concourspetanque.models.Team;

public class Printer {

    public static void printStartMenu() {
        Printer.printLine(40);
        System.out.println("\n\tMenu principal :");
        System.out.println("1 - Simulation mode ligue");
        System.out.println("2 - Simulation mode championat");
        System.out.println("0 - Quitter");
        Printer.printLine(40);
    }

    public static void printEndMenu() {
        Printer.printLine(40);
        System.out.println("\n\tMenu de fin de partie :");
        System.out.println("1 - Afficher la liste des joueurs");
        System.out.println("2 - Afficher la liste des équipes");
        System.out.println("3 - Afficher le déroulé des matchs");
        System.out.println("4 - Afficher les résultats finaux");
        System.out.println("0 - Terminer et revenir au menu principal");
        Printer.printLine(40);
    }

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

    public static void printMatchs(List<Match> matchs, int teamsCount) {
        int matchsPerRound = teamsCount / 2;
        // Titre
        printLine(40);
        System.out.println("MATCHS");
        // Lignes avec les données (Macths)
        int round = 1;
        for(int i = 0 ; i < matchs.size() ; i++) {
            if (i % matchsPerRound == 0) {
                System.out.println("\nPARTIE " + round + "\n");
                round++;
            }
            System.out.printf("%-8s", "Team" + (matchs.get(i).getOpponent1().getId() + 1));
            System.out.printf("%-3s", matchs.get(i).getOpponent1score());
            System.out.print(" - ");
            System.out.printf("%3s", matchs.get(i).getOpponent2score());
            System.out.printf("%8s", "Team" + (matchs.get(i).getOpponent2().getId() + 1));
            System.out.printf("%10s", "");
            System.out.printf("%-25s", "Winner : Team" + (matchs.get(i).getWinner().getId() + 1));
            System.out.println("");
        }
    }

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
            System.out.printf("%10s", "?");
            System.out.println("");
        }
    }

    /**
     * Fonction utilitaire pour print une ligne de séparation avec un espace avant et après
     * @param size : taille de la ligne
     */
    public static void printLine(int size) {
        System.out.print("\n");
        for (int i = 0 ; i < size ; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }

    // public static String printAsciiArt() throws IOException {
    //     return new String(Files.readAllBytes(Paths.get("src/concourspetanque/resources/ascii.txt")));
    // }
    public static void printAsciiArt() {
        System.out.println(asciiArt());
    }

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

