package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.game.Maze;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;

import java.awt.*;

public class Punishment extends Entity {

    private int PunishmentScore = -5;

    public Punishment(Maze maze) {
        super(maze);
    }

    public int updateScore(int score){
        score = score + PunishmentScore;
        return score;
    }

    @Override
    public void render(Graphics g, WorldScreenAdapter adapter) {
        Rectangle offset = g.getClipBounds();
        Point playerScreenPosition = adapter.convert(getPosition());
        g.setColor(Color.PINK);
        Draw.dot(g, offset.x + playerScreenPosition.x + adapter.gridHorizontalSpacing()/2, offset.y + playerScreenPosition.y + adapter.gridVerticalSpacing()/2, 16);

    }
}
