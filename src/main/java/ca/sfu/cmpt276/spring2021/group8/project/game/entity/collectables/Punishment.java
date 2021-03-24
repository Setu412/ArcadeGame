package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import ca.sfu.cmpt276.spring2021.group8.project.ImageLoader;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;

import java.awt.*;

public class Punishment extends Collectable {

    final static int PUNISHMENT_POINTS = -4;

    /**
     * Constructs Punishment object and assigns a position on maze to it
     *
     * @param punishmentCoordinate Point object having position to be assigned to Bonus Reward object
     */
    public Punishment(Point punishmentCoordinate) {
        super(punishmentCoordinate);
    }

    /**
     * Returns points awarded if the player moves into a Punishment
     *
     * @return Integer value of moving into a Punishment
     */
    @Override
    public int getPoints() {
        return PUNISHMENT_POINTS;
    }

    /**
     * Renders a Punishment entity onto the screen
     *
     * @param g Graphic object to draw Image onto screen
     * @param s
     */
    @Override
    public void render(Graphics g, WorldScreenAdapter s) {
        Rectangle offset = g.getClipBounds();
        Point PunishmentScreenPosition = s.convert(getPosition());
        g.drawImage(ImageLoader.pun, offset.x + PunishmentScreenPosition.x - 23 + s.gridHorizontalSpacing()/2, offset.y + PunishmentScreenPosition.y - 23 + s.gridVerticalSpacing()/2, null);
    }
}
