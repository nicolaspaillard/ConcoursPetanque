package concourspetanque.controllers.tools;

import java.util.Random;

public class RandomGenerators {
    private static Random rand = new Random();
    
    public static String generateName() {
        String[] Beginning = { "Kr", "Ca", "Ra", "Mrok", "Cru",
            "Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer", "Jar", "Mjol",
            "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro",
            "Mar", "Luk" };
        String[] Middle = { "air", "ir", "mi", "sor", "mee", "clo",
            "red", "cra", "ark", "arc", "miri", "lori", "cres", "mur", "zer",
            "marac", "zoir", "slamar", "salmar", "urak" };
        String[] End = { "d", "ed", "ark", "arc", "es", "er", "der",
            "tron", "med", "ure", "zur", "cred", "mur" };
        return Beginning[rand.nextInt(Beginning.length)] +
            Middle[rand.nextInt(Middle.length)]+
            End[rand.nextInt(End.length)];
    }

    public static int generateNumberBetween(int min, int max) {
        return rand.nextInt(max-min) + min;
    }
}







