package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.ImageLoader;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BonusReward extends Collectable {
    private BufferedImage bonusImg = ImageLoader.loadImage("src/resources/Images/bonus.png");
    private Image result = bonusImg.getScaledInstance(47, 47, Image.SCALE_DEFAULT);

    public boolean isVisible = false;
    final static int BONUS_POINTS = 5;

    public BonusReward(Point BonusRewardCoordinate) {
        super(BonusRewardCoordinate);
    }

    @Override
    public int getPoints() {
        return BONUS_POINTS;
    }

    public void updateBRCoordinates(Point newPoint){
        this.setPosition(newPoint);
    }

    public void setPosition(Point position) {
        super.setPosition(position);
    }

    @Override
    public void render(Graphics g, WorldScreenAdapter s) {

        if(isVisible) {
            Rectangle offset = g.getClipBounds();
            Point BRewardScreenPosition = s.convert(this.getPosition());
            //g.setColor(Color.GREEN);
            //Draw.dot(g, offset.x + BRewardScreenPosition.x + s.gridHorizontalSpacing() / 2, offset.y + BRewardScreenPosition.y + s.gridVerticalSpacing() / 2, 16);

            g.drawImage(result,offset.x + BRewardScreenPosition.x - 23 + s.gridHorizontalSpacing() / 2, offset.y + BRewardScreenPosition.y - 23 + s.gridVerticalSpacing() / 2, null);
        }
    }


}
