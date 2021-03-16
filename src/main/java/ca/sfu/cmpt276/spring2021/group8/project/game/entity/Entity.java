package ca.sfu.cmpt276.spring2021.group8.project.game.entity;

import ca.sfu.cmpt276.spring2021.group8.project.game.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.TargetedMovementGenerator;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.PositionValidator;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.MovementGenerator;

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

    protected void setPosition(Point position) {
        this.position = position;
    }

    public MovementGenerator getTargetedMovementGenerator(PositionValidator validator) {
        return new TargetedMovementGenerator(validator, this);
    }

    abstract public void render(Graphics g, WorldScreenAdapter s);
}
