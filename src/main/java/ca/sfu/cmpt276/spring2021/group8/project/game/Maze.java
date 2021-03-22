package ca.sfu.cmpt276.spring2021.group8.project.game;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.ImageLoader;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.PositionValidator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Maze implements PositionValidator {


    public static final int WALL = 4;
    public static final int ENTRANCE = 7;
    public static final int EXIT = 8;
    public static int EXITOPEN = 9;

    // Maze variables
    private int width = 20;
    private int height = 12;

    private Point start;
    private Point exit;

    public Point nextToStart;
    public Point nextToExit;

    private int[][] maze={  {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 4, 4, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 0, 0, 4},
                            {4, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 4},
                            {4, 0, 0, 4, 4, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
                        };
                        
    // Constructors
    public Maze() {
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

        // Generate barriers ** TO BE DETERMINED

    }

    // Initialize starting position
    public Point startPosition() {
        return new Point(start);
    }

    // Get size of maze
    public Point getSize() {
        return new Point(width , height);
    }

    // Return whether a move is valid (not running into walls)
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

    // Generate random integer within range
    static int getRandomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    
    // Returns a Point in the maze to use
    public Point generatePosition() {
            int x1;
            int x2;
             do {
                x1 = getRandomInt(1, height - 2);
                x2 = getRandomInt(1, width- 2);
            }while (maze[x1][x2] != 0 && !nextToStart.equals(new Point(x1,x2)) && !nextToExit.equals(new Point(x1,x2)));
            return new Point(x2, x1);
    }

    // Resets the maze to the original design (call after creating all the collectible objects) 
    // * Issue: doesn't have any internal walls right now
    public void resetMaze() {
        for (int i=0; i < height; i++) {
            for (int j=0; j < this.width; j++) {
                if (maze[i][j] == 2) {
                    maze[i][j] = 0;
                }
            }
        }
    }

    public void complete(){
        maze[exit.y][exit.x] = 9;
    }

    // Set the coordinates of the position next to the doors
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

    public void render(Graphics g, WorldScreenAdapter s) {
        Rectangle offset = g.getClipBounds();


        for (int i=0; i < height; i++)
        {
            for (int j = 0; j < this.width; j++)
            {
                if (maze[i][j] == WALL)
                {
                    Point wallScreenPosition = s.convert(j, i);
                    //g.setColor(Color.BLACK);
                    //Draw.dot(g, offset.x + wallScreenPosition.x + s.gridHorizontalSpacing() / 2, offset.y + wallScreenPosition.y + s.gridVerticalSpacing() / 2, 16);
                    g.drawImage(ImageLoader.w, offset.x + wallScreenPosition.x - 24 + s.gridHorizontalSpacing() / 2, offset.y + wallScreenPosition.y - 24 + s.gridVerticalSpacing() / 2, null);
                }
                if (maze[i][j] == ENTRANCE)
                {
                    Point wallScreenPosition = s.convert(j, i);
                    //g.setColor(Color.CYAN);
                    //Draw.dot(g, offset.x + wallScreenPosition.x + s.gridHorizontalSpacing() / 2, offset.y + wallScreenPosition.y + s.gridVerticalSpacing() / 2, 16);
                    g.drawImage(ImageLoader.entr, offset.x + wallScreenPosition.x - 24 + s.gridHorizontalSpacing() / 2, offset.y + wallScreenPosition.y - 24 + s.gridVerticalSpacing() / 2, null );
                }
                if (maze[i][j] == EXIT)
                {
                    Point wallScreenPosition = s.convert(j, i);
                    //g.setColor(Color.MAGENTA);
                    //Draw.dot(g, offset.x + wallScreenPosition.x + s.gridHorizontalSpacing() / 2, offset.y + wallScreenPosition.y + s.gridVerticalSpacing() / 2, 16);
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
