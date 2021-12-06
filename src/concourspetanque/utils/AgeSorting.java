package concourspetanque.utils;

import java.util.Comparator;

import concourspetanque.models.Player;

public class AgeSorting implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2) {
        return Integer.compare(p1.getAge(), p2.getAge());
    }
}
