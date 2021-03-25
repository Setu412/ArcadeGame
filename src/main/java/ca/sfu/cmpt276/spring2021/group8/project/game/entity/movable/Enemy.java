package ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable;

import ca.sfu.cmpt276.spring2021.group8.project.ImageLoader;
import ca.sfu.cmpt276.spring2021.group8.project.game.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.MovementGenerator;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.PositionValidator;

import java.awt.*;

/**
 * The enemies that move around in the game
 */
public class Enemy extends MovableEntity {

    private MovementGenerator movementGenerator;

    /**
     * Sets the starting position and gives Enemy a movementGenerator
     *
     * @param movementGenerator Generates what move an enemy should make
     * @param startPosition Sets the starting point coordinates of the enemy
     */
    public Enemy(MovementGenerator movementGenerator, Point startPosition) {
        super(startPosition);

        this.movementGenerator = movementGenerator;
    }

    /**
     * Tries to move the enemy
     *
     * @param validator To check if move is valid
     */
    public void move(PositionValidator validator) {
        tryMove(validator, movementGenerator.next(getPosition()).getNewPosition(getPosition()), movementGenerator.next(getPosition()));
    }

    /**
     * Renders the enemy images based on the the directions its facing
     * @param g Graphic object to draw Image onto screen
     * @param adapter WorldScreenAdapter object to relate the world and screen
     */
    @Override
    public void render(Graphics g, WorldScreenAdapter adapter) {
        Rectangle offset = g.getClipBounds();
        Point playerScreenPosition = adapter.convert(getPosition());

        if (facing == Direction.North) {
            g.drawImage(ImageLoader.e1, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);
        } else if (facing == Direction.South) {
            g.drawImage(ImageLoader.e2, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);
        } else if (facing == Direction.East) {
            g.drawImage(ImageLoader.e3, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);
        } else {
            g.drawImage(ImageLoader.e4, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);
        }
    }
}
