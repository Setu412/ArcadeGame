package ca.sfu.cmpt276.spring2021.group8.project.game.entity;

import ca.sfu.cmpt276.spring2021.group8.project.game.*;
import java.awt.*;

public abstract class Entity {
    final protected Maze maze; //change to tryMove parameter
    private Point position = new Point();

    protected Entity(Maze maze) {
        this.maze = maze;
    }

    public Point getPosition() {
        return new Point(position);
    }

    protected void move(Point position) {
        this.position = position;
    }

    protected void tryMove(Point position, int[] originalXY) {
        if (!maze.isValidPosition(position, originalXY)) {
            return;
        }

        this.position = position;
    }

    abstract public void render(Graphics g, WorldScreenAdapter s);
}
