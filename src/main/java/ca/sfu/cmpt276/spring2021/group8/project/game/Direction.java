package ca.sfu.cmpt276.spring2021.group8.project.game;

import java.awt.Point;
import java.nio.file.DirectoryIteratorException;

/**
 * Direction is the direction that can be moved towards
 */
public enum Direction {
    // Ordered in clockwise fashion
    // Note: Do NOT change the order, changing the order modifies methods defined below
    North,
    East,
    South,
    West;

    public final static int SIZE=Direction.values().length;
    public final static Direction[] values=values();

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
        return values[(this.ordinal()+2)%SIZE];
    }

    /**
     * Returns the first perpendicular direction from the caller in clockwise order
     * eg: North.getCWOrthogonalDirection() will return East
     *
     * @return the first perpendicular direction from the caller in clockwise order
     */
    public Direction getCWOrthogonalDirection()
    {
        return values[(this.ordinal()+1)%SIZE];
    }

    /**
     * Returns the first perpendicular direction from the caller in counter clockwise order
     * eg: North.getCounterCWOrthogonalDirection() will return West
     *
     * @return the first perpendicular direction from the caller in counter clockwise orde
     */
    public Direction getCounterCWOrthogonalDirection()
    {
        return values[(this.ordinal()+3)%SIZE];
    }

}
