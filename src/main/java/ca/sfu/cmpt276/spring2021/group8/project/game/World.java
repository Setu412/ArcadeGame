package ca.sfu.cmpt276.spring2021.group8.project.game;

import ca.sfu.cmpt276.spring2021.group8.project.game.containers.EntityList;
import ca.sfu.cmpt276.spring2021.group8.project.game.effect.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.CompositePositionValidator;

import java.awt.*;

/**
 * The World class contains all the game entities and handles interactions between these entities, and renders
 */
public class World {
    private final static long MS_PER_ENEMY_MOVE = 1000;
    private final static long MS_PER_BARRIER_CHANGE_POS = 13000;
    private final static long MS_PER_BS_VISIBLE = 5000;

    private final static int NUM_REWARDS = 40; //40
    private static int NUM_PUNISHMENTS = 20; //20
    private final static int NUM_BARRIERS = 10; //10
    private final static int NUM_ENEMIES=5; //5

    private Maze maze;
    private Player player;
    private WorldScreenAdapter adapter;
    private BonusReward bonusReward;
    private EntityList<Barrier> barriers = new EntityList<>();
    private EntityList<Enemy> enemies = new EntityList<>();
    private EntityList<Collectable> collectables = new EntityList<>();

    private long msSinceLastMove = 0;
    private long msSinceLastBRVisible = 0;
    private long msSinceLastMoveBarrier = 0;
    private int rewardCollected = 0;

    /**
     * Constructs the World class object, assigns maze object parameter to maze field and instantiate all the entities
     *
     * @param maze Maze object containing maze design
     */
    public World(Maze maze) {
        this.maze = maze;
        this.player = new Player(maze.startPosition());
        this.adapter = new WorldScreenAdapter(maze.getSize(), new Point(50, 50));

        this.bonusReward = new BonusReward(generateEmptyPosition());

        // Generating rewards
        for (int i = 0; i < NUM_REWARDS; i++) {
            collectables.add(new Reward(generateEmptyPosition()));
        }

        // Generating punishments
        for (int i = 0; i < NUM_PUNISHMENTS; i++) {
            collectables.add(new Punishment(generateEmptyPosition()));
        }

        //Generating Barriers
        for(int i = 0; i< NUM_BARRIERS;i++) {
            barriers.add(new Barrier(generateEmptyPosition()));
        }

        //Generating Enemies
        for (int i=0;i<NUM_ENEMIES;i++) {
            this.enemies.add(new Enemy(this.player.getTargetedMovementGenerator(maze), generateEmptyPosition()));
        }
    }

    /**
     * Constructs World class object
     */
    public World() {
        this(new Maze());
    }

    /**
     * Ensures none of the Collectable entities already exists on new point
     *
     * @param p Point object having new position generated
     * @return returns true if any other collectable already exists on new point, returns false if it does not
     */
    private boolean isCollectiblePoint(Point p) {
        return !collectables.isValidPosition(p);
    }

    /**
     * Ensures none of the Barrier already exists on new point
     *
     * @param p Point object having new position generated
     * @return returns true if any other Barrier already exists on new point, returns false if it does not
     */
    private boolean isBarrierPoint(Point p) {
        return !barriers.isValidPosition(p);
    }

    /**
     * Ensures none of the Bonus Reward entities already exists on new point
     *
     * @param p Point object having new position generated
     * @return returns true if any other Bonus Reward already exists on new point, returns false if it does not
     */
    private boolean isBRPoint(Point p) {
        return bonusReward != null && bonusReward.getPosition().equals(p);
    }

    /**
     * Makes call to different types of entity to make sure newly generated position is empty
     *
     * @param p Point object having new position generated
     * @return returns true if any other entity already exists on new point, returns false if it does not
     */
    private boolean isEmptyPosition(Point p) {
        return !isCollectiblePoint(p) &&  !isBarrierPoint(p) && !isBRPoint(p) && !isAroundPlayer(p);
    }

    /**
     *  Ensure that the the new generated position is not within 1 block around the player
     *
     * @param p Point object having new position generated
     * @return True if the new generated point is around the player, Returns false if it is not around the player
     */
    private boolean isAroundPlayer(Point p) {
        Point pos = player.getPosition();

        if(Math.abs(pos.x - p.x)<2 && Math.abs(pos.y - p.y)<2)
            return true;
        return false;
    }

    /**
     * Generates a new empty position
     *
     * @return Point object having the new empty position
     */
    private Point generateEmptyPosition() {
        // TODO fix this function for the case when there are no empty positions

        Point p;
        do {
            p = maze.generatePosition();
        } while (!isEmptyPosition(p));

        return p;
    }

