package ca.sfu.cmpt276.spring2021.group8.project.game.result;

/**
 * GameOverResult is a GameResult that contains the final state of the game
 */
public class GameOverResult implements GameResult {
    public final long score;
    public final long time;
    public final boolean win;

    GameOverResult(long score, long time, boolean win) {
        this.score = score;
        this.time = time;
        this.win = win;
    }
}
