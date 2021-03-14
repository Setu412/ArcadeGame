package ca.sfu.cmpt276.spring2021.group8.project.game;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Maze {

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

    //private ArrayList<int[][]> matrix = new ArrayList<int[][]>();


    private int[][] maze ={ {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 4, 4, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 0, 0, 4},
                            {4, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 4},
                            {4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 4},
                            {4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 4},
                            {4, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 4},
                            {4, 0, 0, 4, 4, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
                        };

    // Constructors
    public Maze(Point size) {
        this(size.x, size.y);
    }

    public Maze(int width, int height) {

        this.WIDTH = width;
        this.HEIGHT = height;

        int x1, x2;
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
        return new Point(WIDTH , HEIGHT);
    }

    // Return whether a move is valid (not running into walls)
    public boolean isValidPosition(Point p) {
        // Check if next position is out of bounds
        if (p.x < 1) {
            return false;
        } else if (p.x >= WIDTH - 1) {
            return false;
        }

        if (p.y < 1) {
            return false;
        } else if (p.y >= HEIGHT - 1) {
            return false;
        }

        // Check if next position is an empty space
        if (maze[p.y][p.x] != 0) {
            return false;
        }

        return true;
    }

    // Get value at coordinate
    public int getCoordValue(int x, int y) {
        return this.maze[y][x];
    }

       // Generate random integer within range
    static int getRandomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    
    // Returns an unused Point in the maze for an collectible object to use
    public Point getCollectablePoint() {
            int x1 = getRandomInt(1, this.HEIGHT - 2);
            int x2 = getRandomInt(1, this.WIDTH - 2);
            while (maze[x1][x2] != 0) {
                x1 = getRandomInt(1, this.HEIGHT - 2);
                x2 = getRandomInt(1, this.WIDTH - 2);
            }
            maze[x1][x2] = 2;
            return new Point(x2, x1);
    }

    // Resets the maze to the original design (call after creating all the collectible objects) 
    // * Issue: doesn't have any interal walls right now
    public void resetMaze() {
        for (int i=0; i < this.HEIGHT; i++) {
            for (int j=0; j < this.WIDTH; j++) {
                if (maze[i][j] == 2) {
                    maze[i][j] = 0;
                }
            }
        }
    }

    public void complete(){
        maze[exit.y][exit.x] = 9;
    }

    public void render(Graphics g, WorldScreenAdapter s) {
        Rectangle offset = g.getClipBounds();
        for (int i=0; i < this.HEIGHT; i++)
        {
            for (int j = 0; j < this.WIDTH; j++)
            {
                if (maze[i][j] == 4)
                {
                    Point wallScreenPosition = s.convert(j, i);
                    g.setColor(Color.BLACK);
                    Draw.dot(g, offset.x + wallScreenPosition.x + s.gridHorizontalSpacing() / 2, offset.y + wallScreenPosition.y + s.gridVerticalSpacing() / 2, 16);
                }
                if (maze[i][j] == 7)
                {
                    Point wallScreenPosition = s.convert(j, i);
                    g.setColor(Color.CYAN);
                    Draw.dot(g, offset.x + wallScreenPosition.x + s.gridHorizontalSpacing() / 2, offset.y + wallScreenPosition.y + s.gridVerticalSpacing() / 2, 16);
                }
                if (maze[i][j] == 8)
                {
                    Point wallScreenPosition = s.convert(j, i);
                    g.setColor(Color.MAGENTA);
                    Draw.dot(g, offset.x + wallScreenPosition.x + s.gridHorizontalSpacing() / 2, offset.y + wallScreenPosition.y + s.gridVerticalSpacing() / 2, 16);
                }
            }
        }
    }

}
