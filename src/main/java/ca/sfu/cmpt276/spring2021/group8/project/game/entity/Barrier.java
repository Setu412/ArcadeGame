package ca.sfu.cmpt276.spring2021.group8.project.game.entity;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.ImageLoader;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Barrier extends Entity {

    private BufferedImage barrierImg;

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
        barrierImg = ImageLoader.loadImage("src/resources/Images/barrier.jpg");
        Rectangle offset = g.getClipBounds();
        Point BarrierScreenPosition = s.convert(getPosition());
        //g.setColor(Color.GRAY);
        //Draw.dot(g, offset.x + BarrierScreenPosition.x + s.gridHorizontalSpacing()/2, offset.y + BarrierScreenPosition.y + s.gridVerticalSpacing()/2, 16);
        Image result = barrierImg.getScaledInstance(49, 49, Image.SCALE_DEFAULT);
        g.drawImage(result, offset.x + BarrierScreenPosition.x - 24 + s.gridHorizontalSpacing()/2, offset.y + BarrierScreenPosition.y - 24 + s.gridVerticalSpacing()/2, null);
    }
}
