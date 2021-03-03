Package Game;
import java.util.Random;

public class Maze {
	private int WIDTH = 20;
	private int HEIGHT = 12;
	private int REWARDS_NUM = 40;
	private int BONUS_NUM = 5;
	private int TOTAL_OBJECTS = 60;
	// Maze element ID's:
		// -2 <- punishment
		// -1 <- movable enemy 
		//  0 <- empty space 
		//  1 <- player 
		//  2 <- reward
		//  3 <- bonus reward
		//  4 <- walls
		//  5 <- barrier
		//  7 <- entrance (9) if it is unlocked
		//  8 <- exit
	public int[][] maze = { {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
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
			
	// Constructor
	public Maze() {
		int x1;
		int x2;
		
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
		
		// Randomly generate awards (randomly generate, make sure not on top of anything)
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
		// Randomly generate internal walls
		generateWalls();
			
	}
	
	// Get value at coordinates on maze
	public int getPosition(int x, int y) {
		return this.maze[y][x];
	}
	
	// Set value at coordinates on maze
	public void setPosition(int x, int y, int z) {
		this.maze[y][x] = z;
	}
	
	// Randomly generates internal wall patterns and inserts it into maze
	public void generateWalls() {
		return;
	}
	
	static int getRandomInt(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
}