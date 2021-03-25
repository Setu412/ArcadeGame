package ca.sfu.cmpt276.spring2021.group8.project.game.positioning;

import java.awt.Point;

/**
 * Interface for position validation
 */
public interface PositionValidator {
    boolean isValidPosition(Point p);
}
