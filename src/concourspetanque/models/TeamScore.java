package concourspetanque.models;

public class TeamScore extends Team{
    private int victories;
    private int loses;

    private int score;

    public TeamScore(Team t) {
        super(t.getPlayers(), t.getId());
    }
    
    public int getVictories(){
        return this.victories;
    }
    public void addVictory(){
        this.victories++;
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

}
