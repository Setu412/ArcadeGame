package Program;

public class Movement {
	
	static int movePlayer(int[] nextCoords, int x, int y, Maze maze) {
		int nextY = nextCoords[0];
		int nextX = nextCoords[1];
		// Check what the next cell contains
		int result = collide(x, y, maze);
		// Update map position based on what is on the next cell
		switch (result) {
		case -2:
			maze.setPosition(nextX, nextY, 1);
			maze.setPosition(x, y, 0);
			return -2;
		case -1:
			maze.setPosition(nextX, nextY, 1);
			maze.setPosition(x,  y,  0);
			return -1;
		case 0:
			maze.setPosition(nextX, nextY, 1);
			maze.setPosition(x, y, 0);
			return 0;
		case 2:
			maze.setPosition(nextX,  nextY, 1);
			maze.setPosition(x, y, 0);
			return 2;
		case 3:
			maze.setPosition(nextX, nextY, 1);
			maze.setPosition(x,  y,  0);
			return 3;
		case 4:
			return 4;
		case 5:
			return 5;
		case 7:
			return 7;
		default:
			return 0;
		}
	}
	
	// Collisions: -2 <- punishment,
	//			   -1 <- encountered enemy
	//				0 <- empty cell
	//				2 <- reward
	//				3 <- bonus reward
	//			    4 <- wall
	//				7 <- exit
	static int collide(int x, int y, Maze maze) {
		// Get value of next position
		return maze.getPosition(x, y);
	}
	
	// Get coordinates of next move
	static int[] next(int x, int y, char key) {
		int[] next = new int[2];
		if (key == 'w') {
			next[0] = y - 1;
			next[1] = x;
			return  next;
		} else if (key == 'd') {
			next[0] = y + 1;
			next[1] = x;
			return next;
		} else if (key == 'a') {
			next[0] = y;
			next[1] = x - 1;
			return next;
		} else if (key == 'd') {
			next[0] = y;
			next[1] = x + 1;
			return next;
		}
		// Player didn't press wasd
		next[0] = 0;
		next[1] = 0;
		return next;
	}
	
}
