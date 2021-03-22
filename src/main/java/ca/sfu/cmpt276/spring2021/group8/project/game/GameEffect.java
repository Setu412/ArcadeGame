package ca.sfu.cmpt276.spring2021.group8.project.game;

/**
 * GameEffect is a container for communicating game state changes
 */
public interface GameEffect {
    /**
     * Score is a GameEffect that contains a score change
     */
    public class Score implements GameEffect {
        public final int score;

        Score(int score) {
            this.score = score;
        }
    }

    /**
     * Lose is a GameEffect that loses the game
     */
    public class Lose implements GameEffect {}

    /**
     * Lose is a GameEffect that wins the game
     */
    public class Win implements GameEffect {}

    /**
     * Create a GameEffect with the delta score
     * 
     * @param deltaScore the delta score
     * @return a GameEffect that changes
     */
    public static GameEffect createScoreEffect(int deltaScore) {
        return new Score(deltaScore);
    }

    /**
     * Create a GameEffect that loses the game
     * 
     * @return a GameEffect that loses the game
     */
    public static GameEffect createLoseEffect() {
        return new Lose();
    }

    /**
     * Create a GameEffect that wins the game
     * 
     * @return a GameEffect that wins the game
     */
    public static GameEffect createWinEffect() {
        return new Win();
    }
}
