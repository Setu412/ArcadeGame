package ca.sfu.cmpt276.spring2021.group8.project.game.entity;

import ca.sfu.cmpt276.spring2021.group8.project.game.Maze;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;

import java.awt.*;

public class Enemy extends Entity{

    protected Enemy(Maze maze) {
        super(maze);
    }

    @Override
    public void render(Graphics g, WorldScreenAdapter s) {

    }
}

/**
 * TODO in Enemy class
 *
 * 1. generate its random movement --> Another class for it according to UML diagram
 *      this randomly generated movement is such that enemy approaches player --> kinda shortest path
 *
 * 2. update Maze coordinate value according to its movement
 *
 * 3. render image of enemies
 *
 *
 */
