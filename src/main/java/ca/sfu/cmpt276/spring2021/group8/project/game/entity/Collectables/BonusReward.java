package ca.sfu.cmpt276.spring2021.group8.project.game.entity.Collectables;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;

import java.awt.*;

public class BonusReward extends Entity {


    public BonusReward(Point BonusRewardCoordinate) {
        super(BonusRewardCoordinate);
    }

    public void updateBRCoordinates(Point newPoint){
        this.setPosition(newPoint);
    }

    @Override
    public void render(Graphics g, WorldScreenAdapter s) {

        Rectangle offset = g.getClipBounds();
        Point BRewardScreenPosition = s.convert(this.getPosition());
        g.setColor(Color.GREEN);
        Draw.dot(g, offset.x + BRewardScreenPosition.x + s.gridHorizontalSpacing()/2, offset.y + BRewardScreenPosition.y + s.gridVerticalSpacing()/2, 16);

    }
}
