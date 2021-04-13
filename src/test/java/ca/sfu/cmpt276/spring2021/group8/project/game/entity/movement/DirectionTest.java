package ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.awt.*;

class DirectionTest {

    @Nested
    class DirectionNorthTest
    {
        @Test
        public void testGetNewPositionNorth()
        {
            assert (Direction.North.generateNewPosition(new Point(0,0)).equals(new Point(0,-1)));
        }

        @Test
        public void testGetOppositeDirectionNorth()
        {
            assert (Direction.North.getOppositeDirection().equals(Direction.South));
        }

        @Test
        public void testGetCWOrthogonalDirectionNorth()
        {
            assert (Direction.North.getCWOrthogonalDirection().equals(Direction.East));
        }

        @Test
        public void testGetCounterCWOrthogonalDirectionNorth()
        {
            assert (Direction.North.getCounterCWOrthogonalDirection().equals(Direction.West));
        }
    }
    
    @Nested
    class DirectionSouthTest
    {
        @Test
        public void testGetNewPositionSouth()
        {
            assert (Direction.South.generateNewPosition(new Point(0,0)).equals(new Point(0,1)));
        }

        @Test
        public void testGetOppositeDirectionSouth()
        {
            assert (Direction.South.getOppositeDirection().equals(Direction.North));
        }

        @Test
        public void testGetCWOrthogonalDirectionSouth()
        {
            assert (Direction.South.getCWOrthogonalDirection().equals(Direction.West));
        }

        @Test
        public void testGetCounterCWOrthogonalDirectionSouth()
        {
            assert (Direction.South.getCounterCWOrthogonalDirection().equals(Direction.East));
        }
    }

    @Nested
    class DirectionEastTest
    {
        @Test
        public void testGetNewPositionEast()
        {
            assert (Direction.East.generateNewPosition(new Point(0,0)).equals(new Point(1,0)));
        }

        @Test
        public void testGetOppositeDirectionEast()
        {
            assert (Direction.East.getOppositeDirection().equals(Direction.West));
        }

        @Test
        public void testGetCWOrthogonalDirectionEast()
        {
            assert (Direction.East.getCWOrthogonalDirection().equals(Direction.South));
        }

        @Test
        public void testGetCounterCWOrthogonalDirectionEast()
        {
            assert (Direction.East.getCounterCWOrthogonalDirection().equals(Direction.North));
        }
    }

    @Nested
    class DirectionWestTest
    {
        @Test
        public void testGetNewPositionWest()
        {
            assert (Direction.West.generateNewPosition(new Point(0,0)).equals(new Point(-1,0)));
        }

        @Test
        public void testGetOppositeDirectionWest()
        {
            assert (Direction.West.getOppositeDirection().equals(Direction.East));
        }

        @Test
        public void testGetCWOrthogonalDirectionWest()
        {
            assert (Direction.West.getCWOrthogonalDirection().equals(Direction.North));
        }

        @Test
        public void testGetCounterCWOrthogonalDirectionWest()
        {
            assert (Direction.West.getCounterCWOrthogonalDirection().equals(Direction.South));
        }
    }

}