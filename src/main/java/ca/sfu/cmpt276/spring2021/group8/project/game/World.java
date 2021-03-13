package ca.sfu.cmpt276.spring2021.group8.project.game;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Collectables.BonusReward;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Collectables.Punishment;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Collectables.Reward;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class World {
    private final static long MS_PER_ENEMY_MOVE = 1000;

    private Maze maze;
    private Player player;
    private LinkedList<Enemy> enemies = new LinkedList<>();
    private WorldScreenAdapter adapter;
    private ArrayList<Reward> rewards = new ArrayList<Reward>();
    private ArrayList<Punishment> punishments = new ArrayList<Punishment>();
    private BonusReward bonusReward;
    private long msSinceLastMove = 0;
    private long msSinceLastBRVisible = 0;
    private GameEffect MovementEffect;

    final static int REWARDS_POINTS = 2;
    final static int PUNISHMENT_POINTS = -4;
    final static int BONUS_POINTS = 5;

    private final static long MS_PER_BS_VISIBLE = 5000;


    private int tickCount = 0;

    public World(Maze maze) {
        this.maze = maze;
        this.player = new Player(maze.startPosition());
        this.adapter = new WorldScreenAdapter(maze.getSize(), new Point(50, 50));

        this.bonusReward = new BonusReward(maze.getCollectiblePoint());

        /*for (int i = 0; i < 40; i++) {
            Point p = maze.getCollectiblePoint()();
            rewards.add(new Reward(p));
        }*/

        // Create rewards
        int k = 0;
        while (k< 40){
            boolean status = true;
            Point p = maze.getCollectiblePoint();
            if (k == 0) {
                rewards.add(new Reward(p));
                k++;
                continue;
            }
            for (Reward r : rewards) {
                if (p.equals(r.getPosition())) {
                    status = false;
                    break;
                }
            }

            if (status) {
                rewards.add(new Reward(p));
                k++;
            }
        }

        // Create punishments
        k = 0;
        while (k< 20){
            boolean status = true;
            Point p = maze.getCollectiblePoint();
            if (k == 0) {
                for (Reward r : rewards) {
                    if (p.equals(r.getPosition())) {
                        status = false;
                        break;
                    }
                }
                if (status) {
                    punishments.add(new Punishment(p));
                    k++;
                    continue;
                }

            }
            for (Reward r : rewards) {
                if (p.equals(r.getPosition())) {
                    status = false;
                    break;
                }
            }

            for (Punishment b : punishments) {
                if (p.equals(b.getPosition())) {
                    status = false;
                    break;
                }
            }

            if (p.equals(maze.nextToStart) || p.equals(maze.nextToExit)) {
                status = false;
            }

            if (status) {
                punishments.add(new Punishment(p));
                k++;
            }

        }


        /*for(int i=0;i<15;i++){
            Point p = maze.getCollectiblePoint();
            punishments.add(new Punishment(p));
        }*/
        //maze.resetMaze();

        //bonusReward.add(new BonusReward(new Point(0,0))); //update this (0,0) coordinate


        // TODO probably generate non-player entities here
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
        else
        updateBR(deltaTime);
    }

    //does not work correctly.. requires reimplementation
    private void updateBR( long deltaTime) {
        msSinceLastBRVisible += deltaTime;
        if(msSinceLastBRVisible > MS_PER_BS_VISIBLE) {
            if(!bonusReward.isVisible) {
                Point p = maze.getCollectiblePoint();
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
        for (int i = 0; i < rewards.size(); i++) {
            if (rewards.get(i).getPosition().equals(pos)) {
                rewards.remove(i);
                if(rewards.size() == 0){
                    maze.complete();
                }
                return MovementEffect.createScoreEffect(REWARDS_POINTS);
            }
        }
        for (int i = 0; i < punishments.size(); i++) {
            if (punishments.get(i).getPosition().equals(pos)) {
                punishments.remove(i);
                return MovementEffect.createScoreEffect(PUNISHMENT_POINTS);
            }
        }
        if (bonusReward.getPosition().equals(pos)) {
            bonusReward.updateBRCoordinates(new Point());
            return MovementEffect.createScoreEffect(BONUS_POINTS);
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

        tickCount++;
        Point gridSize = adapter.gridSize();

        int xoffset = (size.x - gridSize.x) / 2;
        int yoffset = (size.y - gridSize.y) / 2;

        drawGrid(g, xoffset, yoffset);

        g.clipRect(xoffset, yoffset, gridSize.x, gridSize.y);

        bonusReward.render(g,adapter);
        maze.render(g,adapter);
        /**
         * render all the entities
         */
        for (Enemy enemy : enemies) {
            enemy.render(g, adapter);
        }
        for (Reward i : rewards ) {
            i.render(g, adapter);
        }
        for (Punishment i : punishments) {
            i.render(g, adapter);
        }

        // render the player last so it is on top
        player.render(g, adapter);

        rewards.render(g,adapter);
        punishments.render(g,adapter);

        //logic to make bonus reward appear in few ticks
        if(tickCount%10 == 0)
        {
            maze.TimedApprearance();
            //0-10
            //10-20 generate, bonus reward will be there on the maze
            //20-30 removed
            //should be removed or generated
        }
        bonusRewards.render(g,adapter);

    }
}
