package ca.sfu.cmpt276.spring2021.group8.project.game;

import java.awt.Point;
import java.util.Random;

public class Maze {
    private Point size;

     // Maze variables
    private int WIDTH = 20;
    private int HEIGHT = 12;

    private Point start;
    private Point exit;

    // Maze element ID's:
    // -2 <- punishment
    // -1 <- movable enemy 
    //  0 <- empty space 
    //  1 <- player 
    //  2 <- reward
    //  3 <- bonus reward
    //  4 <- walls
    //  5 <- barrier
    //  7 <- entrance 
    //  8 <- exit (9) if it is unlocked
    // X <- [][]
    // Y <- []
    private int[][] maze ={ {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
                        };
                        
    // Constructors
    public Maze(Point size) {
        this.size = new Point(size);
    }

    public Maze(int width, int height) {
        this(new Point(width, height));

         // Randomly generate entrance
        x1 = getRandomInt(0, this.HEIGHT - 1);
        // If random height is first or last row
        if (x1 == 0 || x1 == this.HEIGHT - 1) {
            x2 = getRandomInt(1, this.WIDTH - 2);
            maze[x1][x2] = 7;
        } else {
            x2 = getRandomInt(0, 1);
            if (x2 == 0) {
                maze[x1][x2] = 7;
            } else {
                x2 = this.WIDTH - 1;
                maze[x1][x2] = 7;
            }
        }
        this.start = new Point(x2, x1);

         // Randomly generate exit
        x1 = getRandomInt(0, this.HEIGHT - 1);
        // If random height is first or last row
        if (x1 == 0 || x1 == this.HEIGHT - 1) {
            x2 = getRandomInt(1, this.WIDTH - 2);
            while (maze[x1][x2] == 7) {
                x2 = getRandomInt(1, this.WIDTH - 2);
            }
            maze[x1][x2] = 8;
        } else {
            x2 = getRandomInt(0, 1);
            while (maze[x1][x2] == 7) {
                x2 = getRandomInt(1, this.WIDTH - 2);
            }
            if (x2 == 0) {
                maze[x1][x2] = 8;
            } else {
                x2 = this.WIDTH - 1;
                maze[x1][x2] = 8;
            }
        } 
        this.exit = new Point(x2, x1); 
    }

    // Initialize starting position
    public Point startPosition() {
        return new Point(start);
    }

    // Get size of maze
    public Point getSize() {
        return new Point(size);
    }

    // Return whether a move is valid (not running into walls)
    public boolean isValidPosition(Point p) {
        // Check if next position is out of bounds
        if (p.x < 1) {
            return false;
        } else if (p.x >= size.x - 1) {
            return false;
        }

        if (p.y < 1) {
            return false;
        } else if (p.y >= size.y - 1) {
            return false;
        }

        // Check if next position is an empty space
        if (maze[p.y][p.x] != 0) {
            return false;
        }

        return true;
    }

    public Point getCollectablePoint() {
        Point p = new Point();
        return p;
    }
}