package ca.sfu.cmpt276.spring2021.group8.project.game.entity;

import ca.sfu.cmpt276.spring2021.group8.project.game.WorldScreenAdapter;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.MovementGenerator;
import ca.sfu.cmpt276.spring2021.group8.project.game.entity.movement.TargetedMovementGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {
    //a mock entity class, to enable instantiating an entity
    class mockEntity extends Entity
    {
        protected mockEntity(Point startPosition) {
            super(startPosition);
        }

        @Override
        public void render(Graphics g, WorldScreenAdapter s) {
            return;
        }
    }
    Entity entity;

    @BeforeEach
    public void setup()
    {
        entity=new mockEntity(new Point(0,0));
    }

    @Test
    public void testGetPosition()
    {
        assert (entity.getPosition().equals(new Point(0,0)));
    }

    @Test
    public void testSetPosition()
    {
        entity.setPosition(new Point(1,1));
        assert (entity.getPosition().equals(new Point(1,1)));
    }

    @Test
    public void testGenerateTargetedMovementGenerator()
    {
        TargetedMovementGenerator test= (TargetedMovementGenerator) entity.generateTargetedMovementGenerator();
        TargetedMovementGenerator control= (TargetedMovementGenerator) entity.generateTargetedMovementGenerator();
        assert (test.getTarget().getPosition().equals(control.getTarget().getPosition()));
    }
}