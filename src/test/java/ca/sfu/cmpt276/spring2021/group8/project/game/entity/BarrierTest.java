package ca.sfu.cmpt276.spring2021.group8.project.game.entity;

import ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables.BonusReward;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BarrierTest {

    private Barrier barrier;

    @BeforeEach
    void setup(){
        barrier = new Barrier(new Point(6,15));
    }

    @Test
    void TestUpdatePosition() {
        barrier.updatePosition(new Point(7,14));
        assertEquals(new Point(7,14),barrier.getPosition());
    }
}