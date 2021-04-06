package ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable;

import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.Direction;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.MovementGenerator;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.PositionValidator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.awt.*;

public class EnemyTest {
    class TestMaze implements PositionValidator
    {
        private final int width=3;
        private final int height=4;
        private int[][] maze={
                {4,4,4},
                {4,0,4},
                {4,0,4},
                {4,4,4}
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

    //generates north movement only for the sake of testing
    class testNorthGenerator implements MovementGenerator
    {
        @Override
        public Direction next(PositionValidator validator, Point currentPosition) {
            return Direction.North;
        }
    }

    TestMaze testMaze=new TestMaze();
    MovementGenerator movementGenerator=new testNorthGenerator();
    Enemy enemy;


    @Test
    public void testEnemyValidMovement()
    {
        //setting up enemy initial position
        enemy = new Enemy(movementGenerator,new Point(1,2));

        /*
        Current maze setup:
                    {4,4,4},
                    {4,0,4},
                    {4,E,4},
                    {4,4,4}
         Where E is the enemy, 4 are walls, and 0 are empty spaces
         */

        //moves the enemy north one step
        enemy.move(testMaze);

        /*
        Expected maze setup:
                    {4,4,4},
                    {4,E,4},
                    {4,0,4},
                    {4,4,4}
         Where E is the enemy, 4 are walls, and 0 are empty spaces
         */
        assert (enemy.getPosition().equals(new Point(1,1)));
    }

    @Test
    public void testEnemyInvalidMovement()
    {
        //setting up enemy initial position
        enemy = new Enemy(movementGenerator,new Point(1,1));

        /*
        Current maze setup:
                    {4,4,4},
                    {4,E,4},
                    {4,0,4},
                    {4,4,4}
         Where E is the enemy, 4 are walls, and 0 are empty spaces
         */

        //moves the enemy north one step
        enemy.move(testMaze);

        /*
        Expected maze setup:
                    {4,4,4},
                    {4,E,4},
                    {4,0,4},
                    {4,4,4}
         Where E is the enemy, 4 are walls, and 0 are empty spaces
         */
        assert (enemy.getPosition().equals(new Point(1,1)));
    }

}
