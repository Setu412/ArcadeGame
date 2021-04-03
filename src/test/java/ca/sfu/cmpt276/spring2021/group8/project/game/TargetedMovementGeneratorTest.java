package ca.sfu.cmpt276.spring2021.group8.project.game;

import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable.Player;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.Direction;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.TargetedMovementGenerator;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.PositionValidator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.awt.*;

public class TargetedMovementGeneratorTest {
    private class TestMaze implements PositionValidator {
        private final int width=7;
        private final int height=9;
        private int[][] maze={
                {4,4,4,4,4,4,4},
                {4,0,0,0,0,0,4},
                {4,0,4,4,4,0,4},
                {4,0,0,0,0,0,4},
                {4,0,0,0,0,0,4},
                {4,0,0,0,0,0,4},
                {4,0,4,4,4,0,4},
                {4,0,0,0,0,0,4},
                {4,4,4,4,4,4,4}
        };

        @Override
        public boolean isValidPosition(Point p) {
            // Check if next position is out of bounds
            if (p.x < 1) {
                return false;
            } else if (p.x >= width - 1) {
                return false;
            }

            if (p.y < 1) {
                return false;
            } else if (p.y >= height - 1) {
                return false;
            }

            // Check if next position is an empty space
            return maze[p.y][p.x] == 0;
        }
    }

    TestMaze testMaze=new TestMaze();
    TargetedMovementGenerator targetedMovementGenerator;

    @BeforeEach
    public void setup()
    {
        Player target=new Player(new Point(3,4));
        targetedMovementGenerator=new TargetedMovementGenerator(target);
        System.out.println("calling setup");
    }

    /*
    Current maze setup:
                {4,4,4,4,4,4,4},
                {4,0,0,0,0,0,4},
                {4,0,4,4,4,0,4},
                {4,0,0,0,0,0,4},
                {4,0,0,T,0,0,4},
                {4,0,0,0,0,0,4},
                {4,0,4,4,4,0,4},
                {4,0,0,0,0,0,4},
                {4,4,4,4,4,4,4}
     Where T is the target, 4 are walls, and 0 are empty spaces
     Note: to quickly find an item:
        1. ctrl+f
        2. press the In Selection button (or ctrl + alt + e)
        3. select the maze above
        4. find the item with the given token (eg, T to find the target
     */

    //Testing movement generation when target is directly south of entity with no walls in between
    @Test
    public void testTargetSouthNoWalls()
    {
        /*
    Current maze setup:
                {4,4,4,4,4,4,4},
                {4,0,0,0,0,0,4},
                {4,0,4,4,4,0,4},
                {4,0,0,E,0,0,4},
                {4,0,0,T,0,0,4},
                {4,0,0,0,0,0,4},
                {4,0,4,4,4,0,4},
                {4,0,0,0,0,0,4},
                {4,4,4,4,4,4,4}
     Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from
     Note: to quickly find an item:
        1. ctrl+f
        2. press the In Selection button (or ctrl + alt + e)
        3. select the maze above
        4. find the item with the given token (eg, T to find the target
     */
        Direction nextMovement= targetedMovementGenerator.next(testMaze,new Point(3,3));
        assert(nextMovement==Direction.South);
    }
}
