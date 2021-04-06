package ca.sfu.cmpt276.spring2021.group8.project.game.movement;

import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable.Player;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.Direction;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.TargetedMovementGenerator;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.PositionValidator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.awt.*;

public class TargetedMovementGeneratorTest {
    //mock maze class to decouple from actual maze class
    private class MockMaze implements PositionValidator {
        private final int width=9;
        private final int height=9;
        private int[][] maze={
                {4,4,4,4,4,4,4,4,4},
                {4,0,0,0,0,0,0,0,4},
                {4,0,0,4,4,4,0,0,4},
                {4,0,4,0,0,0,4,0,4},
                {4,0,4,0,0,0,4,0,4},
                {4,0,4,0,0,0,4,0,4},
                {4,0,0,4,4,4,0,0,4},
                {4,0,0,0,0,0,0,0,4},
                {4,4,4,4,4,4,4,0,4}
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

    //a mock target class to decouple test from actual entity classes
    class MockTarget extends Entity
    {
        protected MockTarget(Point startPosition) {
            super(startPosition);
        }

        @Override
        public void render(Graphics g, WorldScreenAdapter s) {
            return;
        }
    }

    MockMaze mockMaze =new MockMaze();
    TargetedMovementGenerator targetedMovementGenerator;

    @BeforeEach
    public void setup()
    {
        MockTarget target=new MockTarget(new Point(4,4));
        targetedMovementGenerator=new TargetedMovementGenerator(target);
    }

    /*
    Current maze setup:
                {4,4,4,4,4,4,4,4,4},
                {4,0,0,0,0,0,0,0,4},
                {4,0,0,4,4,4,0,0,4},
                {4,0,4,0,0,0,4,0,4},
                {4,0,4,0,T,0,4,0,4},
                {4,0,4,0,0,0,4,0,4},
                {4,0,0,4,4,4,0,0,4},
                {4,0,0,0,0,0,0,0,4},
                {4,4,4,4,4,4,4,0,4}
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
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,E,0,4,0,4},
            {4,0,4,0,T,0,4,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        Direction nextMovement= targetedMovementGenerator.next(mockMaze,new Point(4,3));
        assert(nextMovement==Direction.South);
    }

    //Testing movement generation when target is directly north of entity with no walls in between
    @Test
    public void testTargetNorthNoWalls()
    {
    /*
Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,4,0,T,0,4,0,4},
            {4,0,4,0,E,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        Direction nextMovement= targetedMovementGenerator.next(mockMaze,new Point(4,5));
        assert(nextMovement==Direction.North);
    }

    //Testing movement generation when target is directly east of entity with no walls in between
    @Test
    public void testTargetEastNoWalls()
    {
    /*
Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,4,E,T,0,4,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        Direction nextMovement= targetedMovementGenerator.next(mockMaze,new Point(3,4));
        assert(nextMovement==Direction.East);
    }

    //Testing movement generation when target is directly west of entity with no walls in between
    @Test
    public void testTargetWestNoWalls()
    {
    /*
Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,4,0,T,E,4,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        Direction nextMovement= targetedMovementGenerator.next(mockMaze,new Point(5,4));
        assert(nextMovement==Direction.West);
    }

    //Testing movement generation when target is southwest of entity with no walls in between
    @Test
    public void testTargetSouthWestNoWalls()
    {
    /*
Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,0,E,4,0,4},
            {4,0,4,0,T,0,4,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        for (int i=0;i<10;i++) //testing multiple times because direction will be randomized
        {
            Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(5, 3));
            assert (nextMovement == Direction.West || nextMovement == Direction.South);
        }
    }

    //Testing movement generation when target is southeast of entity with no walls in between
    @Test
    public void testTargetSouthEastNoWalls()
    {
    /*
Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,E,0,0,4,0,4},
            {4,0,4,0,T,0,4,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        for (int i=0;i<10;i++) //testing multiple times because direction will be randomized
        {
            Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(3, 3));
            assert (nextMovement == Direction.East || nextMovement == Direction.South);
        }
    }

    //Testing movement generation when target is northwest of entity with no walls in between
    @Test
    public void testTargetNorthWestNoWalls()
    {
    /*
Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,4,0,T,0,4,0,4},
            {4,0,4,0,0,E,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        for (int i=0;i<10;i++) //testing multiple times because direction will be randomized
        {
            Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(5, 5));
            assert (nextMovement == Direction.West || nextMovement == Direction.North);
        }
    }

    //Testing movement generation when target is northeast of entity with no walls in between
    @Test
    public void testTargetNorthEastNoWalls()
    {
    /*
Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,4,0,T,0,4,0,4},
            {4,0,4,E,0,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        for (int i=0;i<10;i++) //testing multiple times because direction will be randomized
        {
            Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(3, 5));
            assert (nextMovement == Direction.East || nextMovement == Direction.North);
        }
    }
    //Testing movement generation when target is directly south of entity with walls in between
    @Test
    public void testTargetSouthWalls()
    {
    /*
Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,E,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,4,0,T,0,4,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        for (int i=0;i<10;i++) {
            Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(4, 1));
            assert (nextMovement == Direction.East || nextMovement == Direction.West);
        }
    }

    //Testing movement generation when target is directly north of entity with walls in between
    @Test
    public void testTargetNorthWalls()
    {
    /*
Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,4,0,T,0,4,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,E,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        for (int i=0;i<10;i++) {
            Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(4, 7));
            assert (nextMovement == Direction.East || nextMovement == Direction.West);
        }
    }

    //Testing movement generation when target is directly east of entity with  walls in between
    @Test
    public void testTargetEastWalls()
    {
    /*
Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,E,4,0,T,0,4,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        for (int i=0;i<10;i++) {
            Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(1, 4));
            assert (nextMovement == Direction.North || nextMovement == Direction.South);
        }
    }

    //Testing movement generation when target is directly west of entity with  walls in between
    @Test
    public void testTargetWestWalls()
    {
    /*
Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,4,0,T,0,4,E,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        for (int i=0;i<10;i++) {
            Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(7, 4));
            assert (nextMovement == Direction.North || nextMovement == Direction.South);
        }
    }

    //Testing movement generation when target is southwest of entity with  walls in the west
    @Test
    public void testTargetSouthWestWallsWest() {
    /*
    Testing if blocked by wall in the west

Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,0,0,4,E,4},
            {4,0,4,0,T,0,4,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(7, 3));
        assert (nextMovement == Direction.South);
    }

    //Testing movement generation when target is southwest of entity with  walls in the south
    @Test
    public void testTargetSouthWestWallsSouth() {

    /*
    Testing if blocked by wall in the south

Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,E,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,4,0,T,0,4,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(5, 1));
        assert (nextMovement == Direction.West);
    }

    //Testing movement generation when target is southwest of entity with  walls in the south and west
    @Test
    public void testTargetSouthWestWallsSouthAndWest(){

    /*
    Testing if blocked by wall in the south and west

Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,E,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,4,0,T,0,4,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        for (int i=0;i<10;i++) {
            Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(6, 2));
            assert (nextMovement == Direction.North || nextMovement == Direction.East);
        }
    }

    //Testing movement generation when target is southeast of entity with  walls in East
    @Test
    public void testTargetSouthEastWallsEast() {
    /*
    Testing if blocked by wall in the east

Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,E,4,0,0,0,4,0,4},
            {4,0,4,0,T,0,4,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(1, 3));
        assert (nextMovement == Direction.South);
    }

    //Testing movement generation when target is southeast of entity with  walls in south
    @Test
    public void testTargetSouthEastWallsSouth() {

    /*
    Testing if blocked by wall in the south

Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,E,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,4,0,T,0,4,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(3, 1));
        assert (nextMovement == Direction.East);
    }

    //Testing movement generation when target is southeast of entity with  walls in south and east
    @Test
    public void testTargetSouthEastWallsSouthAndEast() {

    /*
    Testing if blocked by wall in the south and east

Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,E,4,4,4,0,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,4,0,T,0,4,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        for (int i=0;i<10;i++) {
            Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(2, 2));
            assert (nextMovement == Direction.North || nextMovement == Direction.West);
        }
    }

    //Testing movement generation when target is northwest of entity with walls in west
    @Test
    public void testTargetNorthWestWallsWest() {
   /*
    Testing if blocked by wall in the west

Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,4,0,T,0,4,0,4},
            {4,0,4,0,0,0,4,E,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(7, 5));
        assert (nextMovement == Direction.North);
    }

    //Testing movement generation when target is northwest of entity with walls in north
    @Test
    public void testTargetNorthWestWallsNorth() {

    /*
    Testing if blocked by wall in the north

Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,4,0,T,0,4,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,E,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(5, 7));
        assert (nextMovement == Direction.West);
    }

    //Testing movement generation when target is northwest of entity with walls in north and west
    @Test
    public void testTargetNorthWestWallsNorthAndWest()
    {

    /*
    Testing if blocked by wall in the north and west

Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,4,0,T,0,4,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,0,4,4,4,E,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        for (int i=0;i<10;i++) {
            Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(6, 6));
            assert (nextMovement == Direction.South || nextMovement == Direction.East);
        }
    }

    //Testing movement generation when target is northeast of entity with walls in east
    @Test
    public void testTargetNorthEastWallsEast() {
    /*
    Testing if blocked by wall in the east

Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,4,0,T,0,4,0,4},
            {4,E,4,0,0,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(1, 5));
        assert (nextMovement == Direction.North);
    }

    //Testing movement generation when target is northeast of entity with walls in south
    @Test
    public void testTargetNorthEastWallsSouth(){

    /*
    Testing if blocked by wall in the south

Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,4,0,T,0,4,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,0,E,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(3, 7));
        assert (nextMovement == Direction.East);
    }

    //Testing movement generation when target is northeast of entity with walls in north and east
    @Test
    public void testTargetNorthEastWallsNorthAndEast(){

    /*
    Testing if blocked by wall in the north and east

Current maze setup:
            {4,4,4,4,4,4,4,4,4},
            {4,0,0,0,0,0,0,0,4},
            {4,0,0,4,4,4,0,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,4,0,T,0,4,0,4},
            {4,0,4,0,0,0,4,0,4},
            {4,0,E,4,4,4,0,0,4},
            {4,0,0,0,0,0,0,0,4},
            {4,4,4,4,4,4,4,0,4}
 Where T is the target, 4 are walls, and 0 are empty spaces, E is the position of the entity to generate movement from

 */
        for (int i=0;i<10;i++) {
            Direction nextMovement = targetedMovementGenerator.next(mockMaze, new Point(2, 6));
            assert (nextMovement == Direction.South || nextMovement == Direction.West);
        }
    }
}
