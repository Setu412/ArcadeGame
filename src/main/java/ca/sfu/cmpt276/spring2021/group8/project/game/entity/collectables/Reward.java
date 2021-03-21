package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;
import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.ImageLoader;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Reward extends Collectable {
    private BufferedImage rewardImg;
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
        rewardImg = ImageLoader.loadImage("src/resources/Images/p.png");

        Rectangle offset = g.getClipBounds();
        Point RewardScreenPosition = s.convert(this.getPosition());
        //g.setColor(Color.YELLOW);
        //Draw.dot(g, offset.x + RewardScreenPosition.x + s.gridHorizontalSpacing()/2, offset.y + RewardScreenPosition.y + s.gridVerticalSpacing()/2, 16);
        Image result = rewardImg.getScaledInstance(47, 47, Image.SCALE_DEFAULT);
        g.drawImage(result, offset.x + RewardScreenPosition.x - 23 + s.gridHorizontalSpacing()/2, offset.y + RewardScreenPosition.y - 23 + s.gridVerticalSpacing()/2, null);

    }
}