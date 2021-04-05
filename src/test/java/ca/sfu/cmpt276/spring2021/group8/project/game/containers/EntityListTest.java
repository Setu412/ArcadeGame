package ca.sfu.cmpt276.spring2021.group8.project.game.containers;

import ca.sfu.cmpt276.spring2021.group8.project.game.entity.Barrier;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.Collectable;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.Punishment;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.Reward;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movable.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class EntityListTest {

    private EntityList<Barrier> barriers;
    EntityList<Collectable> collectables;

    @BeforeEach
    void setup(){
        barriers = new EntityList<>();
        collectables = new EntityList<>();

        collectables.add(new Reward(new Point(1,1)));
        collectables.add(new Punishment(new Point(9,10)));
        barriers.add(new Barrier(new Point(17,2)));
        barriers.add(new Barrier(new Point(7,13)));

    }
    @Test
    void isValidPosition() {
        Point checkPoint = new Point(1,1);

        //Since at Point (1,1) there is a Reward Entity occupied at this position, the function should return false
        assertFalse(collectables.isValidPosition(checkPoint));

        //Since at Point (9,10) there is a Punishment Entity occupied at this position, the function should return false
        checkPoint.setLocation(9,10);
        assertFalse(collectables.isValidPosition(checkPoint));

        //Since at Point (17,2) there is a Barrier Entity occupied at this position, the function should return false
        checkPoint.setLocation(17,2);
        assertFalse(barriers.isValidPosition(checkPoint));

        //Since at Point (7,13) there is a Barrier Entity occupied at this position, the function should return false
        checkPoint.setLocation(7,13);
        assertFalse(barriers.isValidPosition(checkPoint));


        //Since at Point (8,9) is an empty position as none of the entity occupy this position,
        //the function should return true
        checkPoint.setLocation(8,9);
        assertTrue(collectables.isValidPosition(checkPoint) || barriers.isValidPosition(checkPoint));


        //Since at Point (15,5) is an empty position as none of the entity occupy this position,
        //the function should return true
        checkPoint.setLocation(15,5);
        assertTrue(collectables.isValidPosition(checkPoint) || barriers.isValidPosition(checkPoint));
    }
}