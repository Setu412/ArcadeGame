package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import ca.sfu.cmpt276.spring2021.group8.project.game.Maze;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;

public abstract class Collectable extends Entity {

    int score;

    protected Collectable(Maze maze) {
        super(maze);
    }

}