    /**
     * Calls individual update functions for Barriers, enemies and Bonus reward
     *
     * @param deltaTime Integer value containing time difference between two consecutive ticks
     */
    public void update(long deltaTime) {
        updateBarriers(deltaTime);
        updateEnemy(deltaTime);
        updateBR(deltaTime);
    }

    /**
     * Ensures enemies make a move in game after every 1 second
     *
     * @param deltaTime Integer value containing time difference between two consecutive ticks
     */
    public void updateEnemy(long deltaTime){
        msSinceLastMove += deltaTime;
        if (msSinceLastMove > MS_PER_ENEMY_MOVE) {
            msSinceLastMove -= MS_PER_ENEMY_MOVE;

            for (Enemy enemy : enemies) {
                if (player.getHasMoved()) {
                    enemy.move(new CompositePositionValidator(maze, barriers, enemies));
                }
            }
        }
    }

    /**
     * Updates the position of barriers with new position after every 13 seconds
     *
     * @param deltaTime Integer value containing time difference between two consecutive ticks
     */
    public void updateBarriers(long deltaTime){
        msSinceLastMoveBarrier+= deltaTime;
        if (msSinceLastMoveBarrier > MS_PER_BARRIER_CHANGE_POS) {
            msSinceLastMoveBarrier -= MS_PER_BARRIER_CHANGE_POS;
            for (Barrier b : barriers) {
                b.updatePosition(generateEmptyPosition());
            }
        }
    }

    /**
     * Ensures BonusReward appears and disappears in the game after every 5 second.
     * Updates the position of BonusReward with new position after each reappearance
     *
     * @param deltaTime Integer value containing time difference between two consecutive ticks
     */
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

    /**
     * Identifies type of Collectible and called createScoreEffect to update score,
     * or createLoseeffct with enemy is encountered or createWinEffect in case all reward are collected
     * upon player movemenet
     *
     * @return the type of GameEffect for eg. WinEffect, LoseEffects or ScoreEffect
     */
    public GameEffect getGameEffect() {
        Point pos = player.getPosition();

        for (Collectable collectable : collectables) {
            if (!collectable.getPosition().equals(pos)) {
                continue;
            }

            if (collectable instanceof Reward) {
                try {
                    SoundEffects.playMusic("src/resources/Audio/RewardCollection.wav");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                rewardCollected++;
                if(rewardCollected == NUM_REWARDS){
                    maze.complete();
                }
            }

            if (collectable instanceof Punishment) {
                try {
                    SoundEffects.playMusic("src/resources/Audio/Punishment.wav");
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
                        SoundEffects.playMusic("src/resources/Audio/BRCollection.wav");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                return GameEffect.createScoreEffect(bonusReward.getPoints());
            }
        }

        for (Enemy enemy : enemies) {
            if (enemy.getPosition().equals(pos)) {

                try {
                    SoundEffects.playMusic("src/resources/Audio/Punishment.wav");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return GameEffect.createLoseEffect();
            }
        }

        if (player.getPosition().equals(maze.exitPosition())) {
            return GameEffect.createWinEffect();
        }
        return null;
    }

    public void movePlayer(Direction direction) {
        player.move(new CompositePositionValidator(maze, barriers), direction);
    }

    /**
     * Draws the grid onto canvas
     *
     * @param g Graphics object to draw on canvas
     * @param xoffset Horizontal distance from the top of canvas to the intended position
     * @param yoffset Vertical distance from the top of canvas to the intended position
     */
    private void drawGrid(Graphics g, int xoffset, int yoffset) {
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
    }

    /**
     * Makes calls to overridden render function of all game entities after each tick.
     *
     * @param g Graphics object to draw on canvas
     * @param size
     */
    public void render(Graphics g, Point size) {
        Point gridSize = adapter.gridSize();

        int xOffset = (size.x - gridSize.x) / 2;
        int yOffset = (size.y - gridSize.y) / 2;

        drawGrid(g, xOffset, yOffset);
        g.clipRect(xOffset, yOffset, gridSize.x, gridSize.y);

        maze.render(g, adapter);

        bonusReward.render(g, adapter);

        for (Collectable e:collectables) {
            e.render(g, adapter);
        }

        for (Barrier i : barriers) {
            i.render(g, adapter);
        }

        player.render(g, adapter);

        for (Enemy enemy : enemies) {
            enemy.render(g, adapter);
        }
    }
}
