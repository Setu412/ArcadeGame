package ca.sfu.cmpt276.spring2021.group8.project.game;

public class GameEffect {
    final public int score;
    final public boolean lose;

    public static GameEffect createScoreEffect(int deltaScore) {
        return new GameEffect(deltaScore, false);
    }

    public static GameEffect createLoseEffect() {
        return new GameEffect(0, true);
    }

    private GameEffect(int score, boolean lose) {
        this.score = score;
        this.lose = lose;
    }
}
