package ca.sfu.cmpt276.spring2021.group8.project.game.effect;

/**
 * GameEffect is a container for communicating game state changes
 */
public interface GameEffect {
    /**
     * Create a GameEffect with the delta score
     * 
     * @param deltaScore the delta score
     * @return a GameEffect that changes
     */
    static GameEffect createScoreEffect(int deltaScore) {
        return new ScoreEffect(deltaScore);
    }

    /**
     * Create a GameEffect that loses the game
     * 
     * @return a GameEffect that loses the game
     */
    static GameEffect createLoseEffect() {
        return new GameOverEffect(false);
    }

    /**
     * Create a GameEffect that wins the game
     * 
     * @return a GameEffect that wins the game
     */
    static GameEffect createWinEffect() {
        return new GameOverEffect(true);
    }
}
