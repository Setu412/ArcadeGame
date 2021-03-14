package ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement;

import ca.sfu.cmpt276.spring2021.group8.project.game.Direction;
import ca.sfu.cmpt276.spring2021.group8.project.game.Maze;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class TargetedMovementGenerator implements MovementGenerator {
    private final Point target;
    private final Maze maze;

    public TargetedMovementGenerator(Maze maze, Entity target) {
        this.maze = maze;
        this.target = target.getPosition();
    }

    //TODO: change currentPosition into a field
    public Direction next(Point currentPosition) {
        //get an arraylist of movements to test, ordered from the most optimal direct move to the worst
        ArrayList<Direction> movOrder=this.generateMovementOrder(this.getRelativeVerticalPos(currentPosition),this.getRelativeHorizontalPos(currentPosition));
        for (int i=0;i< movOrder.size();i++)
        {
            if (maze.isValidPosition(movOrder.get(i).getNewPosition(currentPosition))) //check if movement is valid
            {
                return movOrder.get(i);
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
        if (this.target.getY() > currentPosition.getY())
        {
            return Direction.South;
        }
        // if target is north of the current position
        else if (this.target.getY() < currentPosition.getY())
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
        if (this.target.getX() > currentPosition.getX())
        {
            return Direction.East;
        }
        //if target is west of currentPosition
        else if (this.target.getX() < currentPosition.getX())
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
     * @param relVerticalPos A Direction value of the relative vertical position of the target to the current position
     * @param relHorizontalPos A Direction value of the relative horizontal position of the target to the current position
     * @return An ArrayList of Directions values, ordered by movement priority
     */
    private ArrayList<Direction> generateMovementOrder (Direction relVerticalPos, Direction relHorizontalPos)
    {
        ArrayList<Direction> movOrder= new ArrayList<>();
        if(relHorizontalPos==null && relHorizontalPos==null)
        {
            return null;
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

    private boolean getCoinFlip()  //generates a random coinflip, used for randomizing weighted movement
    {
        Random random=new Random();
        return random.nextInt()%2==0;
    }
}
