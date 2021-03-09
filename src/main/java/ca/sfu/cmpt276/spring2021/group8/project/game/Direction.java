package ca.sfu.cmpt276.spring2021.group8.project.game;

import java.awt.Point;

public enum Direction {
    North,
    East,
    South,
    West;

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
}
