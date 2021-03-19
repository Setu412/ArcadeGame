package ca.sfu.cmpt276.spring2021.group8.project.game;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.game.containers.EntityList;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.CompositePositionValidator;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.*;

public class World {
    private final static long MS_PER_ENEMY_MOVE = 1000;
    private final static long MS_PER_BARRIER_CHANGE_POS = 13000;
    private final static long MS_PER_BS_VISIBLE = 5000;

    final static int NUM_REWARDS = 40;
    final static int NUM_PUNISHMENTS = 20;
    final static int NUM_BARRIERS = 10;

    private Maze maze;
    private Player player;
    private WorldScreenAdapter adapter;
    private BonusReward bonusReward;
    private long msSinceLastMove = 0;
    private long msSinceLastBRVisible = 0;
    private long msSinceLastMoveBarrier = 0;

    private EntityList<Barrier> barriers = new EntityList<>();
    private EntityList<Enemy> enemies = new EntityList<>();
    private EntityList<Collectable> collectables = new EntityList<>();


    public World(Maze maze) {
        this.maze = maze;
        this.player = new Player(maze.startPosition());
        this.adapter = new WorldScreenAdapter(maze.getSize(), new Point(50, 50));

        this.bonusReward = new BonusReward(generateEmptyPosition());

        // Create rewards
        for (int i = 0; i < NUM_REWARDS; i++) {
            collectables.add(new Reward(generateEmptyPosition()));
        }

        // Create punishments
        for (int i = 0; i < NUM_PUNISHMENTS; i++) {
            collectables.add(new Punishment(generateEmptyPosition()));
        }

        for(int i = 0; i< NUM_BARRIERS;i++) {
            barriers.add(new Barrier(generateEmptyPosition()));
        }


        // TODO probably generate non-player entities here
        for (int i=0;i<5;i++) {
            this.enemies.add(new Enemy(this.player.getTargetedMovementGenerator(maze), generateEmptyPosition()));
        }


    }

    public World() {
        this(new Maze());
    }

    private boolean isCollectiblePoint(Point p) {
        return !collectables.isValidPosition(p);
    }

    private boolean isBarrierPoint(Point p) {
        return !barriers.isValidPosition(p);
    }

    private boolean isBRPoint(Point p) {
        return bonusReward != null && bonusReward.getPosition().equals(p);
    }

    private boolean isEmptyPosition(Point p) {
        return !isCollectiblePoint(p) &&  !isBarrierPoint(p) && !isBRPoint(p);
    }

    private Point generateEmptyPosition() {
        // TODO fix this function for the case when there are no empty positions

        Point p;
        do {
            p = maze.generatePosition();
        } while (!isEmptyPosition(p));

        return p;
    }

    public Point getSize() {
        return maze.getSize();
    }

    public Point playerPosition() {
        return player.getPosition();
    }

    public void update(long deltaTime) {
        updateBarriers(deltaTime);
        updateEnemy(deltaTime);
        updateBR(deltaTime);
    }

    public void updateEnemy(long deltaTime){
        msSinceLastMove += deltaTime;
        if (msSinceLastMove > MS_PER_ENEMY_MOVE) {
            msSinceLastMove -= MS_PER_ENEMY_MOVE;

            for (Enemy enemy : enemies) {
                enemy.move(new CompositePositionValidator(maze, barriers, enemies));
            }
        }
    }

    public void updateBarriers(long deltaTime){
        msSinceLastMoveBarrier+= deltaTime;
        if (msSinceLastMoveBarrier > MS_PER_BARRIER_CHANGE_POS) {
            msSinceLastMoveBarrier -= MS_PER_BARRIER_CHANGE_POS;
            for (Barrier b : barriers) {
                b.updatePosition(generateEmptyPosition());
            }
        }
    }

    private void updateBR(long deltaTime) {
        msSinceLastBRVisible += deltaTime;
        if (msSinceLastBRVisible > MS_PER_BS_VISIBLE) {
            if (!bonusReward.isVisible) {
                bonusReward.setPosition(generateEmptyPosition());
            }

            bonusReward.isVisible = !bonusReward.isVisible;
            msSinceLastBRVisible -= MS_PER_BS_VISIBLE;
        }
    }

    public GameEffect getGameEffect() {
        Point pos = player.getPosition();

        /**
         * Identifies type of Collectible and called createScoreEffect to update score
         */
        for (Collectable collectable : collectables) {
            if (!collectable.getPosition().equals(pos)) {
                continue;
            }

            if (collectable instanceof Reward) {
                try {
                    GameEffect.playMusic("src/resources/Audio/RewardCollection.wav");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (collectable instanceof Punishment) {
                try {
                    GameEffect.playMusic("src/resources/Audio/Punishment.wav");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            collectables.remove(collectable);
            return GameEffect.createScoreEffect(collectable.getPoints());
        }


        if (bonusReward.isVisible) {
            if (bonusReward.getPosition().equals(pos)) {
                bonusReward.isVisible  = false;
                msSinceLastBRVisible = 0;

                    try {
                        GameEffect.playMusic("src/resources/Audio/BRCollection.wav");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                return GameEffect.createScoreEffect(bonusReward.getPoints());
            }
        }

        for (Enemy enemy : enemies) {
            if (enemy.getPosition().equals(pos)) {
                return GameEffect.createLoseEffect();
            }
        }

        return null;
    }

    public void movePlayer(Direction direction) {
        player.move(new CompositePositionValidator(maze, barriers), direction);
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
            Point right = adapter.convert(adapter.worldWidth(), y);
            g.drawLine(xoffset + left.x, yoffset + left.y, xoffset + right.x, yoffset + right.y);
        }

        int xPadding = adapter.gridVerticalSpacing()/2;
        int yPadding = adapter.gridHorizontalSpacing()/2;
        for (int x = 0; x < adapter.worldWidth(); x++) {
            for (int y = 0; y < adapter.worldHeight(); y++) {
                Point screenPoint = adapter.convert(x, y);
                Draw.dot(g, xoffset + screenPoint.x + xPadding, yoffset + screenPoint.y + yPadding, 2);
            }
        }
    }

    public void render(Graphics g, Point size) {
        Point gridSize = adapter.gridSize();

        int xOffset = (size.x - gridSize.x) / 2;
        int yOffset = (size.y - gridSize.y) / 2;

        drawGrid(g, xOffset, yOffset);

        g.clipRect(xOffset, yOffset, gridSize.x, gridSize.y);


        maze.render(g, adapter);
        bonusReward.render(g, adapter);
        /**
         * render all the entities
         */

        for (Collectable e:collectables) {
            e.render(g, adapter);
        }

        for (Enemy enemy : enemies) {
            enemy.render(g, adapter);
        }

        for (Barrier i : barriers) {
            i.render(g, adapter);
        }

        // render the player last so it is on top
        player.render(g, adapter);
    }
}
