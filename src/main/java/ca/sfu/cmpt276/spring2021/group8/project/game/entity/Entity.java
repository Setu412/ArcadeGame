package ca.sfu.cmpt276.spring2021.group8.project.game.entity;

import ca.sfu.cmpt276.spring2021.group8.project.game.*;
import java.awt.*;

public abstract class Entity {
    private Point position = new Point();

    public Point getPosition() {
        return new Point(position);
    }

    protected void move(Point position) {
        this.position = position;
    }

    protected void tryMove(Maze maze, Point position) {
        if (!maze.isValidPosition(position)) {
            return;
        }

        this.position = position;
    }

    abstract public void render(Graphics g, WorldScreenAdapter s);
}
