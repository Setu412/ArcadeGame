package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import ca.sfu.cmpt276.spring2021.group8.project.game.Maze;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;

import java.awt.*;

public class BonusReward extends Collectable{


    public void updateScore(){

        //determine position
        score = score + BonusRewardScore;
    }

    protected BonusReward(Maze maze) {
        super(maze);
    }

    @Override
    public void render(Graphics g, WorldScreenAdapter s) {

    }
}
