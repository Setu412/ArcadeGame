package ca.sfu.cmpt276.spring2021.group8.project.game;

import java.awt.*;

/**
 * WorldScreenAdapter relates the world sizes to the screen
 */
public class WorldScreenAdapter {
    final private Point worldSize;
    final private Point gridSpacing;

    /**
     * Sets the size of the worldSize and sets how the grids should be spaced
     * @param worldSize The size of the world
     * @param gridSpacing The size of the grid spacing
     */
    public WorldScreenAdapter(Point worldSize, Point gridSpacing) {
        this.worldSize = new Point(worldSize);
        this.gridSpacing = new Point(gridSpacing);
    }

    /**
     * Returns the size of the world
     *
     * @return Point object with the size of the world
     */
    public Point worldSize() {
        return new Point(worldSize);
    }

    /**
     * Returns the width of the world
     * @return Width of the world size
     */
    public int worldWidth() {
        return worldSize.x;
    }

    /**
     * Returns the height of the world
     * @return Height of the world size
     */
    public int worldHeight() {
        return worldSize.y;
    }

    /**
     * Returns the size of the grid spacing
     * @return Point object of the grid spacing
     */
    public Point gridSpacing() {
        return new Point(gridSpacing);
    }

    /**
     * Returns the horizontal spacing of the grid
     * @return Size of the horizontal spacing
     */
    public int gridHorizontalSpacing() {
        return gridSpacing.x;
    }

    /**
     * Returns the vertical spacing of the grid
     * @return Size of the vertical spacing
     */
    public int gridVerticalSpacing() {
        return gridSpacing.y;
    }

    /**
     * Returns the size of the grid spacing integrated with the world size
     * @return The size off the grid
     */
    public Point gridSize() {
        return new Point(worldSize.x * gridSpacing.x, worldSize.y * gridSpacing.y);
    }

    /**
     * Returns the size on the grid based on x and y
     * @param x The horizontal size
     * @param y The vertical size
     * @return The size of the grid with respect to x and y
     */
    public Point convert(int x, int y) {
        return new Point(x * gridSpacing.x, y * gridSpacing.y);
    }

    /**
     * Returns the size of the grid given a point
     * @param p Point object with size to convert
     * @return The size of the grid with respect to x and y of p
     */
    public Point convert(Point p) {
        return convert(p.x, p.y);
    }
}
