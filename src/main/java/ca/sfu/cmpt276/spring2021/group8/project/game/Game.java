package ca.sfu.cmpt276.spring2021.group8.project.game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import ca.sfu.cmpt276.spring2021.group8.project.game.effect.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.result.*;

public class Game implements KeyListener {
    private long startTime = System.currentTimeMillis();
    private long score = 0;
    private World world = new World();
    private boolean quitNextUpdate = false;

    public Game() {
        // TODO initialize resources here
    }

    private GameResult update(long deltaTime) {
        if (quitNextUpdate) {
            return GameResult.createQuitResult();
        }

        /**
         * all game logic calling here and returning here
         * change isRunning to false when player wants to stop
         */
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

    private long msSinceGameStart() {
        return System.currentTimeMillis() - startTime;
    }

    private String getFormattedTime() {
        // TODO format time as xx:xx
        // We tried String.format(%tM, int) but it didn't work, but this is working but it's not great
        long seconds = msSinceGameStart()/1000;
        long minutes = (seconds / 60);
        seconds = seconds % 60;
        String strSecond = String.valueOf(seconds);
        String strMinute = String.valueOf(minutes);

        if (strMinute.length() < 2)
            strMinute = "0" + strMinute;
        if (strSecond.length() < 2)
            strSecond = "0" + strSecond;

        String formatTime =  strMinute + ":" + strSecond;
        return formatTime;

    }

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

        // TODO draw more ui elements
    }
    
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

    private void quit() {
        quitNextUpdate = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Direction direction;
        switch (e.getKeyCode()) {
            default:
                return;

            case KeyEvent.VK_Q:
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
