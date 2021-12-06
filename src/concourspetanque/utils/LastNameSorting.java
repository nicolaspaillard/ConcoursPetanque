package concourspetanque.utils;

import java.util.Comparator;

import concourspetanque.models.Player;

public class LastNameSorting implements Comparator<Player>{

    @Override
    public int compare(Player p1, Player p2) {
        return p1.getLastName().compareTo(p2.getLastName());
    }
}