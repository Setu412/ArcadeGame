package ca.sfu.cmpt276.spring2021.group8.project.game.effect;

/**
 * ScoreEffect is a GameEffect that contains a score change
 */
public class ScoreEffect extends GameEffect {
    public final int score;

    ScoreEffect(int score) {
        this.score = score;
    }
}
