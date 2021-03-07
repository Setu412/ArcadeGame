package ca.sfu.cmpt276.spring2021.group8.project.game.entity;

import java.awt.*;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.game.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.Collectable;

public class Player extends Entity {
    public Player(Maze maze) {
        super(maze);
        super.move(maze.startPosition());
    }

    public void move(Direction direction) {
        Point position = getPosition();
        int[] currentXY = {position.x, position.y};
        switch (direction) {
            default:
                return;

            case West:
                position.x -= 1;
                break;

            case North:
                position.y -= 1;
                break;

            case East:
                position.x += 1;
                break;

            case South:
                position.y += 1;
                break;
        }

        tryMove(position, currentXY);
    }

    @Override
    public void render(Graphics g, WorldScreenAdapter adapter) {
        Rectangle offset = g.getClipBounds();
        Point playerScreenPosition = adapter.convert(getPosition());
        g.setColor(Color.BLUE);
        Draw.dot(g, offset.x + playerScreenPosition.x + adapter.gridHorizontalSpacing()/2, offset.y + playerScreenPosition.y + adapter.gridVerticalSpacing()/2, 16);


        // Image scaled = student.getScaledInstance(student.getHeight() / student.getWidth() * gridSpacing.x, student.getWidth() / student.getHeight() * gridSpacing.y, 0);
        // g.drawImage(scaled, xoffset + playerScreenPosition.x, yoffset + playerScreenPosition.y, null);
    }
}
