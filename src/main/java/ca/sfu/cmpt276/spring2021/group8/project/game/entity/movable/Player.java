package ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.ImageLoader;
import ca.sfu.cmpt276.spring2021.group8.project.game.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.PositionValidator;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Player extends MovableEntity {
    private BufferedImage playerup;
    private BufferedImage playerdown;
    private BufferedImage playerright;
    private BufferedImage playerleft;


    public Player(Point startPosition) {
        super(startPosition);
    }

    public void move(PositionValidator validator, Direction direction) {
        tryMove(validator, direction.getNewPosition(getPosition()), direction);
    }

    @Override
    public void render(Graphics g, WorldScreenAdapter adapter) {


        Rectangle offset = g.getClipBounds();
        Point playerScreenPosition = adapter.convert(getPosition());
        //g.setColor(Color.BLUE);
        //Draw.dot(g, offset.x + playerScreenPosition.x + adapter.gridHorizontalSpacing()/2, offset.y + playerScreenPosition.y + adapter.gridVerticalSpacing()/2, 16);

        //Image scaled = student.getScaledInstance(student.getHeight() / student.getWidth() * gridSpacing.x, student.getWidth() / student.getHeight() * gridSpacing.y, 0);

        playerup = ImageLoader.loadImage("src/resources/Images/playerup.png");
        playerdown = ImageLoader.loadImage("src/resources/Images/playerdown.png");
        playerleft = ImageLoader.loadImage("src/resources/Images/playerright.png");
        playerright = ImageLoader.loadImage("src/resources/Images/playerleft.png");

        if (facing == Direction.North) {
            assert playerup != null;
            Image result = playerup.getScaledInstance(49, 49, Image.SCALE_DEFAULT);
            g.drawImage(result, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);
        } else if (facing == Direction.South) {
            assert playerdown != null;
            Image result = playerdown.getScaledInstance(49, 49, Image.SCALE_DEFAULT);
            g.drawImage(result, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);

        } else if (facing == Direction.East) {
            assert playerleft != null;
            Image result = playerleft.getScaledInstance(49, 49, Image.SCALE_DEFAULT);
            g.drawImage(result, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);

        } else {
            assert playerright != null;
            Image result = playerright.getScaledInstance(49, 49, Image.SCALE_DEFAULT);
            g.drawImage(result, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);
        }


        /*
        switch (facing) {
            case North:
                // Sprite facing up
                Image result = playerup.getScaledInstance(49, 49, Image.SCALE_DEFAULT);
                g.drawImage(result, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);
                break;
            case South:
                // Sprite facing down
                Image result2 = playerup.getScaledInstance(49, 49, Image.SCALE_DEFAULT);

                g.drawImage(result2, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);
                break;
            case East:
                // Sprite facing right

                Image result3 = playerup.getScaledInstance(49, 49, Image.SCALE_DEFAULT);

                g.drawImage(result3, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);
                break;
            case West:
                // Sprite facing left

                Image result4 = playerup.getScaledInstance(49, 49, Image.SCALE_DEFAULT);

                g.drawImage(result4, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);
                break;
            default:
                Image result5 = playerup.getScaledInstance(49, 49, Image.SCALE_DEFAULT);

                g.drawImage(result5, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null);
                break;
        }

        /*
        playerup = ImageLoader.loadImage("src/resources/Images/Student.jpg");
        Image result = playerup.getScaledInstance(49, 49, Image.SCALE_DEFAULT);

        g.drawImage(result, offset.x + playerScreenPosition.x + 1, offset.y + playerScreenPosition.y + 1, null); */

    }
}
