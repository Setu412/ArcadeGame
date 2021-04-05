package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardTest {

    private Reward reward;

    @BeforeEach
    void setup(){
        reward = new Reward(new Point(16,6));
    }
    @Test
    void getPoints() {
        assertEquals(2,reward.getPoints());
    }
}
