package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import ca.sfu.cmpt276.spring2021.group8.project.ImageLoader;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;

import java.awt.*;

/**
 * The BonusReward that appears and disappears
 */
public class BonusReward extends Collectable {

    public boolean isVisible = false;
    final static int BONUS_POINTS = 5;

    /**
     * Constructs BonusReward object and assigns a position on maze to it
     *
     * @param BonusRewardCoordinate Position of a Bonus Reward
     */
    public BonusReward(Point BonusRewardCoordinate) {
        super(BonusRewardCoordinate);
    }

    /**
     * Returns points awarded if the player collects a Bonus Reward
     *
     * @return Integer value of collecting a BonusReward
     */
    @Override
    public int getPoints() {
        return BONUS_POINTS;
    }

    /**
     * Resets the position of a BonusReward entity with a new position
     *
     * @param position Point object having new position of BonusReward
     */
    public void setPosition(Point position) {
        super.setPosition(position);
    }

    /**
     * Renders the BonusReward onto the screen only when it should be visible in the game
     *
     * @param g Graphic object to draw Image onto screen
     * @param s WorldScreenAdapter object used to relate the size of world screen
     */
    @Override
    public void render(Graphics g, WorldScreenAdapter s) {
        if(isVisible) {
            Rectangle offset = g.getClipBounds();
            Point BRewardScreenPosition = s.convert(this.getPosition());
            g.drawImage(ImageLoader.bon,offset.x + BRewardScreenPosition.x - 23 + s.gridHorizontalSpacing() / 2, offset.y + BRewardScreenPosition.y - 23 + s.gridVerticalSpacing() / 2, null);
        }
    }
}
