package ca.sfu.cmpt276.spring2021.group8.project.game.effect;

/**
 * GameOverEffect is a GameEffect that ends the game
 */
public class GameOverEffect implements GameEffect {
    public final boolean win;
    
    GameOverEffect(boolean win) {
        this.win = win;
    }
}