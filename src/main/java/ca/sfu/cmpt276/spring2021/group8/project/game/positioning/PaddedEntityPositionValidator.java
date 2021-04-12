package ca.sfu.cmpt276.spring2021.group8.project.game.positioning;

import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;

import java.awt.Point;

public class PaddedEntityPositionValidator implements PositionValidator {
    private Entity entity;
    private int radius;

    /**
     * Sets the entity and radius
     * @param entity Entity for valid moves
     * @param radius Radius for valid moves
     */
    public PaddedEntityPositionValidator(Entity entity, int radius) {
        this.entity = entity;
        this.radius = radius;
    }

    /**
     * Sets the entity
     * @param entity Entity for valid moves
     */
    public PaddedEntityPositionValidator(Entity entity) {
        this(entity, 2);
    }

    /**
     * Checks if the position is valid
     * 
     * @param p Point object that is the coordinates of the next move
     * @return True if the position is valid, false if position is not valid
     */
    public boolean isValidPosition(Point p) {
        Point pos = entity.getPosition();
        return Math.abs(pos.x - p.x) > radius || Math.abs(pos.y - p.y) > radius;
    }
}
