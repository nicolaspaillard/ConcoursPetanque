package concourspetanque;

/**
 *
 * @author nicpa
 */
public class ConcoursPetanque {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        playersInscription();
    }

    private static void playersInscription() {
        double nbPlayers = Math.random();
    }

    public static int random_int(int Min, int Max)
    {
        return (int) (Math.random()*(Max-Min))+Min;
    }

//    random_int(5, 9);

}
