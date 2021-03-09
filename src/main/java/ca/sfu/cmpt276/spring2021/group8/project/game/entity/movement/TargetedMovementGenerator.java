package ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement;

import ca.sfu.cmpt276.spring2021.group8.project.game.Direction;
import ca.sfu.cmpt276.spring2021.group8.project.game.Maze;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;
import java.awt.Point;

public class TargetedMovementGenerator implements MovementGenerator {
    private final Entity entity;
    private final Maze maze;

    public TargetedMovementGenerator(Maze maze, Entity entity) {
        this.maze = maze;
        this.entity = entity;
    }

    public Direction next() {
        Point pos = entity.getPosition();
        maze.isValidPosition(pos);
        // TODO figure out best direction to reach the entity
        return Direction.South;
    }
}
