package ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement;

import ca.sfu.cmpt276.spring2021.group8.project.game.Direction;

import java.awt.*;

public interface MovementGenerator {
    Direction next(Point currentPosition);
}
