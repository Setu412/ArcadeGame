package ca.sfu.cmpt276.spring2021.group8.project.game.entity;

import java.awt.*;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.game.*;

public class Player extends Entity {
    public Player(Point startPosition) {
        super.move(startPosition);
    }

    public void move(Maze maze, Direction direction) {
        tryMove(maze, direction.getNewPosition(getPosition()));
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
