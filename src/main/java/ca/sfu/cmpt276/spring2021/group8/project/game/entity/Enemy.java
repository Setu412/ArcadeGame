package ca.sfu.cmpt276.spring2021.group8.project.game.entity;
import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;

import ca.sfu.cmpt276.spring2021.group8.project.Draw;
import ca.sfu.cmpt276.spring2021.group8.project.game.Direction;
import ca.sfu.cmpt276.spring2021.group8.project.game.Maze;

import java.awt.*;
import java.util.Random;

public class Enemy extends Entity{
    /**
     * Player field, used to check the optimal direction for weighted random enemy movement
     */
    private Player player=Player.getPlayerInstance(maze);

    /**
     * value of the entity at current tile (0 if no entity under current tile, not 0 otherwise)
     */
    private int currentTile=0;

    protected Enemy(Maze maze) {
        super(maze);
    }


    /**
     * Generating the next move of the enemy, called every tick
     * Movement is determined by the current position of the player
     * <p>
     * Alternative: use DFS to look for the shortest path to player
     * Cons to DFS: have to re-adjust shortest path every time player moves
     */
    public void weightedEnemyMove() {
        //if player and enemy are in the same horizontal axis
        if (player.getPosition().getX() == this.getPosition().getX()) {
            //if player directly north of enemy enemy
            if (player.getPosition().getY() < this.getPosition().getY()) {
                weightedEnemyMove(Direction.North);
            }
            //if player directly south of enemy
            else if (player.getPosition().getY() > this.getPosition().getY()) {
                weightedEnemyMove(Direction.South);
            }
        }
        //if the player is east of enemy
        else if (player.getPosition().getX() > this.getPosition().getX()) {
            //if player is directly east of enemy
            if (player.getPosition().getY() == this.getPosition().getY()) {
                weightedEnemyMove(Direction.East);
            }
            //if player is northeast of enemy
            else if (player.getPosition().getY()<this.getPosition().getY()) {
                weightedEnemyMove(Direction.NorthEast);
            }
            //if player is southeast of enemy
            else{
                weightedEnemyMove(Direction.SouthEast);
            }
        }
        //if player is west of enemy
        else {
            //if player is directly west of enemy
            if (player.getPosition().getX()==this.getPosition().getY()){
                weightedEnemyMove(Direction.West);
            }
            //if player is northwest of enemy
            else if (player.getPosition().getY()<this.getPosition().getY()){
                weightedEnemyMove(Direction.NorthWest);
            }
            else {
                weightedEnemyMove(Direction.SouthWest);
            }
        }
    }

    /**
     * Moves the enemy according to relative location to the player.
     * Movement will be decided according to all possible moves,
     * where directions directly moving the enemy to the location of the player is prioritized
     *
     * @param relPosition The position of the player relative to the enemy, all 8 values of direction possible
     */
    public void weightedEnemyMove(Direction relPosition)
    {
        Random random= new Random();
        int coinflip=random.nextInt(2); //generates a random number from [0,2), used for randomizing weighted movement
        switch (relPosition)
        {
            default:
                return;
            case East: //if the player is directly east of the enemy, primary movement target becomes east
                if (moveEnemy(Direction.East)) //check if primary movement target (east) is available
                    return;
                if (coinflip==0) //because north and south are equally far from the player, order of the movement is randomized
                {
                    if (moveEnemy(Direction.North))
                        return;
                    if (moveEnemy(Direction.South))
                        return;
                }
                else
                {
                    if (moveEnemy(Direction.South))
                        return;
                    if (moveEnemy(Direction.North))
                        return;
                }
                if (moveEnemy(Direction.West)) //because west is the direct opposite of primary movement target, it's prioritized last
                    return;
                break;
            case West:
                if (moveEnemy(Direction.West))
                    return;
                if (coinflip==0)
                {
                    if (moveEnemy(Direction.North))
                        return;
                    if (moveEnemy(Direction.South))
                        return;
                }
                else
                {
                    if (moveEnemy(Direction.South))
                        return;
                    if (moveEnemy(Direction.North))
                        return;
                }
                if (moveEnemy(Direction.East))
                    return;
                break;
            case North:
                if (moveEnemy(Direction.North))
                    return;
                if (coinflip==0)
                {
                    if (moveEnemy(Direction.East))
                        return;
                    if (moveEnemy(Direction.West))
                        return;
                }
                else
                {
                    if (moveEnemy(Direction.West))
                        return;
                    if (moveEnemy(Direction.East))
                        return;
                }
                if (moveEnemy(Direction.South))
                    return;
                break;
            case South:
                if (moveEnemy(Direction.South))
                    return;
                if (coinflip==0)
                {
                    if (moveEnemy(Direction.East))
                        return;
                    if (moveEnemy(Direction.West))
                        return;
                }
                else
                {
                    if (moveEnemy(Direction.West))
                        return;
                    if (moveEnemy(Direction.East))
                        return;
                }
                if (moveEnemy(Direction.North))
                    return;
                break;
            //if the player is both north and east of the enemy,
            //then there are 2 possible primary movement target (north and east)
            case NorthEast:
                if (coinflip==0) //randomize the testing order of the primary movement target
                {
                    if (moveEnemy(Direction.North))
                        return;
                    if (moveEnemy(Direction.East))
                        return;
                }
                else
                {
                    if (moveEnemy(Direction.East))
                        return;
                    if (moveEnemy(Direction.North))
                        return;
                }
                if (coinflip==0)
                {
                    if (moveEnemy(Direction.South))
                        return;
                    if (moveEnemy(Direction.West))
                        return;
                }
                else
                {
                    if (moveEnemy(Direction.West))
                        return;
                    if (moveEnemy(Direction.South))
                        return;
                }
                break;
            case NorthWest:
                if (coinflip==0)
                {
                    if (moveEnemy(Direction.North))
                        return;
                    if (moveEnemy(Direction.West))
                        return;
                }
                else
                {
                    if (moveEnemy(Direction.West))
                        return;
                    if (moveEnemy(Direction.North))
                        return;
                }
                if (coinflip==0)
                {
                    if (moveEnemy(Direction.South))
                        return;
                    if (moveEnemy(Direction.East))
                        return;
                }
                else
                {
                    if (moveEnemy(Direction.East))
                        return;
                    if (moveEnemy(Direction.South))
                        return;
                }
                break;
            case SouthEast:
                if (coinflip==0)
                {
                    if (moveEnemy(Direction.South))
                        return;
                    if (moveEnemy(Direction.East))
                        return;
                }
                else
                {
                    if (moveEnemy(Direction.East))
                        return;
                    if (moveEnemy(Direction.South))
                        return;
                }
                if (coinflip==0)
                {
                    if (moveEnemy(Direction.North))
                        return;
                    if (moveEnemy(Direction.West))
                        return;
                }
                else
                {
                    if (moveEnemy(Direction.West))
                        return;
                    if (moveEnemy(Direction.North))
                        return;
                }
                break;
            case SouthWest:
                if (coinflip==0)
                {
                    if (moveEnemy(Direction.South))
                        return;
                    if (moveEnemy(Direction.West))
                        return;
                }
                else
                {
                    if (moveEnemy(Direction.West))
                        return;
                    if (moveEnemy(Direction.South))
                        return;
                }
                if (coinflip==0)
                {
                    if (moveEnemy(Direction.North))
                        return;
                    if (moveEnemy(Direction.East))
                        return;
                }
                else
                {
                    if (moveEnemy(Direction.East))
                        return;
                    if (moveEnemy(Direction.North))
                        return;
                }
                break;
        }
    }

