package ca.sfu.cmpt276.spring2021.group8.project.game.containers;

import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.PositionValidator;

import java.awt.Point;
import java.util.LinkedList;

public class EntityList<T extends Entity> extends LinkedList<T> implements PositionValidator {
    public boolean isValidPosition(Point p) {
        for (T entity : this) {
            if (entity.getPosition().equals(p)) {
                return false;
            }
        }

        return true;
    }
}
