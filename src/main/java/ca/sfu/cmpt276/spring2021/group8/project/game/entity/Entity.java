package ca.sfu.cmpt276.spring2021.group8.project.game.entity;

import ca.sfu.cmpt276.spring2021.group8.project.game.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.TargetedMovementGenerator;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.MovementGenerator;

import javax.swing.text.Position;
import java.awt.*;

public abstract class Entity {

    private Point position = new Point();

    protected Entity(Point startPosition) {
        this.position = startPosition;
    }

    public Entity() {
    }

    public Point getPosition() {
        return new Point(position);
    }

    public void setPosition(Point Position){
        this.position = Position;
    }

    public MovementGenerator getTargetedMovementGenerator(Maze maze) {
        return new TargetedMovementGenerator(maze, this);
    }

    protected void tryMove(Maze maze, Point position) {
        if (!maze.isValidPosition(position)) {
            return;
        }

        this.position = position;
    }

    abstract public void render(Graphics g, WorldScreenAdapter s);
}
