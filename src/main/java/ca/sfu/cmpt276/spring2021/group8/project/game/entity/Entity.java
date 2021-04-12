package ca.sfu.cmpt276.spring2021.group8.project.game.entity;

import ca.sfu.cmpt276.spring2021.group8.project.game.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.TargetedMovementGenerator;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.PositionValidator;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.MovementGenerator;

import java.awt.*;

/**
 * Entity is an object on the game board
 */
public abstract class Entity implements PositionValidator {

    private Point position = new Point();


    /**
     * Constructs Entity object and assigns initial position on maze to it
     *
     * @param startPosition Point object having new position to a be assigned to an Entity
     */
    protected Entity(Point startPosition) {
        this.position = startPosition;
    }

    /**
     * Returns the position of a particular entity in form of Point object
     *
     * @return Point object containing the position of entity
     */
    public Point getPosition() {
        return new Point(position);
    }

    /**
     * Assigns the entity a new position on the maze
     *
     * @param position Point object having position to a be assigned to an Entity
     */
    protected void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Get a targeted movement generator, where the this entity is the target for the movement generator
     */
    public MovementGenerator generateTargetedMovementGenerator() {
        return new TargetedMovementGenerator(this);
    }

    /**
     * Checks if the position is valid
     * 
     * @param p Point object that is the coordinates of the next move
     * @return True if the position is valid, false if position is not valid
     */
    @Override
    public boolean isValidPosition(Point p) {
        return !p.equals(this.position);
    }

    /**
     * Renders the entity onto screen
     *
     * @param g Graphic object to draw Image onto screen
     * @param s WorldScreenAdapter object used to relate the size of world screen
     */
    abstract public void render(Graphics g, WorldScreenAdapter s);
}
