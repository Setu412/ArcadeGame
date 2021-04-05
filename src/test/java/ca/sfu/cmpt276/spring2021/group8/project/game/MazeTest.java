package ca.sfu.cmpt276.spring2021.group8.project.game;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

class MazeTest {
    int width = 21;
    int height = 14;
    Maze maze = new Maze();

    private int[][] map={  {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
                    };

    @Test
    public void testGetSize() {
        Point p = new Point(width, height);
        assertEquals(p, maze.getSize());
    }

    @Test
    public void testIsValidPosition() {
        // Valid position
        Point p1 = new Point(2,2);

        // Invalid positions
        // Walls
        Point p2 = new Point(1,height - 1);
        Point p3 = new Point (width - 1, 2);
        Point p4 = new Point (1, height - 1);
        Point p5 = new Point (width - 1, 1);
        Point p6 = new Point (3, 4);

        // Corners
        Point p7 = new Point(0, 0);
        Point p8 = new Point(width - 1, 0);
        Point p9 = new Point (0, height - 1);
        Point p10 = new Point (width - 1, height - 1);
        

        assertEquals(true, maze.isValidPosition(p1));
        assertEquals(false, maze.isValidPosition(p2));
        assertEquals(false, maze.isValidPosition(p3));
        assertEquals(false, maze.isValidPosition(p4));
        assertEquals(false, maze.isValidPosition(p5));
        assertEquals(false, maze.isValidPosition(p6));
        assertEquals(false, maze.isValidPosition(p8));
        assertEquals(false, maze.isValidPosition(p9));
        assertEquals(false, maze.isValidPosition(p10));

    }

    @Test
    public void testGetRandomInt() {
        int num;
        for (int i = 0; i < 50; i++) {
            num = maze.getRandomInt(1, 10);
            assertTrue(num >= 1 && num <= 10);
        }
    }

    @Test
    public void testGeneratePosition() {
        Point p = maze.generatePosition();
        assertEquals(0, map[p.y][p.x]);
    }

}
