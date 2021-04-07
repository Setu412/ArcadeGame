package ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable;

import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.Direction;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.PositionValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class MovableEntityTest {
    class MockMovableEntity extends MovableEntity
    {

        public MockMovableEntity(Point point) {
            super(point);
        }

        @Override
        public void render(Graphics g, WorldScreenAdapter s) {
            return;
        }
    }

    class MockMaze implements PositionValidator
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

    MockMaze mockMaze=new MockMaze();
    MockMovableEntity mockMovableEntity;

    @BeforeEach
    public void setup()
    {
        mockMovableEntity=new MockMovableEntity(new Point(1,2));
    }

    @Test
    public void testValidMovement()
    {
        /*
        Current maze setup:
                    {4,4,4},
                    {4,0,4},
                    {4,E,4},
                    {4,4,4}
         Where E is the entity, 4 are walls, and 0 are empty spaces
    */
        //Moves the entity north to test valid movement
        mockMovableEntity.tryMove(mockMaze, Direction.North.getNewPosition(mockMovableEntity.getPosition()),Direction.North);

        /*
        Maze setup after moving north:
                    {4,4,4},
                    {4,E,4},
                    {4,0,4},
                    {4,4,4}
         Where E is the entity, 4 are walls, and 0 are empty spaces
        */

        assert (mockMovableEntity.getPosition().equals(new Point(1,1)));
    }

    @Test
    public void testInvalidMovement()
    {
        /*
        Current maze setup:
                    {4,4,4},
                    {4,0,4},
                    {4,E,4},
                    {4,4,4}
         Where E is the entity, 4 are walls, and 0 are empty spaces
    */

        //Moves the entity east to test valid movement
        mockMovableEntity.tryMove(mockMaze, Direction.East.getNewPosition(mockMovableEntity.getPosition()),Direction.East);

        /*
        Maze setup after moving east:
                    {4,4,4},
                    {4,0,4},
                    {4,E,4},
                    {4,4,4}
         Where E is the entity, 4 are walls, and 0 are empty spaces
        */

        assert (mockMovableEntity.getPosition().equals(new Point(1,2)));
    }

}