package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;

import java.awt.*;

public abstract class Collectable extends Entity {

    /**
     * Assigns a Position on maze to a particular collectable object
     *
     * @param point Point object having new position to be assigned to a collectable
     */
    public Collectable(Point point)
    {
        super(point);
    }

    /**
     * Returns points awarded if the player collects a particular Collectable object
     *
     * @return Integer value of collecting a Collectable
     */
    public abstract int getPoints();
}
