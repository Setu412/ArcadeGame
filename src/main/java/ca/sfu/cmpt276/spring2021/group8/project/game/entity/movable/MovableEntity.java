package ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable;

import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.Direction;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.PositionValidator;

import java.awt.*;

/**
 * MovableEntity is used by entities that can move
 */
public abstract class MovableEntity extends Entity {
    protected Direction facing;

    /**
     * Sets the Point position to point
     *
     * @param point The Point object with coordinates used to set the position
     */
    public MovableEntity(Point point) {
        super(point);
    }

    /**
     * Tries to move the entity to the position it wants to move to
     *
     * @param validator Checks if move is valid
     * @param position The current position of the entity
     * @param d The Direction that the entity tries to move towards
     */
    protected void tryMove(PositionValidator validator, Point position, Direction d) {
        facing = d;
        if (!validator.isValidPosition(position)) {
            return;
        }

        this.setPosition(position);
    }
}
