package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import ca.sfu.cmpt276.spring2021.group8.project.ImageLoader;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;
import java.awt.*;

/**
 * The regular rewards the are scattered across the board
 */
public class Reward extends Collectable {

    final static int REWARDS_POINTS = 2;

    /**
     * Constructs Reward object and assigns a Position on maze to it
     *
     * @param rewardCoordinate Point Object having a position to be assigned to a Reward object
     */
    public Reward(Point rewardCoordinate) {
        super(rewardCoordinate);
    }

    /**
     * Returns points awarded if the player collects a reward
     *
     * @return Integer value of collecting a reward
     */
    @Override
    public int getPoints() {
        return REWARDS_POINTS;
    }

    /**
     * Renders the Reward onto the screen
     *
     * @param g Graphic object to draw Image onto screen
     * @param s
     */
    @Override
    public void render(Graphics g, WorldScreenAdapter s) {
        Rectangle offset = g.getClipBounds();
        Point RewardScreenPosition = s.convert(this.getPosition());
        g.drawImage(ImageLoader.rr, offset.x + RewardScreenPosition.x - 23 + s.gridHorizontalSpacing()/2, offset.y + RewardScreenPosition.y - 23 + s.gridVerticalSpacing()/2, null);
    }
}