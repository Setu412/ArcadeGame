package ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement;

import ca.sfu.cmpt276.spring2021.group8.project.game.Direction;
import ca.sfu.cmpt276.spring2021.group8.project.game.Maze;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;
import java.awt.Point;

public class TargetedMovementGenerator implements MovementGenerator {
    private final Entity target;
    private final Maze maze;

    public TargetedMovementGenerator(Maze maze, Entity target) {
        this.maze = maze;
        this.target = target;
    }

    public Direction next(Point currentPosition) {
        Point pos = target.getPosition();
        maze.isValidPosition(pos);
        // TODO figure out best direction to reach the entity

        return Direction.South;
    }
}
