package ca.sfu.cmpt276.spring2021.group8.project.game.positioning;

import java.awt.Point;

public class CompositePositionValidator implements PositionValidator {
    private PositionValidator[] validators;
    public CompositePositionValidator(PositionValidator ...validators) {
        this.validators = validators;
    }

    public boolean isValidPosition(Point p) {
        for (PositionValidator validator : validators) {
            if (!validator.isValidPosition(p)) {
                return false;
            }
        }

        return true;
    }
}
