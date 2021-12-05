package concourspetanque.models;

public class Match {//simples doublettes triplettes
    private Team opponent1;
    private Team opponent2;
    private int opponent1score = 0;
    private int opponent2score = 0;
    private Team winner;
    private int roundNumber;
    //private int score;

    public Match(Team opponent1, Team opponent2, int opponent1score, int opponent2score, int roundNumber) {
        this.opponent1 = opponent1;
        this.opponent2 = opponent2;
        this.opponent1score = opponent1score;
        this.opponent2score = opponent2score;
        this.winner = this.opponent1score > this.opponent2score ? this.opponent1 : this.opponent2;
        this.roundNumber = roundNumber;
    }

    public Team getOpponent1() {
        return opponent1;
    }

    public Team getOpponent2() {
        return opponent2;
    }

    public int getRoundNumber(){
        return roundNumber;
    }

    public Team getWinner() {
        return winner;
    }

    public int getOpponent1score() {
        return opponent1score;
    }

    public int getOpponent2score() {
        return opponent2score;
    }

    @Override
    public String toString() {
        return "Match{" +
                "Team(" + opponent1.getId() + ") score = " + opponent1score +
                ", Team(" + opponent2.getId() + ") score = " + opponent2score +
                ", winner = " + winner.getId() +
                '}';
    }
}
