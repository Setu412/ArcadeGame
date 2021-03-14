package ca.sfu.cmpt276.spring2021.group8.project.game.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.ImageLoader;
import ca.sfu.cmpt276.spring2021.group8.project.game.*;

public class Player extends Entity {

    private BufferedImage player;
    public Player(Point startPosition) {
        super(startPosition);
    }

    public void move(Maze maze, Direction direction) {
        tryMove(maze, direction.getNewPosition(getPosition()));
    }

    @Override
    public void render(Graphics g, WorldScreenAdapter adapter) {

        player = ImageLoader.loadImage("src/resources/Images/Student.jpg");
        Rectangle offset = g.getClipBounds();
        Point playerScreenPosition = adapter.convert(getPosition());
        g.setColor(Color.BLUE);
        Draw.dot(g, offset.x + playerScreenPosition.x + adapter.gridHorizontalSpacing()/2, offset.y + playerScreenPosition.y + adapter.gridVerticalSpacing()/2, 16);
        // Image scaled = student.getScaledInstance(student.getHeight() / student.getWidth() * gridSpacing.x, student.getWidth() / student.getHeight() * gridSpacing.y, 0);
        //g.drawImage(player, offset.x + playerScreenPosition.x, offset.y + playerScreenPosition.y, null);
    }
}
