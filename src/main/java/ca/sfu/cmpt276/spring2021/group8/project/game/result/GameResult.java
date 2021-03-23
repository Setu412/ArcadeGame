package ca.sfu.cmpt276.spring2021.group8.project.game.result;

/**
 * GameResult is a container for communicating the result of a game
 */
public interface GameResult {
    /**
     * Create a losing GameOver GameResult
     */
    static GameResult createLoseResult(long score, long time) {
        return new GameOverResult(score, time, false);
    }
    
    /**
     * Create a winning GameOver GameResult
     */
    static GameResult createWinResult(long score, long time) {
        return new GameOverResult(score, time, true);
    }

    /**
     * Create a Quit GameResult
     */
    static GameResult createQuitResult() {
        return new GameQuitResult();
    }
}
