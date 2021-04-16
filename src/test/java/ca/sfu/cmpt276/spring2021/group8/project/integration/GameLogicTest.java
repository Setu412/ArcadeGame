package ca.sfu.cmpt276.spring2021.group8.project.integration;

import ca.sfu.cmpt276.spring2021.group8.project.game.containers.EntityList;
import ca.sfu.cmpt276.spring2021.group8.project.game.effect.GameEffect;
import ca.sfu.cmpt276.spring2021.group8.project.game.effect.GameOverEffect;
import ca.sfu.cmpt276.spring2021.group8.project.game.effect.ScoreEffect;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Barrier;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.BonusReward;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.Collectable;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.Punishment;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.Reward;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable.Enemy;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable.Player;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.Direction;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.CompositePositionValidator;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.PositionValidator;
import ca.sfu.cmpt276.spring2021.group8.project.game.result.GameOverResult;
import ca.sfu.cmpt276.spring2021.group8.project.game.result.GameResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.awt.*;


// Integration testing of interactions between:
    // EntityList
    // All Entities
    // GameResult classes
    // GameEffect classes
    // Movement classes
    // PositionValidators
public class GameLogicTest {

    //mock maze class to simulate the functionality of the Maze class
    class MockMaze implements PositionValidator {
        private int width=9;
        private int height=9;

        private Point exit;
        private Point entrance;

        private int[][] maze;

        public MockMaze(int[][] maze,Point entrance, Point exit)
        {
            this.maze=maze;
            this.height=maze.length;
            this.width=maze[0].length;

            this.entrance=entrance;
            this.exit=exit;
        }

        @Override
        public boolean isValidPosition(Point p) {
            //check if moving towards unlocked exit tile
            if (p.equals(exit) && maze[exit.y][exit.x] == 9) {
                return true;
            }

            // Check if next position is out of bounds
            if (p.x < 1) {
                return false;
            } else if (p.x >= width - 1) {
                return false;
            }

            if (p.y < 1) {
                return false;
            } else if (p.y >= height - 1) {
                return false;
            }

            // Check if next position is an empty space
            return maze[p.y][p.x] == 0;
        }

        public Point getEntrance() {
            return entrance;
        }

        public Point getExit() {
            return exit;
        }

        public void complete(){
            maze[exit.y][exit.x] = 9;
        }

    }

    // MockWorld class to simulate the functionality of the world and some of the game class
    class MockWorld
    {
        int num_enemies;
        int num_rewards;
        int num_punishments;
        int num_barriers;
        int num_bonus;

        int rewardCollected=0;
        int score =0;

        MockMaze maze;
        Player player;
        EntityList<Barrier> barriers = new EntityList<>();
        EntityList<Enemy> enemies = new EntityList<>();
        EntityList<Collectable> collectables = new EntityList<>();

        public MockWorld(MockMaze maze, int num_enemies, int num_rewards, int num_bonus, int num_punishments, int num_barriers)
        {
            this.maze=maze;
            this.player=new Player(maze.getEntrance());

            this.num_enemies=num_enemies;
            this.num_rewards=num_rewards;
            this.num_bonus=num_bonus;
            this.num_punishments=num_punishments;
            this.num_barriers=num_barriers;
        }

        public void generateBonus(Point[] positions)
        {
            for (int i=0;i<num_bonus;i++)
            {
                collectables.add(new BonusReward(positions[i]));
            }
        }

        public void generateRewards(Point[] positions)
        {
            for (int i=0;i<num_rewards;i++)
            {
                collectables.add(new Reward(positions[i]));
            }
        }

        public void generateEnemies(Point[] positions)
        {
            for (int i=0;i<num_enemies;i++)
            {
                enemies.add(new Enemy(this.player.generateTargetedMovementGenerator(), positions[i]));
            }
        }

        public void generateBarriers(Point[] positions)
        {
            for (int i=0;i<num_barriers;i++)
            {
                barriers.add(new Barrier(positions[i]));
            }
        }

        public void generatePunishments(Point[] positions)
        {
            for (int i=0;i<num_punishments;i++)
            {
                collectables.add(new Punishment(positions[i]));
            }
        }

        public void movePlayer(Direction direction) {
            player.move(new CompositePositionValidator(maze, barriers), direction);
        }

        public void moveEnemy()
        {
            for (Enemy enemy : enemies)
            {
                if (player.getHasMoved()) {
                    enemy.move(new CompositePositionValidator(maze, barriers, enemies));
                }
            }
        }

