package ca.sfu.cmpt276.spring2021.group8.project.game.entity.movingentities;

import ca.sfu.cmpt276.spring2021.group8.project.game.Maze;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;

import java.awt.*;

public abstract class MovingEntities extends Entity {

    public MovingEntities(Point point)
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
