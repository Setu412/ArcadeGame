package ca.sfu.cmpt276.spring2021.group8.project.game.entity;

import ca.sfu.cmpt276.spring2021.group8.project.game.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.BonusReward;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.Punishment;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.Reward;

import java.awt.*;

public abstract class Entity {

    final protected Maze maze;
    protected Point position = new Point();
    int score = 0;
    int checkAllRewards = 0;

    protected Entity(Maze maze) {
        this.maze = maze;
    }


    public Point getPosition() {
        return new Point(position);
    }

//    protected void move(Point position) {
//        this.position = position;
//    }



    abstract public void render(Graphics g, WorldScreenAdapter s);
}
