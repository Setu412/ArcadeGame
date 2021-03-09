package ca.sfu.cmpt276.spring2021.group8.project.game;

import java.awt.*;
import java.awt.event.*;

public class Game implements KeyListener {
    private long startTime = System.currentTimeMillis();
    private long score = 0;
    private World world = new World(20, 10);

    public Game() {
        // TODO initialize resources here
    }

    public void update(long deltaTime) {
        /**
         * all game logic calling here and returning here
         * change isRunning to false when player wants to stop
         */

        GameEffect effect = world.getGameEffect();
        if (effect != null) {
            if (effect.lose) {
                // TODO lose game here
                System.out.println("lost the game :(");
            }

            this.score += effect.score;
        }
    }

    private long msSinceGameStart() {
        return System.currentTimeMillis() - startTime;
    }

    private String getFormattedTime() {
        // TODO format time as xx:xx
        return String.valueOf(msSinceGameStart()/1000);
    }

    public void render(Graphics g, Point size) {
        world.render(g, size);
        g.setClip(null);

        // render timer
        g.setColor(Color.RED);
        g.drawString("Time: " + getFormattedTime(), 10, 20);

        // render score
        g.setColor(Color.RED);
        g.drawString("Score: " + score, 10, 30);

        // TODO draw more ui elements
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Direction direction;
        switch (e.getKeyCode()) {
            default:
                return;

            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                direction = Direction.West;
                break;

            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                direction = Direction.North;
                break;

            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                direction = Direction.East;
                break;

            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                direction = Direction.South;
                break;
        }

        this.world.movePlayer(direction);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}
