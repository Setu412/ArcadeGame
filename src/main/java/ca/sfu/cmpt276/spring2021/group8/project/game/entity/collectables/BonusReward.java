package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import ca.sfu.cmpt276.spring2021.group8.project.game.Maze;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;

import java.awt.*;

public class BonusReward extends Collectable{

    private int BonusRewardScore = 5;

    public void updateScore(){

        //determine position
        Point PlayerPosition = getPosition();
        //Need Maze coordinates
        if(PlayerPosition.x == getMazeXCoordinate() && PlayerPosition.y == getMazeYCoordinate()) {
            score = score + BonusRewardScore;
        }

    }

    protected BonusReward(Maze maze) {
        super(maze);
    }

    @Override
    public void render(Graphics g, WorldScreenAdapter s) {

    }
}