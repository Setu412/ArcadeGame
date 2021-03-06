package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import ca.sfu.cmpt276.spring2021.group8.project.game.Maze;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;

import java.awt.*;

public class Reward extends Collectable{

    private int RewardScore = 2;

    public void updateScore(){

        //determine position
        score = score + RewardScore;
    }

    protected Reward(Maze maze) {
        super(maze);
    }

    @Override
    public void render(Graphics g, WorldScreenAdapter s) {

    }
}
