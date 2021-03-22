package ca.sfu.cmpt276.spring2021.group8.project.game;

/**
 * GameResult is a container for communicating the result of a game
 */
public interface GameResult {
    /**
     * GameOver is a GameResult that contains the final state of the game
     */
    public class GameOver implements GameResult {
        public final int score;
        public final long time;
        public final boolean win;

        GameOver(int score, long time, boolean win) {
            this.score = score;
            this.time = time;
            this.win = win;
        }
    }

    /**
     * Quit is a GameResult that indicates game interruption
     */
    public class Quit implements GameResult {}
    
    /**
     * Create a losing GameOver GameResult
     */
    public static GameResult createLoseResult(int score, long time) {
        return new GameOver(score, time, false);
    }
    
    /**
     * Create a winning GameOver GameResult
     */
    public static GameResult createWinResult(int score, long time) {
        return new GameOver(score, time, true);
    }

    /**
     * Create a Quit GameResult
     */
    public static GameResult createQuitResult() {
        return new Quit();
    }
}
