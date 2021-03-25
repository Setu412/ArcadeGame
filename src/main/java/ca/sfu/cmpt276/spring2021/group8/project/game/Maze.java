package ca.sfu.cmpt276.spring2021.group8.project.game;

import ca.sfu.cmpt276.spring2021.group8.project.ImageLoader;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.PositionValidator;

import java.awt.*;
import java.util.Random;

/**
 * Maze is the game board that contains the entrance, exit, and walls
 */
public class Maze implements PositionValidator {


    public static final int WALL = 4;
    public static final int ENTRANCE = 7;
    public static final int EXIT = 8;
    public static int EXITOPEN = 9;

    // Maze variables
    private int width = 21;
    private int height = 14;

    private Point start;
    private Point exit;

    public Point nextToStart;
    public Point nextToExit;

    private int[][] maze={  {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 0, 0, 4},
                            {4, 0, 0, 4, 0, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 4, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 4, 0, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 4, 0, 0, 4},
                            {4, 0, 0, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
                        };


    /**
     * Constructs a Maze object, along with randomly generating entrance and exit positions
     */
    public Maze() {

        //maze = GenerateInternalMazeWalls.readWallCoordinates(maze);

        int x1, x2;

        // Randomly generate entrance
        x1 = getRandomInt(0, height - 1);

        // If random height is first or last row
        if (x1 == 0 || x1 == height - 1) {
            x2 = getRandomInt(1, width- 2);
            maze[x1][x2] = ENTRANCE;
        } else {
            x2 = getRandomInt(0, 1);
            if (x2 == 0) {
                maze[x1][x2] = ENTRANCE;
            } else {
                x2 = width- 1;
                maze[x1][x2] = ENTRANCE;
            }
        }
        start = new Point(x2, x1);

        // Randomly generate exit
        x1 = getRandomInt(0, height - 1);

        // If random height is first or last row
        if (x1 == 0 || x1 == height - 1) {
            x2 = getRandomInt(1, width- 2);
            while (maze[x1][x2] == ENTRANCE) {
                x2 = getRandomInt(1, width- 2);
            }
            maze[x1][x2] = 8;
        } else {
            x2 = getRandomInt(0, 1);
            while (maze[x1][x2] == ENTRANCE) {
                x2 = getRandomInt(1, width- 2);
            }
            if (x2 == 0) {
                maze[x1][x2] = EXIT;
            } else {
                x2 = width- 1;
                maze[x1][x2] = EXIT;
            }
        } 

        exit = new Point(x2, x1);

        nextToStart = nextToDoors(start);
        nextToExit = nextToDoors(exit);
    }

    /**
     * Returns the entrance position where the player spawn into the game
     *
     * @return start point
     */
    public Point startPosition() {
        return new Point(start);
    }

    /**
     * Returns the exit position where player need to reach after collection all rewards
     *
     * @return exit point
     */
    public Point exitPosition() {
        return new Point(exit);
    }

    /**
     * Return Height and Width of the maze as a Point object
     *
     * @return Size of maze as Point object
     */
    public Point getSize() {
        return new Point(width , height);
    }

    /**
     * Ensures the new position the movable entity intends to move is not a wall
     *
     * @param p New position of movable entity
     * @return True if the position p is not a wall or an unlocked exit point, Return False the new position is a wall
     */
    public boolean isValidPosition(Point p) {

        //check if moving towards unlocked exit tile
        if (p.equals(exit) && maze[exit.y][exit.x] == 9) {
            return true;
        }

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

    /**
     * Generate and returns a random integer within range
     *
     * @param min minimum bound for random number generation
     * @param max maximum bound for random number generation
     * @return randomly generated integer
     */
    static int getRandomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Generates and returns a Point on the maze to use
     *
     * @return newly generated position as Point object
     */
    public Point generatePosition() {
        int x1;
        int x2;
         do {
            x1 = getRandomInt(1, height - 2);
            x2 = getRandomInt(1, width- 2);

            /*if(nextToStart.equals(new Point(x2,x1))){
                continue;
            }*/

        }while (maze[x1][x2] != 0);

        return new Point(x2, x1);
    }

    /**
     * When all rewards are collected, value stored at maze coordinate is changed to 9
     */
    public void complete(){
        maze[exit.y][exit.x] = 9;
    }

    /**
     * Finds and returns the position on maze right next to start or exit point
     *
     * @param door Point of the Start or the Exit
     * @return A position right next to Start or exits as Point object
     */
    public Point nextToDoors(Point door) {

        // Check what row it is on
        if (door.y == 0) {
            return new Point(door.x,  door.y + 1);
        }
        if (door.y == height - 1) {
            return new Point(door.x, door.y - 1);
        }
        if (door.x == 0) {
            return new Point(door.x + 1, door.y);
        }
        if (door.x == width - 1) {
            return new Point(door.x - 1, door.y);
        }
        return null;
    }

    /**
     * Renders the maze layout on to the screen containing walls, entrance and exit positions
     *
     * @param g Graphic object to draw Image onto screen
     * @param s WorldScreenAdapter object to relate the world and screen
     */
    public void render(Graphics g, WorldScreenAdapter s) {
        Rectangle offset = g.getClipBounds();

        for (int i=0; i < height; i++)
        {
            for (int j = 0; j < this.width; j++)
            {
                if (maze[i][j] == WALL)
                {
                    Point wallScreenPosition = s.convert(j, i);
                    g.drawImage(ImageLoader.w, offset.x + wallScreenPosition.x - 24 + s.gridHorizontalSpacing() / 2, offset.y + wallScreenPosition.y - 24 + s.gridVerticalSpacing() / 2, null);
                }
                if (maze[i][j] == ENTRANCE)
                {
                    Point wallScreenPosition = s.convert(j, i);
                    g.drawImage(ImageLoader.entr, offset.x + wallScreenPosition.x - 24 + s.gridHorizontalSpacing() / 2, offset.y + wallScreenPosition.y - 24 + s.gridVerticalSpacing() / 2, null );
                }
                if (maze[i][j] == EXIT)
                {
                    Point wallScreenPosition = s.convert(j, i);
                    g.drawImage(ImageLoader.exitC, offset.x + wallScreenPosition.x - 24 + s.gridHorizontalSpacing() / 2, offset.y + wallScreenPosition.y - 24 + s.gridVerticalSpacing() / 2, null);
                }

                if (maze[i][j] == EXITOPEN)
                {
                    Point wallScreenPosition = s.convert(j, i);
                    g.drawImage(ImageLoader.exitO, offset.x + wallScreenPosition.x - 24 + s.gridHorizontalSpacing() / 2, offset.y + wallScreenPosition.y - 24 + s.gridVerticalSpacing() / 2, null);
                }
            }
        }
    }

}
