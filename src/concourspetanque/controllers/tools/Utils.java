package concourspetanque.controllers.tools;

public class Utils {
    public static void printLine(int size) {
        System.out.print("\n");
        for (int i = 0 ; i < size ; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }
}
