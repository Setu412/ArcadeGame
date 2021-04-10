package ca.sfu.cmpt276.spring2021.group8.project.game.positioning;

import java.awt.Point;

/**
 * CompositePositionValidator validates the position of a move
 */
public class CompositePositionValidator implements PositionValidator {
    private PositionValidator[] validators;

    /**
     * Sets the validators
     * @param validators Validators for valid moves
     */
    public CompositePositionValidator(PositionValidator ...validators) {
        this.validators = validators;
    }

    /**
     * Checks if the position is valid
     * @param p Point object that is the coordinates of the next move
     * @return True if the position is valid, false if position is not valid
     */
    public boolean isValidPosition(Point p) {
        for (PositionValidator validator : validators) {
            if (validator != null && !validator.isValidPosition(p)) {
                return false;
            }
        }

        return true;
    }
}
