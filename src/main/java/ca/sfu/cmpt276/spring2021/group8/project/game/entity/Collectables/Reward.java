package ca.sfu.cmpt276.spring2021.group8.project.game.entity.Collectables;
import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.game.Maze;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;
import java.awt.*;

public class Reward extends Entity {

    public Reward(Point rewardCoordinate) {
        super(rewardCoordinate);
    }

   /*public int updateScore(int score){
        score  = score + RewardScore;
        return score;
    }*/

    @Override
    public void render(Graphics g, WorldScreenAdapter adapter) {
        Rectangle offset = g.getClipBounds();

      /* *//* for(int y=0 ; y<maze.getHEIGHT() ; y++) {
            for (int x = 0; x < maze.getWIDTH(); x++) {
                if (maze.getCoordValue(x, y) == 2) {*//*
                    Point rewardScreenPosition = adapter.convert(x, y);
                    g.setColor(Color.ORANGE);
                    Draw.dot(g, offset.x + rewardScreenPosition.x + adapter.gridHorizontalSpacing()/2, offset.y + rewardScreenPosition.y + adapter.gridVerticalSpacing()/2, 16);
                }
            }
        }*/
    }
}