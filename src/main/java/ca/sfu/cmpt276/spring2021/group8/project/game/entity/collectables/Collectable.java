package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import ca.sfu.cmpt276.spring2021.group8.project.game.Maze;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;

import java.awt.*;

//this class is of no use now
public abstract class Collectable extends Entity {

    protected Collectable(Maze maze) {
        super(maze);
    }
}
    /*protected int score = 0; //final score
    private Point PlayersNewPosition = getPosition();
    private Punishment punishment;
    private Reward reward;
    private BonusReward bonusReward;


    public Collectable(Maze maze) {
        super(maze);
    }


    //abstract method to update the score according to the type of collectable

    //check if the player interacts with the collectable and updates accordingly
    public void intersect(){

        int value_At_XY_in_Maze = maze.getValueAtXYinMaze(PlayersNewPosition.x,PlayersNewPosition.y);
        switch(value_At_XY_in_Maze){

            case -2:
                punishment = new Punishment(maze);
                punishment.updateScore();
                break;
            case 2:
                reward = new Reward(maze);
                reward.updateScore();
                break;
            case 4:
                bonusReward = new BonusReward(maze);
                bonusReward.updateScore();
                break;

        }
    }*/

