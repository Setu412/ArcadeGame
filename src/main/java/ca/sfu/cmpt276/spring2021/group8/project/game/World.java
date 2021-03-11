package ca.sfu.cmpt276.spring2021.group8.project.game;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.*;
import java.awt.*;
import java.util.LinkedList;

public class World {
    private final static long MS_PER_ENEMY_MOVE = 1000;

    private Maze maze;
    private Player player;
    private LinkedList<Enemy> enemies = new LinkedList<>();
    private WorldScreenAdapter adapter;
    private long msSinceLastMove = 0;

    public World(Maze maze) {
        this.maze = maze;
        this.player = new Player(maze.startPosition());
        this.adapter = new WorldScreenAdapter(maze.getSize(), new Point(60, 60));

        // TODO probably generate non-player entities here
        this.collectible = new Collectible(maze)
        // this.enemies.add(new Enemy(this.player.getTargetedMovementGenerator(maze), new Point(0, 0)));
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

    public void update(long deltaTime) {
        msSinceLastMove += deltaTime;
        if (msSinceLastMove > MS_PER_ENEMY_MOVE) {
            msSinceLastMove -= MS_PER_ENEMY_MOVE;

            for (Enemy enemy : enemies) {
                enemy.move(maze);
            }
        }
    }

    public GameEffect getGameEffect() {
        Point pos = player.getPosition();
        // if (false) { // check if hit enemy
        //     return MovementEffect.createLoseEffect();
        // }

        // if (false) { // check if hit collectible
        //     return MovementEffect.createScoreEffect(collectible.value);
        // }
        for (int i=0; i < rewards.size(); i++) {
            if (pos == rewards[i].position) {
                return MovementEffect.createScoreEffect(REWARDS_POINTS);
            }
        }
        for (int i=0; i < punishment.size(); i++) {
            if (pos == rewards[i].position) {
                return MovementEffect.createScoreEffect(PUNISHMENT_POINTS);
            }
        }
        for (int i=0; i < bonus.size(); i++) {
            if (pos == rewards[i].position) {
                return MovmeentEffect.createScoreEffect(BONUS_POINTS);
            }
        }

        return null;
    }

    public void movePlayer(Direction direction) {
        player.move(maze, direction);
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

        /**
         * render all the entities
         */
        for (Enemy enemy : enemies) {
            enemy.render(g, adapter);
        }

        // render the player last so it is on top
        player.render(g, adapter);
    }
}
