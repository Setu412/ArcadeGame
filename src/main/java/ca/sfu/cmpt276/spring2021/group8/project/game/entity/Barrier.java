package ca.sfu.cmpt276.spring2021.group8.project.game.entity;

import ca.sfu.cmpt276.spring2021.group8.project.ImageLoader;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;

import java.awt.*;

/**
 * The barriers that obstruct the path of the player and enemies
 */
public class Barrier extends Entity {

    /**
     * Constructs a Barrier object and assigns a Position on maze to it
     *
     * @param startPosition Point object having initial position to be assigned to Barrier object
     */
    public Barrier(Point startPosition) {
        super(startPosition);
    }

    /**
     * Assigns new updated Position to a barrier object on the maze
     *
     * @param newPosition Point object having new Position to be assigned to a barrier
     */
    public void updatePosition(Point newPosition){
        this.setPosition(newPosition);
    }

    /**
     * Renders the Barrier onto the screen
     *
     * @param g Graphic object to draw Image onto screen
     * @param s WorldScreenAdapter object used to relate the size of world screen
     */
    @Override
    public void render(Graphics g, WorldScreenAdapter s) {
        Rectangle offset = g.getClipBounds();
        Point BarrierScreenPosition = s.convert(getPosition());
        g.drawImage(ImageLoader.bar, offset.x + BarrierScreenPosition.x - 24 + s.gridHorizontalSpacing()/2, offset.y + BarrierScreenPosition.y - 24 + s.gridVerticalSpacing()/2, null);
    }
}
