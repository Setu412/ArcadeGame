package ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable;

import java.awt.*;
import java.awt.image.BufferedImage;
import ca.sfu.cmpt276.spring2021.group8.project.ImageLoader;
import ca.sfu.cmpt276.spring2021.group8.project.game.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.PositionValidator;

/**
 *  Player is the movable player that the user controls
 */
public class Player extends MovableEntity {
    private BufferedImage player;
    private boolean hasMoved=false;

    /**
     * Sets the starting position for the player
     *
     * @param startPosition Sets the starting point coordinates of the player
     */
    public Player(Point startPosition) {
        super(startPosition);
    }

    /**
     * Tries to move the player
     *
     * @param validator Check if move is valid
     * @param direction The direction the player a tried to move towards
     */
    public void move(PositionValidator validator, Direction direction) {
        tryMove(validator, direction.getNewPosition(getPosition()), direction);
        hasMoved=true;
    }

    /**
     * Checks to see if the player has moved or not
     *
     * @return True if player has moved, false if player has not moved
     */
    public boolean getHasMoved()
    {
        return hasMoved;
    }

    /**
     * Renders the player images based on the direction it is facing onto the screen
     *
     * @param g Graphic object to draw Image onto screen
     * @param adapter WorldScreenAdapter object to relate the world and screen
     */
    @Override
    public void render(Graphics g, WorldScreenAdapter adapter) {
        Rectangle offset = g.getClipBounds();
        Point playerScreenPosition = adapter.convert(getPosition());

        if (facing == Direction.North) {
            assert ImageLoader.playerup != null;
            g.drawImage(ImageLoader.p1, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);
        } else if (facing == Direction.South) {
            assert ImageLoader.playerdown != null;
            g.drawImage(ImageLoader.p2, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);

        } else if (facing == Direction.East) {
            assert ImageLoader.playerleft != null;
            g.drawImage(ImageLoader.p3, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);

        } else {
            assert ImageLoader.playerright != null;
            g.drawImage(ImageLoader.p4, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);
        }
    }
}
