package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;
import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;
import java.awt.*;

public class Reward extends Collectable {

    final static int REWARDS_POINTS = 2;

    public Reward(Point rewardCoordinate) {
        super(rewardCoordinate);
    }

    @Override
    public int getPoints() {
        return REWARDS_POINTS;
    }

   /*public int updateScore(int score){
        score  = score + RewardScore;
        return score;
    }*/

    @Override
    public void render(Graphics g, WorldScreenAdapter s) {

        Rectangle offset = g.getClipBounds();
        Point RewardScreenPosition = s.convert(this.getPosition());
        g.setColor(Color.YELLOW);
        Draw.dot(g, offset.x + RewardScreenPosition.x + s.gridHorizontalSpacing()/2, offset.y + RewardScreenPosition.y + s.gridVerticalSpacing()/2, 16);

    }
}