        public GameEffect getGameEffect() {
            Point pos = player.getPosition();

            for (Collectable collectable : collectables) {
                if (!collectable.getPosition().equals(pos)) {
                    continue;
                }

                if (collectable instanceof Reward) {
                    rewardCollected++;
                    if(rewardCollected == num_rewards){
                        maze.complete();
                    }
                }

                collectables.remove(collectable);
                return GameEffect.createScoreEffect(collectable.getPoints());
            }

            for (Enemy enemy : enemies) {
                if (enemy.getPosition().equals(pos)) {
                    return GameEffect.createLoseEffect();
                }
            }

            if (player.getPosition().equals(maze.getExit())) {

                return GameEffect.createWinEffect();
            }
            return null;
        }

        public GameResult updateGameResult()
        {
            GameEffect effect = this.getGameEffect();
            if (effect != null) {
                if (effect instanceof GameOverEffect) {
                    if (((GameOverEffect) effect).win) {
                        return GameResult.createWinResult(score, 0);
                    } else {
                        return GameResult.createLoseResult(score, 0);
                    }
                } else if (effect instanceof ScoreEffect) {
                    //update score
                    this.score += ((ScoreEffect) effect).score;

                    //check if score is negative --> ends game
                    if (this.score < 0) {
                        return GameResult.createLoseResult(score, 0);
                    }
                }
            }

            return null;
        }
    }

    //Testing whether or not the scores are correct, testing interactions between:
        // GameEffect classes
        // GameResult classes
        // Collectible classes
        // Player class
    @Nested
    class ScoreTest
    {
        /*
        Current maze setup:
                    {4,7,4},
                    {4,0,4},
                    {4,0,4},
                    {4,0,4},
                    {4,8,4}
         Where 4 are walls, 0 are empty spaces, 7 is entrance, and 8 is closed exit
         */
        int[][] layout=
                {
                        {4, 7, 4},
                        {4, 0, 4},
                        {4, 0, 4},
                        {4, 0, 4},
                        {4, 8, 4}
                };
        Point entrance = new Point(1,0);
        Point exit= new Point(1,4);

        MockMaze maze=new MockMaze(layout,entrance,exit);
        MockWorld world=new MockWorld(maze,0,1,1,1,0);

        @BeforeEach
        public void setup()
        {
            world.generateRewards(new Point[]{new Point(1,1)});
            world.generateBonus(new Point[]{new Point(1,2)});
            world.generatePunishments(new Point[]{new Point(1,3)});

        }

        @Test
        public void testScores()
        {
            assert (world.score==0);

            world.movePlayer(Direction.South);
            world.updateGameResult();
            assert (world.score==2);

            world.movePlayer(Direction.South);
            world.updateGameResult();
            assert (world.score==7);

            world.movePlayer(Direction.South);
            world.updateGameResult();
            assert (world.score==2);

        }
    }

    @Nested
    class WinningTest
    {
        int[][] layout=
                {
                        {4, 7, 4, 4},
                        {4, 0, 0, 4},
                        {4, 0, 0, 4},
                        {4, 4, 8, 4}
                };
        Point entrance = new Point(1,0);
        Point exit= new Point(2,3);

        MockMaze maze=new MockMaze(layout,entrance,exit);
        MockWorld world=new MockWorld(maze,0,2,0,0,0);

        @BeforeEach
        public void setup()
        {
            world.generateRewards(new Point[]{new Point(1,1),new Point(1,2)});
        }

        @Test
        public void testWinGame()
        {
            assert (world.updateGameResult()==null);

            world.movePlayer(Direction.South);
            assert (world.updateGameResult()==null);

            world.movePlayer(Direction.South);
            assert (world.updateGameResult()==null);

            world.movePlayer(Direction.East);
            assert (world.updateGameResult()==null);

            world.movePlayer(Direction.South);
            GameResult gameResult=world.updateGameResult();
            assert (gameResult!=null);
            assert (gameResult instanceof GameOverResult);
            assert (((GameOverResult) gameResult).win);
        }

        //tests that the player doesnt win when the door is still locked
        @Test
        public void testExitNotOpen()
        {
            assert (world.updateGameResult()==null);

            world.movePlayer(Direction.South);
            assert (world.updateGameResult()==null);

            world.movePlayer(Direction.East);
            assert (world.updateGameResult()==null);


            world.movePlayer(Direction.South);
            assert (world.updateGameResult()==null);

            world.movePlayer(Direction.South);
            assert (world.updateGameResult()==null);
        }
    }

    @Nested
    class LosingTest
    {
        int[][] layout=
                {
                        {4, 7, 4, 4},
                        {4, 0, 0, 4},
                        {4, 0, 0, 4},
                        {4, 4, 8, 4}
                };
        Point entrance = new Point(1,0);
        Point exit= new Point(2,3);

