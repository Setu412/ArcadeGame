package ca.sfu.cmpt276.spring2021.group8.project.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * GenerateInternalMazeWalls generates a map that is used as the maze layout
 */
public class GenerateInternalMazeWalls {

    /**
     * Returns the updated maze with internal walls (having coordinate value equals 4)
     * after reading wall coordinates form MazeDesign files. The MazeDesign file is randomly chosen for a game.
     *
     * @param maze A reference to the current maze used in Maze class
     * @return A maze with internal walls after reading its coordinated from a file
     */
    public static int[][] readWallCoordinates(int maze[][]){

        String path;
        Random r = new Random();

        if(1 + r.nextInt(2) == 1)
            path = "src/resources/MazeDesigns/Maze1.txt";
        else
            path = "src/resources/MazeDesigns/Maze2.txt";

        try {
            File file = new File(path);
            Scanner readFile = new Scanner(file);
            while(readFile.hasNextLine()){

                String read = readFile.nextLine();
                String[] strAry = read.split("\\s+");

                String strNumber = strAry[0];
                int x1 = Integer.parseInt(strNumber);

                strNumber = strAry[1];
                int x2 = Integer.parseInt(strNumber);
                maze[x1][x2] = 4;
            }
            readFile.close();
            return maze;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return maze;
        }
    }

}
