package concourspetanque.models;

import java.util.List;

public class TeamScore extends Team{
    private int victories;
    private int loses;
    public TeamScore(Team t) {
        super(t.getPlayers(), t.getId());
        //TODO Auto-generated constructor stub
    }
    public int getVictories(){
        return this.victories;
    }
    public void addVictory(){
        this.
        victories++;
    }
    public int getLoses(){
        return this.loses;
    }
    public void addLoss(){
        this.loses++;
    }
}
