package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.game.Maze;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;

import java.awt.*;

public class Reward extends Entity {

    private int RewardScore = 2;

    public Reward(Maze maze) {
        super(maze);
    }

    /*public int updateScore(int score){
        score  = score + RewardScore;
        return score;
    }*/

    @Override
    public void render(Graphics g, WorldScreenAdapter adapter) {
        Rectangle offset = g.getClipBounds();

        for(int i=0 ; i<maze.getHEIGHT() ; i++) {
            for (int j = 0; j < maze.getWIDTH(); j++) {
                if (maze.getCoordValue(j, i) == 2) {
                    Point rewardScreenPosition = adapter.convert(i, j);
                    g.setColor(Color.ORANGE);
                    Draw.dot(g, offset.x + rewardScreenPosition.x + adapter.gridHorizontalSpacing()/2, offset.y + rewardScreenPosition.y + adapter.gridVerticalSpacing()/2, 16);
                }
            }
        }
    }
}
