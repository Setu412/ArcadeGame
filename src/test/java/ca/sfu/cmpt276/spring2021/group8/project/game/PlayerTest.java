package ca.sfu.cmpt276.spring2021.group8.project.game;

import ca.sfu.cmpt276.spring2021.group8.project.game.containers.EntityList;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Barrier;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable.Player;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.Direction;
import ca.sfu.cmpt276.spring2021.group8.project.game.positioning.CompositePositionValidator;
import java.awt.Point;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    Player player = new Player(new Point(3,3));
    Maze maze = new Maze();
    EntityList<Barrier> barriers = new EntityList<>();

    @Test
    public void testGetHasMoved() {
        // Adding barriers
        for (int i =1; i < 10; i++) {
            barriers.add(new Barrier(new Point(i,1)));
        }
        // Should assert false as player has not moved
        assertFalse(player.getHasMoved());

        assertFalse(player.getHasMoved());
        Direction w = Direction.West;

        // Should assert true as player has moved;
        player.move(new CompositePositionValidator(maze, barriers), w);
        assertTrue(player.getHasMoved());
    }
}