    /**
     * Moves the enemy according to the direction given by argument
     * Returns false if movement fails and true if succeeded
     *
     * @param direction The direction of the move, only 4 movement directions available (West, East, North, South)
     * @return see above
     */
    public boolean moveEnemy (Direction direction)
    {
        Point positionWest=convertMovementToPosition(Direction.West);
        Point positionNorth=convertMovementToPosition(Direction.North);
        Point positionSouth=convertMovementToPosition(Direction.South);
        Point positionEast=convertMovementToPosition(Direction.East);
        switch (direction)
        {
            default:
                return false;
            case West:
                if (maze.isValidPosition(positionWest)!=0&&maze.isValidPosition(positionWest)!=9)
                {
                    maze.setCoordValue(this.position.x,this.position.y,currentTile); //update the tile that enemy was in before moving
                    this.position.x-=1; //update the enemy position internally
                    currentTile=maze.getCoordValue(this.position.x,this.position.y); //update currentTile
                    maze.setCoordValue(this.position.x,this.position.y,-1); //update the enemy position in the map
                    return true;
                }
                return false;
            case East:
                if (maze.isValidPosition(positionEast)!=0&&maze.isValidPosition(positionEast)!=9)
                {
                    maze.setCoordValue(this.position.x,this.position.y,currentTile);
                    this.position.x+=1;
                    maze.setCoordValue(this.position.x,this.position.y,-1);
                    return true;
                }
                return false;
            case North:
                if (maze.isValidPosition(positionNorth)!=0&&maze.isValidPosition(positionNorth)!=9)
                {
                    maze.setCoordValue(this.position.x,this.position.y,currentTile);
                    this.position.y-=1;
                    maze.setCoordValue(this.position.x,this.position.y,-1);
                    return true;
                }
                return false;
            case South:
                if (maze.isValidPosition(positionSouth)!=0&&maze.isValidPosition(positionSouth)!=9)
                {
                    maze.setCoordValue(this.position.x,this.position.y,currentTile);
                    this.position.y+=1;
                    maze.setCoordValue(this.position.x,this.position.y,-1);
                    return true;
                }
                return false;
        }
    }

    /**
     * Converts direction of the movement to the 
     * @param direction
     * @return
     */
    public Point convertMovementToPosition(Direction direction)
    {
        switch (direction) {
            default:
                return null;

            case West:
                return new Point(this.position.x-1,this.position.y);
            case North:
                return new Point(this.position.x,this.position.y-1);

            case East:
                return new Point(this.position.x+1,this.position.y);

            case South:
                return new Point(this.position.x,this.position.y+1);
        }
    }




    @Override
    public void render(Graphics g, WorldScreenAdapter adapter) {
        Rectangle offset = g.getClipBounds();

        for(int i=0 ; i<maze.getHEIGHT() ; i++) {
            for (int j = 0; j < maze.getWIDTH(); j++) {
                if (maze.getCoordValue(j, i) == -1) {
                    Point punishmentScreenPosition = adapter.convert(i, j);
                    g.setColor(Color.BLACK);
                    Draw.dot(g, offset.x + punishmentScreenPosition.x + adapter.gridHorizontalSpacing()/2, offset.y + punishmentScreenPosition.y + adapter.gridVerticalSpacing()/2, 16);
                }
            }
        }
    }
}


/**
 * TODO in Enemy class
 *
 * 1. generate its random movement --> Another class for it according to UML diagram
 *      this randomly generated movement is such that enemy approaches player --> kinda shortest path
 *
 * 2. update Maze coordinate value according to its movement
 *
 * 3. render image of enemies
 *
 *
 */
