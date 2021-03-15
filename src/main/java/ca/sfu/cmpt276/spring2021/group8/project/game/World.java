package ca.sfu.cmpt276.spring2021.group8.project.game;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Collectables.BonusReward;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Collectables.Punishment;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Collectables.Reward;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class World {
    private final static long MS_PER_ENEMY_MOVE = 1000;
    private final static long MS_PER_BARRIER_CHANGE_POS = 13000;

    private Maze maze;
    private Player player;
    private LinkedList<Enemy> enemies = new LinkedList<>();
    private WorldScreenAdapter adapter;
    private ArrayList<Reward> rewards = new ArrayList<Reward>();
    private ArrayList<Punishment> punishments = new ArrayList<Punishment>();
    private BonusReward bonusReward;
    private ArrayList<Barrier> barriers = new ArrayList<Barrier>();
    private long msSinceLastMove = 0;
    private long msSinceLastBRVisible = 0;
    private long msSinceLastMoveBarrier = 0;
    private GameEffect MovementEffect;

    final static int REWARDS_POINTS = 2;
    final static int PUNISHMENT_POINTS = -4;
    final static int BONUS_POINTS = 5;

    private final static long MS_PER_BS_VISIBLE = 5000;

    public World(Maze maze) {
        this.maze = maze;
        this.player = new Player(maze.startPosition());
        this.adapter = new WorldScreenAdapter(maze.getSize(), new Point(50, 50));

        this.bonusReward = new BonusReward(maze.getCollectiblePoint());

        // Create rewards
        for (int i = 0; i < 40; i++) {
            rewards.add(new Reward(getEmptyCollectiblePoint()));
        }

        // Create punishments
        for (int i = 0; i < 20; i++) {
            punishments.add(new Punishment(getEmptyCollectiblePoint()));
        }

        for(int i = 0; i<10;i++){
            barriers.add(new Barrier(getEmptyCollectiblePoint()));
        }

        //bonusReward.add(new BonusReward(new Point(0,0))); //update this (0,0) coordinate


        // TODO probably generate non-player entities here
        // this.enemies.add(new Enemy(this.player.getTargetedMovementGenerator(maze), new Point(0, 0)));
    }

    public World() {
        this(new Maze());
    }

    private boolean isRewardPoint(Point p) {
        for (Reward e : rewards) {
            if (p.equals(e.getPosition())) {
                return true;
            }
        }

        return false;
    }

    private boolean isPunishmentPoint(Point p) {
        for (Punishment e : punishments) {
            if (p.equals(e.getPosition())) {
                return true;
            }
        }

        return false;
    }

    private boolean isBarrierPoint(Point p){
        for (Barrier e : barriers) {
            if (p.equals(e.getPosition())) {
                return true;
            }
        }

        return false;
    }

    private boolean isBRPoint(Point p){
        if(p.equals(bonusReward.getPosition()))
            return true;
        return false;
    }

    private boolean isEmptyPosition(Point p) {
        return !isRewardPoint(p) && !isPunishmentPoint(p) &&  !isBarrierPoint(p) && !isBRPoint(p);
    }

    private Point getEmptyCollectiblePoint() {
        // TODO fix this function for the case when there are no empty positions

        Point p;
        do {
            p = maze.getCollectiblePoint();
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
        updateEnemy(deltaTime);
        updateBarriers(deltaTime);
        updateBR(deltaTime);
    }
    public void updateEnemy(long deltaTime){
        msSinceLastMove += deltaTime;
        if (msSinceLastMove > MS_PER_ENEMY_MOVE) {
            msSinceLastMove -= MS_PER_ENEMY_MOVE;

            for (Enemy enemy : enemies) {
                enemy.move(maze);
            }
        }
    }
    public void updateBarriers(long deltaTime){
        msSinceLastMoveBarrier+= deltaTime;
        if (msSinceLastMoveBarrier > MS_PER_BARRIER_CHANGE_POS) {
            msSinceLastMoveBarrier -= MS_PER_BARRIER_CHANGE_POS;
            for (Barrier b : barriers) {
                Point newPosition = getEmptyCollectiblePoint();
                b.updatePosition(newPosition);
            }
        }
    }
    private void updateBR( long deltaTime) {
        msSinceLastBRVisible += deltaTime;
        if(msSinceLastBRVisible > MS_PER_BS_VISIBLE) {
            if(!bonusReward.isVisible) {
                Point p = getEmptyCollectiblePoint();
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
                try {
                    playMusic("src/resources/Audio/pokemonReward.wav");
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
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

    private void playMusic(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        Clip clip;
        // create AudioInputStream object
        AudioInputStream audioInputStream =
                AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        clip.start();
    }

    public void movePlayer(Direction direction) {
        Point newPositionOfPlayer = direction.getNewPosition(player.getPosition());
        if(isBarrierPoint(newPositionOfPlayer)){
            return;
        }
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
        for (Barrier i : barriers) {
            i.render(g, adapter);
        }

        // render the player last so it is on top
        player.render(g, adapter);
    }
}
