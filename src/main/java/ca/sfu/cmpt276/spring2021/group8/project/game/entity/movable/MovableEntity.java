package ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable;

import ca.sfu.cmpt276.spring2021.group8.project.game.Maze;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;

import java.awt.*;

public abstract class MovableEntity extends Entity {

    public MovableEntity(Point point)
    {
        super(point);
    }
    protected void tryMove(Maze maze, Point position) {
        if (!maze.isValidPosition(position)) {
            return;
        }
        this.setPosition(position);
    }
}
