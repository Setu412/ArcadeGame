package ca.sfu.cmpt276.spring2021.group8.project.game.entity;

import ca.sfu.cmpt276.spring2021.group8.project.game.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.BonusReward;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.Punishment;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.Reward;

import java.awt.*;

public abstract class Entity {

    final protected Maze maze;
    private Point position = new Point();
    int score = 0;
    int checkAllRewards = 0;

    protected Entity(Maze maze) {
        this.maze = maze;
    }


    public Point getPosition() {
        return new Point(position);
    }

    protected void move(Point position) {
        this.position = position;
    }

    protected void tryMove(Point position, int[] originalXY) {
        int isValidPosition = maze.isValidPosition(position, originalXY);
        //isValidPosition == 0 mean its a wall and you cant move
        //isValidPosition == some other value, means player has moved into something
        if ( isValidPosition == 0) {
            return;
        }

        this.position = position;
        maze.setCoordValue(originalXY[0], originalXY[1], 0);
        //Now, player has moved to new position and its position has changed
        //creating a function to reflect what would happen if player makes that move
        // could be enemy, reward, punishments
        afterMoving(isValidPosition);
    }

    void afterMoving(int movedIntoValue ){

        int BonusRewardScore = 5;
        int RewardScore = 2;
        int PunishmentScore = -5;

        switch(movedIntoValue){
            case -1: //game over moved in enemy
                //TODO stop the game --> loosing screen
                break;
            case -2: // moved in punishment
                score = score + PunishmentScore;
                if(score < 0)
                {
                    //TODO stop the game --> loosing screen
                }
                break;
            case 2: // moved into reward
                score = score + RewardScore;
                checkAllRewards++;
                if(checkAllRewards == 40)
                {
                    maze.completed();
                }

                break;
            case 3: // moved into bonusReward
                score = score + BonusRewardScore;
                break;
            case 9: //game is won
                //TODO won the game --> winning screen
                break;
        }
     }

    abstract public void render(Graphics g, WorldScreenAdapter s);
}
