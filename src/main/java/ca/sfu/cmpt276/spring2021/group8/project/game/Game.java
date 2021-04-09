package ca.sfu.cmpt276.spring2021.group8.project.game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import ca.sfu.cmpt276.spring2021.group8.project.game.effect.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.Direction;
import ca.sfu.cmpt276.spring2021.group8.project.game.result.*;

/**
 * Game contains the world and updates the World entities after every loop and records key pressed
 */
public class Game implements KeyListener {
    private long startTime = System.currentTimeMillis();
    private long score = 0;
    private World world = new World();
    private boolean quitNextUpdate = false;

    /**
     * Updates the world and checks the game is over and further return the gameResult accordingly
     *
     * @param deltaTime Integer value containing time difference between two consecutive ticks
     * @return GameResult objects - loosingEffect if game is lost or WinningEffect if player wins the game
     */
    private GameResult update(long deltaTime) {
        if (quitNextUpdate) {
            return GameResult.createQuitResult();
        }

        world.update(deltaTime);

        GameEffect effect = world.getGameEffect();
        if (effect != null) {
            if (effect instanceof GameOverEffect) {
                if (((GameOverEffect) effect).win) {
                    System.out.println("won the game :D");
                    return GameResult.createWinResult(score, msSinceGameStart());
                } else {
                    System.out.println("lost the game :(");
                    return GameResult.createLoseResult(score, msSinceGameStart());
                }
            } else if (effect instanceof ScoreEffect) {
                //update score
                this.score += ((ScoreEffect) effect).score;
    
                //check if score is negative --> ends game
                if(this.score < 0){
                    System.out.println("lost the game :(");
                    return GameResult.createLoseResult(score, msSinceGameStart());
                }
            }
        }

        return null;
    }

    /**
     * Return the milliseconds since the game started
     *
     * @return long value of the milliseconds since the game started
     */
    private long msSinceGameStart() {
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Returns into standard format to time representation mm:ss in form of string
     *
     * @return Standard representation of time in form of string
     */
    private String getFormattedTime() {
        return TimeFormatConverter.convertTime(msSinceGameStart());
    }

    /**
     * Renders everything in the world on canvas including score, time, grid
     *
     * @param g Graphics object to draw on canvas
     * @param size Point object for the size of the maze
     */
    private void render(Graphics g, Point size) {
        world.render(g, size);
        g.setClip(null);

        // render timer
        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.drawString("Time: " + getFormattedTime(), 10, 30);

        // render score
        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.drawString("Score: " + score, 1180, 30);
    }

    /**
     * Continuous loop that controls the overall flow of the game until player attains a gameResult
     *
     * @param canvas Used to put everything the game renders onto
     * @return GameResult objects - loosingEffect if game is lost or WinningEffect if player wins the game
     */
    public GameResult loop(Canvas canvas) {
        BufferStrategy buffer = canvas.getBufferStrategy();
        if (buffer == null) {
            canvas.createBufferStrategy(2);
            buffer = canvas.getBufferStrategy();
        }

        long lastFrameTime = System.currentTimeMillis();
        while (true) {
            Rectangle rect = canvas.getBounds();
            // subtract the offset to get the actual canvas size
            Point size = new Point(rect.width, rect.height);

            Graphics g = buffer.getDrawGraphics();
            try {
                g.clearRect(0, 0, rect.width, rect.height);
                render(g, size);
                buffer.show();
                final long currentTime = System.currentTimeMillis();
                final long deltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;
                GameResult result = update(deltaTime);
                if (result != null) {
                    return result;
                }
            } finally {
                if (g != null) {
                    g.dispose();
                }
            }
        }
    }

    /**
     * Quits the game
     */
    private void quit() {
        quitNextUpdate = true;
    }

    /**
     * Record the key pressed and moves the player according to the corresponding Direction
     * @param e the KeyEvent of the key press
     * @see World#movePlayer(Direction)
     */
    @Override
    public void keyPressed(KeyEvent e) {
        Direction direction;
        switch (e.getKeyCode()) {
            default:
                return;

            case KeyEvent.VK_Q:
            case KeyEvent.VK_ESCAPE:
                quit();
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

        this.world.changePlayerNextMove(direction);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
