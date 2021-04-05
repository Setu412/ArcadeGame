package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BonusRewardTest {

    private BonusReward bonusReward;

    @BeforeEach
    void setup(){
        bonusReward = new BonusReward(new Point(10,11));
    }

    @Test
    void getPoints() {
        assertEquals(5,bonusReward.getPoints());
    }

    @Test
    void setPosition() {
        bonusReward.setPosition(new Point(9,8));
        assertEquals(new Point(9,8), bonusReward.getPosition());
    }
}