package Program;

public class Player {
	// Current position on map
	private int x;
	private int y;
	
	// Constructor
	public Player() {
		this.x = 1;
		this.y = 1;
	}
	
	// Main method for controlling player movement
	public void move(char key_pressed, Maze maze) {
	
		// Get coordinates of next move
		int[] nextCoords = Movement.next(this.x, this.y, key_pressed);
		
		// If player didn't press wasd
		if (nextCoords[0] == 0 && nextCoords[1] == 0) {
			return;
		}

		int result = Movement.movePlayer(nextCoords, this.x, this.y, maze);
		
		if (result != 4 || result != 5 || result != 7) {
			this.y = nextCoords[0];
			this.x = nextCoords[1];
		}
	}
}
