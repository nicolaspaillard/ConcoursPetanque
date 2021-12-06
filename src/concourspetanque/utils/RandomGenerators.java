package concourspetanque.utils;

import java.util.Random;

public class RandomGenerators {
    private static Random rand = new Random();
    
    /** 
     * Génère des noms aléatoires
     * @return String
     */
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

    /** 
     * Génère un nombre aléatoire entre les deux paramètre passés.
     * @param min
     * @param max
     * @return int
     */
    public static int generateNumberBetween(int min, int max) {
        // if(max == 0 && min > 0){
        //     throw new Error("Erreur : paramètre \"max\" incorrect dans la fonction generateNumberBetween("+min+","+max+")");
        // }else 
        if(min == 0 && max == 0){
            return 0;
        }else if(max-min==0){
            return min;
        }else{
            return rand.nextInt(max-min) + min;
        }        
    }
}







