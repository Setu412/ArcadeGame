package ca.sfu.cmpt276.spring2021.group8.project.game;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Enemy;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Entity;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Player;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.BonusReward;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.Punishment;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.Reward;

import java.awt.*;

public class World {
    protected Maze maze;
    private Player player;
    private Reward reward;
    private BonusReward bonusReward;
    private Punishment punishment;
    private Enemy enemy;
    private WorldScreenAdapter adapter;

    public World(Maze maze) {
        this.maze = maze;
        this.player = new Player(maze);
        this.adapter = new WorldScreenAdapter(maze.getSize(), new Point(60, 60));
    }

    public World(Point size) {
        this(new Maze(size));
    }

    public World(int width, int height) {
        this(new Maze(width, height));
    }

    public Point getSize() {
        return maze.getSize();
    }

    public Point playerPosition() {
        return player.getPosition();
    }

    public void movePlayer(Direction direction) {
        player.move(direction);
    }

    private void drawGrid(Graphics g, int xoffset, int yoffset) {
        // TODO replace with actual map textures
        g.setColor(Color.BLACK);

        for (int x = 0; x <= adapter.worldWidth(); x++) {
            Point top = adapter.convert(x, 0);
            Point bottom = adapter.convert(x, adapter.worldHeight());
            g.drawLine(xoffset + top.x, yoffset + top.y, xoffset + bottom.x, yoffset + bottom.y);
        }

        for (int y = 0; y <= adapter.worldHeight(); y++) {
            Point left = adapter.convert(0, y);
            Point rigth = adapter.convert(adapter.worldWidth(), y);
            g.drawLine(xoffset + left.x, yoffset + left.y, xoffset + rigth.x, yoffset + rigth.y);
        }

        int xpadding = adapter.gridVerticalSpacing()/2;
        int ypadding = adapter.gridHorizontalSpacing()/2;
        for (int x = 0; x < adapter.worldWidth(); x++) {
            for (int y = 0; y < adapter.worldHeight(); y++) {
                Point screenPoint = adapter.convert(x, y);
                Draw.dot(g, xoffset + screenPoint.x + xpadding, yoffset + screenPoint.y + ypadding, 2);
            }
        }
    }

    public void render(Graphics g, Point size) {
        Point gridSize = adapter.gridSize();

        int xoffset = (size.x - gridSize.x) / 2;
        int yoffset = (size.y - gridSize.y) / 2;

        drawGrid(g, xoffset, yoffset);

        g.clipRect(xoffset, yoffset, gridSize.x, gridSize.y);

        // render entities
        player.render(g, adapter);
        //reward.render(g,adapter);
        //punishment.render(g,adapter);
        //bonusReward.render(g,adapter);
        //enemy.render(g,adapter);
    }
}
