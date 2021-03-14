package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;

import java.awt.*;

public class Punishment extends Collectable {

    final static int PUNISHMENT_POINTS = -4;

    public Punishment(Point rewardCoordinate) {
        super(rewardCoordinate);
    }

    @Override
    public int getPoints() {
        return PUNISHMENT_POINTS;
    }

    @Override
    public void render(Graphics g, WorldScreenAdapter s) {

        Rectangle offset = g.getClipBounds();
        Point PunishmentScreenPosition = s.convert(getPosition());
        g.setColor(Color.PINK);
        Draw.dot(g, offset.x + PunishmentScreenPosition.x + s.gridHorizontalSpacing()/2, offset.y + PunishmentScreenPosition.y + s.gridVerticalSpacing()/2, 16);

    }
}
