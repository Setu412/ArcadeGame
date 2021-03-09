package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.game.Maze;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;

import java.awt.*;

public class BonusReward extends Entity {

    private int BonusRewardScore = 5;

    public BonusReward(Maze maze) {
        super(maze);
    }

    /*public int updateScore(int score){
        score = score + BonusRewardScore;
        return score;
    }*/

    @Override
    public void render(Graphics g, WorldScreenAdapter adapter) {
        Rectangle offset = g.getClipBounds();

        for(int i=0 ; i<maze.getHEIGHT() ; i++) {
            for (int j = 0; j < maze.getWIDTH(); j++) {
                if (maze.getCoordValue(j, i) == 3) {
                    Point BRScreenPosition = adapter.convert(i, j);
                    g.setColor(Color.GREEN);
                    Draw.dot(g, offset.x + BRScreenPosition.x + adapter.gridHorizontalSpacing()/2, offset.y + BRScreenPosition.y + adapter.gridVerticalSpacing()/2, 16);
                }
            }
        }
    }
}
