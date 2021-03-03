package ca.sfu.cmpt276.spring2021.group8.project;

// import ca.sfu.cmpt276.spring2021.group8.project.input.UserInput;
// import ca.sfu.cmpt276.spring2021.group8.project.screens.MainMenu;
// import de.gurkenlabs.litiengine.Game;
// import de.gurkenlabs.litiengine.environment.Environment;
// import de.gurkenlabs.litiengine.environment.EnvironmentLoadedListener;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Canvas;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Color;

public class Main extends Canvas implements KeyListener {
    private Point playerPosition = new Point();

    public static void main(String[] args) {
        int width = 1280;
        int height = 720;

        Frame f = new Frame();
        final Main main = new Main();
        main.setSize(width, height);
        f.add(main);
        f.pack();
        f.setSize(width, height);
        f.setVisible(true);
        main.createBufferStrategy(3);
    }

    private Main() {
        this.addKeyListener(this);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component component = e.getComponent();
                if (component != null) {
                    Rectangle bounds = component.getBounds();
                    setSize(bounds.width, bounds.height);
                }
            }
        });
    }

    private static void drawDot(Graphics g, int x, int y, int r) {
        g.fillOval(x - r, y - r, r*2, r*2);
    }

    private Point worldSize = new Point(20, 10);
    private Point gridSpacing = new Point(60, 60);

    private Point gridSize() {
        return new Point((worldSize.x - 1) * gridSpacing.x, (worldSize.y - 1) * gridSpacing.y);
    }

    private Point worldToScreenPoint(int x, int y) {
        return new Point(x * gridSpacing.x, y * gridSpacing.y);
    }

    private Point worldToScreenPoint(Point p) {
        return worldToScreenPoint(p.x, p.y);
    }

    private void drawGrid(Graphics g, int xoffset, int yoffset) {
        xoffset -= gridSpacing.x/2;
        yoffset -= gridSpacing.y/2;
        for (int x = 0; x <= worldSize.x; x++) {
            Point top = worldToScreenPoint(x, 0);
            Point bottom = worldToScreenPoint(x, worldSize.y);
            g.drawLine(xoffset + top.x, yoffset + top.y, xoffset + bottom.x, yoffset + bottom.y);
        }

        for (int y = 0; y <= worldSize.y; y++) {
            Point left = worldToScreenPoint(0, y);
            Point rigth = worldToScreenPoint(worldSize.x, y);
            g.drawLine(xoffset + left.x, yoffset + left.y, xoffset + rigth.x, yoffset + rigth.y);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Rectangle rect = this.getBounds();
        // subtract the offset to get the actual canvas size
        Point size = new Point(rect.width, rect.height);

        paint(g, size);
    }

    public void paint(Graphics g, Point size) {
        int dotradius = 2;
        Point gridSize = gridSize();

        int xoffset = (size.x - gridSize.x) / 2;
        int yoffset = (size.y - gridSize.y) / 2;
        for (int x = 0; x < worldSize.x; x++) {
            for (int y = 0; y < worldSize.y; y++) {
                Point screenPoint = worldToScreenPoint(x, y);
                drawDot(g, xoffset + screenPoint.x, yoffset + screenPoint.y, dotradius);
            }
        }

        drawGrid(g, xoffset, yoffset);

        Point playerScreenPosition = worldToScreenPoint(playerPosition);
        g.setColor(Color.BLUE);
        drawDot(g, xoffset + playerScreenPosition.x, yoffset + playerScreenPosition.y, 16);

        // int width = 200;
        // int height = 200;
        // g.fillOval((rect.width - width)/2, (rect.height - height)/2, width, height);

        // g.setColor(Color.RED);
        // g.drawLine(rect.width / 2, 0, rect.width / 2, rect.height);
        // g.drawLine(0, rect.height / 2, rect.width, rect.height / 2);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            default:
                return;

            case KeyEvent.VK_LEFT:
                playerPosition.x -= 1;
                break;

            case KeyEvent.VK_UP:
                playerPosition.y -= 1;
                break;

            case KeyEvent.VK_RIGHT:
                playerPosition.x += 1;
                break;

            case KeyEvent.VK_DOWN:
                playerPosition.y += 1;
                break;
        }

        if (playerPosition.x < 0) {
            playerPosition.x = 0;
        } else if (playerPosition.x >= worldSize.x) {
            playerPosition.x = worldSize.x - 1;
        }

        if (playerPosition.y < 0) {
            playerPosition.y = 0;
        } else if (playerPosition.y >= worldSize.y) {
            playerPosition.y = worldSize.y - 1;
        }

        this.repaint();
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
