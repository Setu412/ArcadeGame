package ca.sfu.cmpt276.spring2021.group8.project.game.entity;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;

import java.awt.*;

public class Barrier extends Entity {

    //May want to have all the barries on the maze itself
    // up for discussion
    public Barrier(Point startPosition) {
        super(startPosition);
    }

    public void updatePosition(Point newPosition){
        this.setPosition(newPosition);
    }

    @Override
    public void render(Graphics g, WorldScreenAdapter s) {

        Rectangle offset = g.getClipBounds();
        Point BarrierScreenPosition = s.convert(getPosition());
        g.setColor(Color.GRAY);
        Draw.dot(g, offset.x + BarrierScreenPosition.x + s.gridHorizontalSpacing()/2, offset.y + BarrierScreenPosition.y + s.gridVerticalSpacing()/2, 16);

    }
}
