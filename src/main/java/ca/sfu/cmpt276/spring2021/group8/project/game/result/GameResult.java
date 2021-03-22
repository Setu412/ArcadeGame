package ca.sfu.cmpt276.spring2021.group8.project.game.result;

public abstract class GameResult {
    /**
     * Create a losing GameOver GameResult
     */
    public static GameResult createLoseResult(long score, long time) {
        return new GameOverResult(score, time, false);
    }
    
    /**
     * Create a winning GameOver GameResult
     */
    public static GameResult createWinResult(long score, long time) {
        return new GameOverResult(score, time, true);
    }

    /**
     * Create a Quit GameResult
     */
    public static GameResult createQuitResult() {
        return new GameQuitResult();
    }
}
