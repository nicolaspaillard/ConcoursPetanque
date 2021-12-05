package concourspetanque.utils;

import java.util.Comparator;

import concourspetanque.models.Team;

public class GoalAverageComparator implements Comparator<Team> {
    @Override
    public int compare(Team team1, Team team2) {
        return Integer.compare(team2.getGoalAverage(), team1.getGoalAverage());
    }
}
