package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import ca.sfu.cmpt276.spring2021.group8.project.game.Maze;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;

import java.awt.*;

public abstract class Collectable extends Entity {

    int score; //final
    Point NewPlayerPosition = getPosition();

    protected Collectable(Maze maze) {
        super(maze);
    }

    //abstract method to update the score according to the type of collectable
    public abstract void updateScore();

    //check if the player interacts with the collectable and updates accordingly
    public void intersect()
    {
        if(NewPlayerPosition.x == getMazeXCoordinate() && NewPlayerPosition.y == getMazeYCoordinate())
        {
            updateScore();
        }
    }
}
