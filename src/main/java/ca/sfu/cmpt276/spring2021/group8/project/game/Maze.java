package ca.sfu.cmpt276.spring2021.group8.project.game;

import java.awt.Point;

public class Maze {
    private Point size;

    public Maze(Point size) {
        this.size = new Point(size);
    }

    public Maze(int width, int height) {
        this(new Point(width, height));
    }

    public Point startPosition() {
        return new Point(0, 0);
    }

    public Point getSize() {
        return new Point(size);
    }

    public boolean isValidPosition(Point p) {
        if (p.x < 0) {
            return false;
        } else if (p.x >= size.x) {
            return false;
        }

        if (p.y < 0) {
            return false;
        } else if (p.y >= size.y) {
            return false;
        }

        return true;
    }
}
