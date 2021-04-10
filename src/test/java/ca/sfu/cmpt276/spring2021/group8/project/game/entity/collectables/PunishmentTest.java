package ca.sfu.cmpt276.spring2021.group8.project.game.entity.collectables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PunishmentTest {

    private Punishment punishment;

    @BeforeEach
    void setup(){
        punishment = new Punishment(new Point(1,12));
    }
    @Test
    void getPoints() {
        assertEquals(-5,punishment.getPoints());
    }
}