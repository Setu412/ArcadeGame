package ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable;

import ca.sfu.cmpt276.spring2021.group8.project.game.Direction;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.PositionValidator;

import java.awt.*;

public abstract class MovableEntity extends Entity {
    protected Direction facing;

    public MovableEntity(Point point) {
        super(point);
    }

    protected void tryMove(PositionValidator validator, Point position, Direction d) {
        facing = d;
        if (!validator.isValidPosition(position)) {
            return;
        }

        this.setPosition(position);
    }
}
