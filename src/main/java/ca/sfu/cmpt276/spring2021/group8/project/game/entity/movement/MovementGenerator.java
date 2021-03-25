package ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement;

import java.awt.*;

/**
 * Interface for the automated movement of an entity
 * @see TargetedMovementGenerator
 */
public interface MovementGenerator {
    /**
     * Gets the next direction of the entity
     * @param currentPosition The current position of the entity
     * @return Direction object of the next direction of the entity
     */
    Direction next(Point currentPosition);
}
