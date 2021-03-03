package ca.sfu.cmpt276.spring2021.group8.project.game;

import java.awt.*;

public class WorldScreenAdapter {
    final private Point worldSize;
    final private Point gridSpacing;

    public WorldScreenAdapter(Point worldSize, Point gridSpacing) {
        this.worldSize = new Point(worldSize);
        this.gridSpacing = new Point(gridSpacing);
    }

    public Point worldSize() {
        return new Point(worldSize);
    }

    public int worldWidth() {
        return worldSize.x;
    }

    public int worldHeight() {
        return worldSize.y;
    }

    public Point gridSpacing() {
        return new Point(gridSpacing);
    }

    public int gridHorizontalSpacing() {
        return gridSpacing.x;
    }

    public int gridVerticalSpacing() {
        return gridSpacing.y;
    }

    public Point gridSize() {
        return new Point(worldSize.x * gridSpacing.x, worldSize.y * gridSpacing.y);
    }

    public Point convert(int x, int y) {
        return new Point(x * gridSpacing.x, y * gridSpacing.y);
    }

    public Point convert(Point p) {
        return convert(p.x, p.y);
    }
}
