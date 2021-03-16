package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;

import java.awt.*;

public abstract class Collectable extends Entity {
    public Collectable(Point point)
    {
        super(point);
    }

    public abstract int getPoints();
}