        MockMaze maze=new MockMaze(layout,entrance,exit);
        MockWorld world=new MockWorld(maze,1,0,0,1,0);

        @BeforeEach
        public void setup()
        {
            world.generateEnemies(new Point[]{new Point(1,2)});
            world.generatePunishments(new Point[]{new Point(2,1)});
        }

        @Test
        public void testLostByEnemy()
        {
            assert (world.updateGameResult()==null);

            world.movePlayer(Direction.South);
            assert (world.updateGameResult()==null);

            world.movePlayer(Direction.South);
            GameResult gameResult=world.updateGameResult();
            assert (gameResult!=null);
            assert (gameResult instanceof GameOverResult);
            assert (!((GameOverResult) gameResult).win);
        }

        @Test
        public void testLostByPunishment()
        {
            assert (world.updateGameResult()==null);

            world.movePlayer(Direction.South);
            assert (world.updateGameResult()==null);

            world.movePlayer(Direction.East);
            GameResult gameResult=world.updateGameResult();
            assert (gameResult!=null);
            assert (gameResult instanceof GameOverResult);
            assert (!((GameOverResult) gameResult).win);
        }
    }

    @Nested
    class EnemyMovementTest
    {
        int[][] layout=
                {
                        {4, 7, 4, 4, 4},
                        {4, 0, 0, 0, 4},
                        {4, 0, 0, 0, 4},
                        {4, 4, 8, 4, 4}
                };
        Point entrance = new Point(1,0);
        Point exit= new Point(2,3);

        MockMaze maze=new MockMaze(layout,entrance,exit);
        MockWorld world=new MockWorld(maze,1,0,0,0,0);

        @BeforeEach
        public void setup()
        {
            world.generateEnemies(new Point[]{new Point(3,2)});
        }

        //Tests that enemy doesnt move if the player has not moved
        @Test
        public void testEnemyNotMoveBeforePlayer()
        {
            assert (world.enemies.get(0).getPosition().equals(new Point(3,2)));
            world.moveEnemy();
            assert (world.enemies.get(0).getPosition().equals(new Point(3,2)));
        }

        //Tests that enemy moves after the player moves
        @RepeatedTest(10)
        public void testEnemyMoveAfterPlayer()
        {
            world.movePlayer(Direction.South);
            world.moveEnemy();
            assert (world.enemies.get(0).getPosition().equals(new Point(2,2))
                    ||world.enemies.get(0).getPosition().equals(new Point(3,1)));
        }

        //Tests that the enemy can move to the player's position in the most efficient path
        @Test
        public void testEnemyCanReachPlayer()
        {
            world.movePlayer(Direction.South);

            world.moveEnemy();
            world.moveEnemy();
            world.moveEnemy();

            assert (world.enemies.get(0).getPosition().equals(world.player.getPosition()));
        }

    }

    @Nested
    class PositionValidityTest
    {
        int[][] layout=
                {
                        {4, 7, 4, 4, 4},
                        {4, 0, 4, 0, 4},
                        {4, 0, 0, 0, 4},
                        {4, 4, 8, 4, 4}
                };
        Point entrance = new Point(1,0);
        Point exit= new Point(2,3);

        MockMaze maze=new MockMaze(layout,entrance,exit);
        MockWorld world=new MockWorld(maze,2,0,0,0,1);

        @BeforeEach
        public void setup()
        {
            world.generateEnemies(new Point[]{new Point(3,1),new Point(3,2)});
        }

        //Tests invalid movement interaction from player-barrier, player-maze, enemy-barrier, enemy-wall, and enemy-enemy
        @Test
        public void testInvalidMovement()
        {
            world.generateBarriers(new Point[]{new Point(2,2)});
            world.movePlayer(Direction.South);

            assert (world.enemies.get(0).getPosition().equals(new Point(3,1)));
            assert (world.enemies.get(1).getPosition().equals(new Point(3,2)));
            world.moveEnemy();

            //If position validity doesn't work, the enemies will walk past the barriers/other enemies/walls
            assert (world.enemies.get(0).getPosition().equals(new Point(3,1)));
            assert (world.enemies.get(1).getPosition().equals(new Point(3,2)));

            //If position validity doesn't work, the player will walk past the wall
            world.movePlayer(Direction.West);
            assert (world.player.getPosition().equals(new Point(1,1)));

            //If position validity doesn't work, the player will walk past the barrier
            world.movePlayer(Direction.East);
            assert (world.player.getPosition().equals(new Point(1,1)));
        }
    }
}
