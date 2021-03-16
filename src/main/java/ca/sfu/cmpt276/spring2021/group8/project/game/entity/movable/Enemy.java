package ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable;

import ca.sfu.cmpt276.spring2021.group8.project.game.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.MovementGenerator;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.PositionValidator;
import ca.sfu.cmpt276.spring2021.group8.project.Draw;

import java.awt.*;

public class Enemy extends MovableEntity {
    private MovementGenerator movementGenerator;

    public Enemy(MovementGenerator movementGenerator, Point startPosition) {
        super(startPosition);

        this.movementGenerator = movementGenerator;
    }

    public void move(PositionValidator validator) {
        tryMove(validator, movementGenerator.next(getPosition()).getNewPosition(getPosition()));
    }

    @Override
    public void render(Graphics g, WorldScreenAdapter adapter) {
        Rectangle offset = g.getClipBounds();
        Point playerScreenPosition = adapter.convert(getPosition());
        g.setColor(Color.RED);
        Draw.dot(g, offset.x + playerScreenPosition.x + adapter.gridHorizontalSpacing()/2, offset.y + playerScreenPosition.y + adapter.gridVerticalSpacing()/2, 16);

        // Image scaled = student.getScaledInstance(student.getHeight() / student.getWidth() * gridSpacing.x, student.getWidth() / student.getHeight() * gridSpacing.y, 0);
        // g.drawImage(scaled, xoffset + playerScreenPosition.x, yoffset + playerScreenPosition.y, null);
    }
}