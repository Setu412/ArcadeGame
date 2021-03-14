package ca.sfu.cmpt276.spring2021.group8.project.game;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.BonusReward;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.Collectable;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.Punishment;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.Reward;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class World {
    private final static long MS_PER_ENEMY_MOVE = 1000;

    private Maze maze;
    private Player player;
    private LinkedList<Enemy> enemies = new LinkedList<>();
    private WorldScreenAdapter adapter;
//    private ArrayList<Reward> rewards = new ArrayList<>();
//    private ArrayList<Punishment> punishments = new ArrayList<>();
    private ArrayList<Collectable> collectables= new ArrayList<>();
    private BonusReward bonusReward;
    private long msSinceLastMove = 0;
    private long msSinceLastBRVisible = 0;
    private GameEffect MovementEffect;

    final static int REWARDS_POINTS = 2;
    final static int PUNISHMENT_POINTS = -4;
    final static int BONUS_POINTS = 5;

    private final static long MS_PER_BS_VISIBLE = 5000;

    public World(Maze maze) {
        this.maze = maze;
        this.player = new Player(maze.startPosition());
        this.adapter = new WorldScreenAdapter(maze.getSize(), new Point(50, 50));

        this.bonusReward = new BonusReward(maze.generatePosition());

        // Create rewards
        for (int i = 0; i < 40; i++) {
            collectables.add(new Reward(generateEmptyPosition()));
        }

        // Create punishments
        for (int i = 0; i < 20; i++) {
            collectables.add(new Punishment(generateEmptyPosition()));
        }

        //bonusReward.add(new BonusReward(new Point(0,0))); //update this (0,0) coordinate


        // TODO probably generate non-player entities here
        // this.enemies.add(new Enemy(this.player.getTargetedMovementGenerator(maze), new Point(0, 0)));
    }

    public World() {
        this(new Maze());
    }

//    private boolean isRewardPoint(Point p) {
//        for (Reward e : rewards) {
//            if (p.equals(e.getPosition())) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    private boolean isPunishmentPoint(Point p) {
//        for (Punishment e : punishments) {
//            if (p.equals(e.getPosition())) {
//                return true;
//            }
//        }
//
//        return false;
//    }

    private boolean isCollectiblePoint(Point p)
    {
        for (Collectable e:collectables)
        {
            if (p.equals(e.getPosition()))
            {
                return true;
            }
        }
        return false;
    }

    private boolean isEmptyPosition(Point p) {
        //return !isRewardPoint(p) && !isPunishmentPoint(p);
        return !isCollectiblePoint(p);
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
        msSinceLastMove += deltaTime;
        if (msSinceLastMove > MS_PER_ENEMY_MOVE) {
            msSinceLastMove -= MS_PER_ENEMY_MOVE;

            for (Enemy enemy : enemies) {
                enemy.move(maze);
            }
        }
        updateBR(deltaTime);
    }

    //does not work correctly.. requires reimplementation
    private void updateBR( long deltaTime) {
        msSinceLastBRVisible += deltaTime;
        if(msSinceLastBRVisible > MS_PER_BS_VISIBLE) {
            if(!bonusReward.isVisible) {
                Point p = maze.generatePosition();
                bonusReward.setPosition(p);
                bonusReward.isVisible = true;
                msSinceLastBRVisible -= MS_PER_BS_VISIBLE;
            }
            else
                bonusReward.isVisible = false;
        }
    }

    public GameEffect getGameEffect() {
        Point pos = player.getPosition();

        //if (false) { // cannot have false

         //    return MovementEffect.createLoseEffect();
        //}

        // check if hit collectible

        /**
         * Identifies type of Collectible and called createScoreEffect to update score
         */
//        for (int i = 0; i < rewards.size(); i++) {
//            if (rewards.get(i).getPosition().equals(pos)) {
//                rewards.remove(i);
//                if(rewards.size() == 0){
//                    maze.complete();
//                }
//                return MovementEffect.createScoreEffect(REWARDS_POINTS);
//            }
//        }
//        for (int i = 0; i < punishments.size(); i++) {
//            if (punishments.get(i).getPosition().equals(pos)) {
//                punishments.remove(i);
//                return MovementEffect.createScoreEffect(PUNISHMENT_POINTS);
//            }
//        }
        for (int i=0;i<collectables.size();i++)
        {
            if (collectables.get(i).getPosition().equals(pos))
            {
                GameEffect scoreEffect=GameEffect.createScoreEffect(collectables.get(i).getPoints());
                collectables.remove(i);
                return scoreEffect;
            }
        }
        if (bonusReward.getPosition().equals(pos)) {
            bonusReward.updateBRCoordinates(new Point());
            return GameEffect.createScoreEffect(BONUS_POINTS);
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

        bonusReward.render(g,adapter);
        maze.render(g,adapter);
        /**
         * render all the entities
         */
        for (Enemy enemy : enemies) {
            enemy.render(g, adapter);
        }
//        for (Reward i : rewards ) {
//            i.render(g, adapter);
//        }
//        for (Punishment i : punishments) {
//            i.render(g, adapter);
//        }
        for (Collectable e:collectables)
        {
            e.render(g,adapter);
        }

        // render the player last so it is on top
        player.render(g, adapter);
    }
}
