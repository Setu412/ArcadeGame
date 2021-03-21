package ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable;

import ca.sfu.cmpt276.spring2021.group8.project.ImageLoader;
import ca.sfu.cmpt276.spring2021.group8.project.game.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.MovementGenerator;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.PositionValidator;
import ca.sfu.cmpt276.spring2021.group8.project.Draw;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends MovableEntity {
    private BufferedImage enemyup = ImageLoader.loadImage("src/resources/Images/enemyup.png");
    private BufferedImage enemydown = ImageLoader.loadImage("src/resources/Images/enemydown.png");
    private BufferedImage enemyleft = ImageLoader.loadImage("src/resources/Images/enemyleft.png");
    private BufferedImage enemyright = ImageLoader.loadImage("src/resources/Images/enemyright.png");

    private MovementGenerator movementGenerator;

    public Enemy(MovementGenerator movementGenerator, Point startPosition) {
        super(startPosition);

        this.movementGenerator = movementGenerator;
    }

    public void move(PositionValidator validator) {
        tryMove(validator, movementGenerator.next(getPosition()).getNewPosition(getPosition()), movementGenerator.next(getPosition()));
    }

    @Override
    public void render(Graphics g, WorldScreenAdapter adapter) {

        Rectangle offset = g.getClipBounds();
        Point playerScreenPosition = adapter.convert(getPosition());
        //g.setColor(Color.RED);
        //Draw.dot(g, offset.x + playerScreenPosition.x + adapter.gridHorizontalSpacing()/2, offset.y + playerScreenPosition.y + adapter.gridVerticalSpacing()/2, 16);

        // Image scaled = student.getScaledInstance(student.getHeight() / student.getWidth() * gridSpacing.x, student.getWidth() / student.getHeight() * gridSpacing.y, 0);
        // g.drawImage(scaled, xoffset + playerScreenPosition.x, yoffset + playerScreenPosition.y, null);



        if (facing == Direction.North) {
            assert enemyup != null;
            Image result = enemyup.getScaledInstance(49, 49, Image.SCALE_DEFAULT);
            g.drawImage(result, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);
        } else if (facing == Direction.South) {
            assert enemydown != null;
            Image result = enemydown.getScaledInstance(49, 49, Image.SCALE_DEFAULT);
            g.drawImage(result, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);

        } else if (facing == Direction.East) {
            assert enemyright != null;
            Image result = enemyright.getScaledInstance(49, 49, Image.SCALE_DEFAULT);
            g.drawImage(result, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);

        } else {
            assert enemyleft != null;
            Image result = enemyleft.getScaledInstance(49, 49, Image.SCALE_DEFAULT);
            g.drawImage(result, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);
        }
    }
}
