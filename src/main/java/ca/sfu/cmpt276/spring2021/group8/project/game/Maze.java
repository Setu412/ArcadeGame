package ca.sfu.cmpt276.spring2021.group8.project.game;

import java.awt.Point;
import java.util.Random;

/* Issues
 1) What happens when player loses the game?
 2) What happens when player collects an reward/?
 3) What happens when player unlocks door?
 4) Should set a height and width of board and stick with it
 
*/

public class Maze {
    private Point size;

    // Maze variables
    private int WIDTH = 20;
    private int HEIGHT = 12;
    private int REWARDS_NUM = 40;
    private int BONUS_NUM = 5;
    private int TOTAL_OBJECTS = 60;
    private int BARRIERS_NUM = 5;

    private Point start;
    private Point exit;

    // Score variables
    private int score = 0;
    private int REGULAR_POINTS = 250;
    private int BONUS_POINTS = 500;
    private int PUNISHMENT_POINTS = -400;

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
    private int[][] maze ={{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
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
    public Maze() {
        int x1;
        int x2;

        // Generate maze according to width and height
        /*
        int[this.HEIGHT][this.WIDTH] maze;
        for (int i = 0; i < this.HEIGHT; i++) {
            for (int j=0; j < this.WIDTH; j++) {
                if (i == 0 || i == this.HEIGHT - 1) {
                    this.maze[i][j] = 4;
                }
                else {
                    if (j == 0 || j == this.WIDTH - 1){
                        this.maze[i][j] = 4;
                    } else {
                        this.maze[i][j] = 0;
                    }             
                }
            }
        } */
        
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
        this.start = new Point(x1, x2);
    
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
        this.exit = new Point(x1, x2); 

        // Randomly generate internal walls
        generateWalls(); 
        
        // Randomly generate items (make sure not on top of anything)
        for (int i=0; i < this.TOTAL_OBJECTS; i++) {
            x1 = getRandomInt(1, this.HEIGHT - 2);
            x2 = getRandomInt(1, this.WIDTH - 2);
            while (maze[x1][x2] != 0) {
                x1 = getRandomInt(1, this.HEIGHT - 2);
                x2 = getRandomInt(1, this.WIDTH - 2);
            }
            if (i < this.REWARDS_NUM - 1) {
                maze[x1][x2] = 2;
            } else if (i >= this.REWARDS_NUM - 1 && i < this.REWARDS_NUM - 1 + this.BONUS_NUM - 1) {
                maze[x1][x2] = 3;
            } else {
                maze[x1][x2] = -2;
            }
        }

        // Randomly generate barriers
        for (int i=0; i < this.BARRIERS_NUM; i++) {
            x1 = getRandomInt(1, this.HEIGHT - 2);
            x2 = getRandomInt(1, this.WIDTH - 2);
            while (maze[x1][x2] != 0) {
                x1 = getRandomInt(1, this.HEIGHT - 2);
                x2 = getRandomInt(1, this.WIDTH - 2);
            }
            maze[x1][x2] = 5;
        }

    }

    public Maze(Point size) {
        this();
        this.size = new Point(size);
    }

    public Maze(int width, int height) {
        this(new Point(width, height));
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    // Point operations
    public Point startPosition() {
        /* Need to check where we want player to start from
        if (start.x = 0) {
            return new Point(start.x + 1, start.y);
        } else if (start.x = this.WIDTH - 1) {
            return new Point(start.x - 1, start.y);
        }

        if (start.y = 0) {
            return new Point(start.x, start.y + 1);
        } else if (start.y = this.WIDTH - 1) {
            return new Point(start.x, start.y - 1);
        }
        */
        return new Point(0, 0);
    }


    public Point getSize() {
        return new Point(size);
    }

    // Returns whether or not move is possible
    public boolean isValidPosition(Point p, int[] originalXY) {
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

        // Check if next position contains anything
        int result = moveInMaze(p, originalXY);

        if (result == -1) {
            // * Game is over
            return false;

        } else if (result == 4 || result == 5 || result == 7 || result == 8 ) {
            return false;
        } else if (result == 2 || result == 3) {
            // * Reward collected, update frame at location (p.x, p.y) before moving player
            
            return true;
        } else if (result == -2) {
            // * Punishment collected, update frame at location (p.x, p.y) before moving player
            return true;
        }

        // Game is won
        /*
        if (result == 9) {
            return something;
        } */

        return true;
    }

    // Maze related methods
    // --------------------------------------------------------

    // Get value at coordinate
    public int getCoordValue(int x, int y) {
        return this.maze[y][x];
    }

    // Set value at coordinate
    public void setCoordValue(int x, int y, int z) {
        this.maze[y][x] = z;
    }

    // Generate walls on maze (*this function is hardcoded right now)
    public void generateWalls() {
        int yWay = 0;
        for (int i = 2; i < 6; i++) {
            for (int j = 2; j < 8; j++) {
                this.maze[i + yWay][j] = 4;
            }
            for (int k = 12; k < 16; k++) {
                this.maze[i + yWay][k] = 4;
            }
            yWay++;
        }
    }

    // Generate random integer within range
    static int getRandomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    // Generate coordinate in maze 
    static Point getACoord(int min, int max) {
        Point p = new Point();
        p.x = getRandomInt(min, max);
        p.y = getRandomInt(min, max);
        return p;
    }
    
    // Print out the maze
    public void displayMaze() {
        for(int i = 0; i < this.HEIGHT; i++) {
            for (int j = 0; j < this.WIDTH; j++) {
                if (maze[i][j] >= 0) {
                    System.out.print(" " + maze[i][j] + " ");
                } else {
                    System.out.print(maze[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // Try to move player to coordinate
    public int moveInMaze(Point p, int[] originalXY) {
        int nextX = p.x;
        int nextY = p.y;

        int X = originalXY[1];
        int Y = originalXY[2];

        int result = getCoordValue(nextX, nextY);

        switch (result) {
        default:
            return 0;
        case -2:
            setCoordValue(nextX, nextY, 1);
            setCoordValue(X, Y, 0);
            updateScore(this.PUNISHMENT_POINTS);
            return -2;
        case -1:
            setCoordValue(nextX, nextY, 1);
            setCoordValue(X,  Y,  0);
            return -1;
        case 0:
            setCoordValue(nextX, nextY, 1);
            setCoordValue(X, Y, 0);
            return 0;
        case 2:
            setCoordValue(nextX,  nextY, 1);
            setCoordValue(X, Y, 0);
            updateScore(this.REGULAR_POINTS);
            return 2;
        case 3:
            setCoordValue(nextX, nextY, 1);
            setCoordValue(X,  Y,  0);
            updateScore(this.BONUS_POINTS);
            return 3;
        case 4:
            return 4;
        case 5:
            return 5;
        case 7:
            return 7;
        case 8:
            if (unlockedStatus()) {
                setCoordValue(nextX, nextY, 1);
                setCoordValue(X, Y, 9);
                return 9;
            }
            return 8;

        }

    }

    public int getScore() {
        return this.score;
    }

    public void updateScore(int x) {
        this.score += x;
    }

    public boolean unlockedStatus() {
        if (this.score >= this.UNLOCKED_SCORE) {
            return true;
        }
        return false;
    }

}
