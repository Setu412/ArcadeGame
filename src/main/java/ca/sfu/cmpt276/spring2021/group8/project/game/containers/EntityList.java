package ca.sfu.cmpt276.spring2021.group8.project.game.containers;

import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.PositionValidator;

import java.awt.Point;
import java.util.LinkedList;

/**
 * EntityList is a list to store multiple entities of the same type
 *
 * @param <T> The type of the Entity
 */
public class EntityList<T extends Entity> extends LinkedList<T> implements PositionValidator {

    /**
     * Ensure none of the entities already exists on the position passed as parameter
     *
     * @param p Point object containing a new Position
     * @return True if none of the entities already exists on new Position, return false if any entity already exists
     */
    public boolean isValidPosition(Point p) {
        for (T entity : this) {
            if (entity.getPosition().equals(p)) {
                return false;
            }
        }

        return true;
    }
}
