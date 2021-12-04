package concourspetanque.models.scores;

import concourspetanque.models.Team;

public class TeamScore extends Team{
    private int victories;
    private int loses;

    private int score;
    private int points;

    public TeamScore(Team t) {
        super(t.getPlayers(), t.getId());
    }
    
    public int getVictories(){
        return this.victories;
    }
    public void addVictory(){
        this.victories++;
    }
    public void addPoints(int matchPoints){
        this.points+=matchPoints;
    }
    public int getPoints(){
        return this.points;
    }
    public int getLoses(){
        return this.loses;
    }
    public void addLoss(){
        this.loses++;
    }

    public int getScore(){
        return this.score;
    }
    public void addWin(){
        this.score++;
    }
    public void removeWin(){
        this.score--;
    }

    @Override
    public String toString() {
        return " [loses=" + loses + ", points=" + points + ", score=" + score + ", victories=" + victories + "]";
    }    
}
