package ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement;

import java.awt.Point;

/**
 * Direction is the direction that can be moved towards
 */
public enum Direction {
    // Ordered in clockwise fashion
    North,
    East,
    South,
    West,
    None;

    /**
     * Returns a new Point one unit in the direction.
     */
    public Point getNewPosition(Point p) {
        Point newPoint = new Point(p);
        switch (this) {
            case West:
                newPoint.x -= 1;
                break;

            case North:
                newPoint.y -= 1;
                break;

            case East:
                newPoint.x += 1;
                break;

            case South:
                newPoint.y += 1;
                break;

            case None:
                break;
        }
        return newPoint;
    }

    /**
     * Returns the opposite direction of the caller
     * eg: North.getOppositeDirection() will return South
     *
     * @return The opposite direction of the caller
     */
    public Direction getOppositeDirection()
    {
        switch (this) {
            case West:
                return East;

            case North:
                return South;

            case East:
                return West;

            case South:
                return North;
        }
        return None;
    }

    /**
     * Returns the first perpendicular direction from the caller in clockwise order
     * eg: North.getCWOrthogonalDirection() will return East
     *
     * @return the first perpendicular direction from the caller in clockwise order
     */
    public Direction getCWOrthogonalDirection()
    {
        switch (this) {
            case West:
                return North;

            case North:
                return East;

            case East:
                return South;

            case South:
                return West;
        }
        return None;
    }

    /**
     * Returns the first perpendicular direction from the caller in counter clockwise order
     * eg: North.getCounterCWOrthogonalDirection() will return West
     *
     * @return the first perpendicular direction from the caller in counter clockwise order
     */
    public Direction getCounterCWOrthogonalDirection()
    {
        switch (this) {
            case West:
                return South;

            case North:
                return West;

            case East:
                return North;

            case South:
                return East;
        }
        return None;
    }

}
