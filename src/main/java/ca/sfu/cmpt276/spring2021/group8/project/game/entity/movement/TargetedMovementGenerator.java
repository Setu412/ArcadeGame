package ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement;

import ca.sfu.cmpt276.spring2021.group8.project.game.Direction;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.PositionValidator;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class to generate movement for an entity to get closer to a target
 * @see MovementGenerator
 */
public class TargetedMovementGenerator implements MovementGenerator {
    private final Entity target;
    private final PositionValidator validator;

    /**
     * Generates a targeted movement generator to generate movement in the general direction of the target
     * @param validator a PositionValidator to check whether or not a movement is valid
     * @param target an Entity as the target of our movement
     */
    public TargetedMovementGenerator(PositionValidator validator, Entity target) {
        this.validator = validator;
        this.target = target;
    }

    /**
     * Generates the next move of an entity in the general direction of the target
     * @param currentPosition Point of the current position of the entity to move
     * @return Direction of the next move of an entity in general direction of the target
     * @see MovementGenerator#next(Point)
     */
    @Override
    public Direction next(Point currentPosition) {
        //get an arraylist of movements to test, ordered from the most optimal direct move to the worst
        ArrayList<Direction> movOrder=this.generateMovementOrder(this.getRelativeVerticalPos(currentPosition),this.getRelativeHorizontalPos(currentPosition));
        for (Direction direction : movOrder) {
            if (validator.isValidPosition(direction.getNewPosition(currentPosition))) //check if movement is valid
            {
                return direction;
            }
        }
        return Direction.South; //default direction, could be changed to null
    }

    /**
     * Returns the relative vertical position of the target to the parameter.
     * If located at the same vertical position, returns null
     *
     * @param currentPosition A reference to the current position of the caller
     * @return A Direction of the relative vertical position of the target to the parameter, returns null if same vertical position
     */
    private Direction getRelativeVerticalPos(Point currentPosition)
    {
        // if target is south of the current position
        if (this.target.getPosition().getY() > currentPosition.getY())
        {
            return Direction.South;
        }
        // if target is north of the current position
        else if (this.target.getPosition().getY() < currentPosition.getY())
        {
            return Direction.North;
        }
        // if target has the same vertical position as current position
        else
        {
            return null;
        }
    }

    /**
     * Returns the relative horizontal position of the target to the parameter.
     * If located at the same horizontal position, returns null
     *
     * @param currentPosition A reference to the current position of the caller
     * @return A Direction of the relative horizontal position of the target to the parameter, returns null if same horizontal position
     */
    private Direction getRelativeHorizontalPos(Point currentPosition)
    {
        //if target is east of currentPosition
        if (this.target.getPosition().getX() > currentPosition.getX())
        {
            return Direction.East;
        }
        //if target is west of currentPosition
        else if (this.target.getPosition().getX() < currentPosition.getX())
        {
            return Direction.West;
        }
        // if target has the same horizontal position as currentPosition
        else
        {
            return null;
        }
    }

    /**
     * Generating a list of weighted movement order for the movement generator to try
     * Movement is weighted according to target location
     *
     * @param relVerticalPos A Direction value of the relative vertical position of the target to the current position
     * @param relHorizontalPos A Direction value of the relative horizontal position of the target to the current position
     * @return An ArrayList of Directions values, ordered by movement priority
     */
    private ArrayList<Direction> generateMovementOrder (Direction relVerticalPos, Direction relHorizontalPos)
    {
        ArrayList<Direction> movOrder= new ArrayList<>();
        if(relHorizontalPos==null && relVerticalPos==null)
        {
            return movOrder;
        }
        if(relHorizontalPos==null) //if target and current position has the same horizontal axis
        {
            movOrder.add(relVerticalPos); //adds the primary movement target first
            //next, adds the secondary movement target, which are the orthogonal directions
            if (getCoinFlip()) //because orthogonal directions are equally far from the target, priority is randomized between the two
            {
                movOrder.add(relVerticalPos.getCWOrthogonalDirection());
                movOrder.add(relVerticalPos.getCounterCWOrthogonalDirection());
            }
            else
            {
                movOrder.add(relVerticalPos.getCounterCWOrthogonalDirection());
                movOrder.add(relVerticalPos.getCWOrthogonalDirection());
            }
            movOrder.add(relVerticalPos.getOppositeDirection()); //finally, add the opposite direction as a last resort
        }
        else if(relVerticalPos==null)
        {
            movOrder.add(relHorizontalPos);
            if (getCoinFlip())
            {
                movOrder.add(relHorizontalPos.getCWOrthogonalDirection());
                movOrder.add(relHorizontalPos.getCounterCWOrthogonalDirection());
            }
            else
            {
                movOrder.add(relHorizontalPos.getCounterCWOrthogonalDirection());
                movOrder.add(relHorizontalPos.getCWOrthogonalDirection());
            }
            movOrder.add(relHorizontalPos.getOppositeDirection());
        }
        else //If target is in an intermediate direction (eg: northwest) relatively to the current position
        {
            if(getCoinFlip()) //Randomizing the primary movement
            {
                movOrder.add(relHorizontalPos);
                movOrder.add(relVerticalPos);
            }
            else
            {
                movOrder.add(relVerticalPos);
                movOrder.add(relHorizontalPos);
            }
            if(getCoinFlip()) //Randomizing the secondary movement
            {
                movOrder.add(relHorizontalPos.getOppositeDirection());
                movOrder.add(relVerticalPos.getOppositeDirection());
            }
            else
            {
                movOrder.add(relVerticalPos.getOppositeDirection());
                movOrder.add(relHorizontalPos.getOppositeDirection());
            }
        }
        return movOrder;
    }

    /**
     * generates a random value of true/false, used for randomizing weighted movement
     * @return randomized boolean t/f
     */
    private boolean getCoinFlip()
    {
        Random random=new Random();
        return random.nextInt()%2==0;
    }
}
