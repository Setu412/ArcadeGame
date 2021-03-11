package ca.sfu.cmpt276.spring2021.group8.project.game.entity;

import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;

import java.awt.*;

public class Barrier extends Entity {

    //May want to have all the barries on the maze itself
    // up for discussion
    public Barrier(Point startPosition) {
        super(startPosition);
    }

    @Override
    public void render(Graphics g, WorldScreenAdapter s) {

    }
}
