package concourspetanque.Tools;

public class Tools {
    public static int random_int(int Min, int Max) {
        int result = (int) (Math.random()*(Max-Min))+Min;
        return result;
    }
}
