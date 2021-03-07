package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import ca.sfu.cmpt276.spring2021.group8.project.game.Maze;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;

import java.awt.*;

public class Punishment extends Collectable{

    private int PunishmentScore = -5;

    public void updateScore(){
        score = score + PunishmentScore;
    }

    private void checkValidScore(){

    }

    protected Punishment(Maze maze) {
        super(maze);
    }

    @Override
    public void render(Graphics g, WorldScreenAdapter s) {

    }
}
