package ca.sfu.cmpt276.spring2021.group8.project.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import ca.sfu.cmpt276.spring2021.group8.project.game.effect.GameEffect;
import ca.sfu.cmpt276.spring2021.group8.project.game.effect.ScoreEffect;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.BonusReward;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.Direction;

import ca.sfu.cmpt276.spring2021.group8.project.game.containers.EntityList;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Barrier;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable.Enemy;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable.Player;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    World world;

    @BeforeEach
    void setup(){
        world = new World();
    }

    @Test
    void testUpdateEnemy(){

        try {
            //Accessing private enemy field in World class using Reflection

            Field PlayerField = World.class.getDeclaredField("player");
            PlayerField.setAccessible(true);
            Player playerCopy = (Player) PlayerField.get(world);
            Point player_Position = playerCopy.getPosition();
            //System.out.println("Player initial position  x = " + player_Position.x + " y = " + player_Position.y);

            Field EnemyField = World.class.getDeclaredField("enemies");
            EnemyField.setAccessible(true);
            EntityList<Enemy> EnemyCopy = (EntityList<Enemy>) EnemyField.get(world);

            Point EnemyAtIndex0_Pos = EnemyCopy.get(0).getPosition();
            Point EnemyAtIndex1_Pos = EnemyCopy.get(1).getPosition();
            Point EnemyAtIndex2_Pos = EnemyCopy.get(2).getPosition();

            //Moving the player once to start the game, to make hasMoved = true
            if (player_Position.x == 0){
                world.movePlayer(Direction.East);
            }
            else if (player_Position.y == 0){
                world.movePlayer(Direction.South);
            }
            else if (player_Position.x == 20){
                world.movePlayer(Direction.West);
            }
            else if (player_Position.y == 13){
                world.movePlayer(Direction.North);
            }

            //Enemies make a movement in each 0.5 sec, thus making deltaTime larger than 500 so as to ensure enemy moves
            long testDeltaTime = 600;
            world.updateEnemy(testDeltaTime);

            //Since enemy has moved, position should not match, thus assertion must be false
            assertFalse(EnemyAtIndex0_Pos.equals(EnemyCopy.get(0).getPosition()));
            assertFalse(EnemyAtIndex1_Pos.equals(EnemyCopy.get(1).getPosition()));
            assertFalse(EnemyAtIndex2_Pos.equals(EnemyCopy.get(2).getPosition()));

            //Testing deltaTime be less than 0.5 sec, so that enemy must not move

            //Copying positions
            EnemyAtIndex0_Pos = EnemyCopy.get(0).getPosition();
            EnemyAtIndex1_Pos = EnemyCopy.get(1).getPosition();
            EnemyAtIndex2_Pos = EnemyCopy.get(2).getPosition();

            testDeltaTime = 200;
            world.updateEnemy(testDeltaTime);

            //Since enemy has not moved, position should match, thus assertion must be True
            assertTrue(EnemyAtIndex0_Pos.equals(EnemyCopy.get(0).getPosition()));
            assertTrue(EnemyAtIndex1_Pos.equals(EnemyCopy.get(1).getPosition()));
            assertTrue(EnemyAtIndex2_Pos.equals(EnemyCopy.get(2).getPosition()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateBarriers(){

        try {
            //Accessing Barrier field in World class using Reflection

            Field barrierField = World.class.getDeclaredField("barriers");
            barrierField.setAccessible(true);
            EntityList<Barrier> Barrier_Copy = (EntityList<Barrier>) barrierField.get(world);

            ArrayList<Point> barrierPos = new ArrayList<Point>();
            for(int i=0; i<10;i++){
                barrierPos.add(Barrier_Copy.get(i).getPosition());
            }

            //Barrier change position after every 13 sec, thus passing deltatime > 13000 to ensure position is updated
            long testDeltaTime = 14000;
            world.updateBarriers(testDeltaTime);

            ArrayList<Point> barrier_NewPos = new ArrayList<Point>();
            for(int i=0; i<10;i++) {
                barrier_NewPos.add(Barrier_Copy.get(i).getPosition());
            }

            //Since Barrier position has updated, the new position shouldnot match, thus assertion must be False
            for(int i=0; i<10;i++) {
                assertEquals(false, barrierPos.get(i).equals(barrier_NewPos.get(i)));
            }

            //Checking if deltaTime is less than 13 sec, thus Barrier shouldnot update

            //assigning barrier_NewPos to barrierPos
            for(int i=0; i<10;i++) {
                barrierPos.get(i).setLocation(barrier_NewPos.get(i));
            }

            testDeltaTime = 10000;
            world.updateBarriers(testDeltaTime);

            for(int i=0; i<10;i++) {
                barrier_NewPos.add(Barrier_Copy.get(i).getPosition());
            }

            //Since Barrier position has not updated, the new position should match, thus assertion must be True
            for(int i=0; i<10;i++) {
                assertEquals(true, barrierPos.get(i).equals(barrier_NewPos.get(i)));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetGameEffect(){

        try {
            //Accessing Player field in World class using Reflection
            Field PlayerField = World.class.getDeclaredField("player");
            PlayerField.setAccessible(true);
            Player playerCopy = (Player) PlayerField.get(world);
            Point player_Position = playerCopy.getPosition();
            //System.out.println("Player initial position  x = " + player_Position.x + " y = " + player_Position.y);

                //Trying to change the position of the player
            if (player_Position.x == 0){
                world.movePlayer(Direction.East);
            }
            else if (player_Position.y == 0){
                world.movePlayer(Direction.South);
            }
            else if (player_Position.x == 20){
                world.movePlayer(Direction.West);
            }
            else if (player_Position.y == 13){
                world.movePlayer(Direction.North);
            }

            player_Position = playerCopy.getPosition();
            //System.out.println("Player position after movement x = " + player_Position.x + " y = " + player_Position.y);

            // //Accessing BonusReward field in World class using Reflection
            Field BonusRewardField = World.class.getDeclaredField("bonusReward");
            BonusRewardField.setAccessible(true);
            BonusReward BR_Copy = (BonusReward) BonusRewardField.get(world);

            BR_Copy.setPosition(player_Position);
            //System.out.println("Player and BR position equal x = " + BR_Position.x + " y = " + BR_Position.y);

                //set bonusReward visibility true
            BR_Copy.isVisible = true;

            GameEffect effect = world.getGameEffect();
            int score = 0;
            score = score + ((ScoreEffect) effect).score;

            //Asserting the increment in score to be equal to BonusRewardPoints
            assertEquals(BR_Copy.getPoints(), score);

            /*
            //Setting random position for BonusReward
            BR_Copy.setPosition(new Point(15,7));
            effect = world.getGameEffect();
            score = 0;
            score = score + ((ScoreEffect) effect).score;
            assertNotEquals(BR_Copy.getPoints(),score);
            */
        }
        catch (Exception e) {
            e.printStackTrace();
        }
   }

   @Test
    void TestMovePlayer(){
       try {
           //Accessing Player field in World class using Reflection
           Field PlayerField = World.class.getDeclaredField("player");
           PlayerField.setAccessible(true);
           Player playerCopy = (Player) PlayerField.get(world);

           Point ptr = playerCopy.getPosition();

           //Moving Player based on the initial position of the player and asserting with new position
           if (ptr.x == 0){
               world.movePlayer(Direction.East);
               assertEquals(true, (new Point(ptr.x + 1,ptr.y)).equals(playerCopy.getPosition()));
           }
           else if (ptr.y == 0){
               world.movePlayer(Direction.South);
               assertEquals(true, (new Point(ptr.x,ptr.y+1)).equals(playerCopy.getPosition()));
           }
           else if (ptr.x == 20){
               world.movePlayer(Direction.West);
               assertEquals(true, (new Point(ptr.x-1,ptr.y)).equals(playerCopy.getPosition()));
           }
           else if (ptr.y == 13){
               world.movePlayer(Direction.North);
               assertEquals(true, (new Point(ptr.x,ptr.y - 1)).equals(playerCopy.getPosition()));
           }
       }
       catch (Exception e) {
           e.printStackTrace();
       }
   }

   @Test
    public void testRender()
   {
       BufferedImage img = new BufferedImage(1280, 720, BufferedImage.TYPE_BYTE_BINARY);
       Graphics g = img.getGraphics();
       g.setClip(0,0,1280,720);

       assertDoesNotThrow( ()->{
           world.render(g,new Point(1280, 720));
       });
   }
}