package ca.sfu.cmpt276.spring2021.group8.project.game.entity;

import java.awt.*;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.game.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.Collectable;

public class Player extends Entity {

    private static Player playerInstance=null;
    private Player(Maze maze) {
        super(maze);
        this.position= maze.startPosition();
    }

    public static Player getPlayerInstance(Maze maze)
    {
        if (playerInstance==null)
        {
            playerInstance=new Player(maze);
        }
        return playerInstance;
    }

    protected void tryMove(Point position, int[] originalXY) {
        int isValidPosition = maze.isValidPosition(position);
        //isValidPosition == 0 mean its a wall and you cant move
        //isValidPosition == some other value, means player has moved into something
        if ( isValidPosition == 0) {
            return;
        }

        this.position = position;
        //Darren Question: Did you update the player position in the maze map? If so, where did you update it?
        maze.setCoordValue(originalXY[0], originalXY[1], 0);
        //Now, player has moved to new position and its position has changed
        //creating a function to reflect what would happen if player makes that move
        // could be enemy, reward, punishments
        afterMoving(isValidPosition);
    }

    void afterMoving(int movedIntoValue ){

        int BonusRewardScore = 5;
        int RewardScore = 2;
        int PunishmentScore = -5;

        switch(movedIntoValue){
            case -1: //game over moved in enemy
                //TODO stop the game --> loosing screen
                break;
            case -2: // moved in punishment
                score = score + PunishmentScore;
                if(score < 0)
                {
                    //TODO stop the game --> loosing screen
                }
                break;
            case 2: // moved into reward
                score = score + RewardScore;
                checkAllRewards++;
                if(checkAllRewards == 40)
                {
                    maze.completed();
                }

                break;
            case 3: // moved into bonusReward
                score = score + BonusRewardScore;
                break;
            case 9: //game is won
                //TODO won the game --> winning screen
                break;
        }
    }

    public void move(Direction direction) {
        Point position = getPosition();
        int[] currentXY = {position.x, position.y};
        switch (direction) {
            default:
                return;

            case West:
                position.x -= 1;
                break;

            case North:
                position.y -= 1;
                break;

            case East:
                position.x += 1;
                break;

            case South:
                position.y += 1;
                break;
        }

        tryMove(position, currentXY);
    }

    @Override
    public void render(Graphics g, WorldScreenAdapter adapter) {
        Rectangle offset = g.getClipBounds();
        Point playerScreenPosition = adapter.convert(getPosition());
        g.setColor(Color.BLUE);
        Draw.dot(g, offset.x + playerScreenPosition.x + adapter.gridHorizontalSpacing()/2, offset.y + playerScreenPosition.y + adapter.gridVerticalSpacing()/2, 16);


        // Image scaled = student.getScaledInstance(student.getHeight() / student.getWidth() * gridSpacing.x, student.getWidth() / student.getHeight() * gridSpacing.y, 0);
        // g.drawImage(scaled, xoffset + playerScreenPosition.x, yoffset + playerScreenPosition.y, null);
    }
}
