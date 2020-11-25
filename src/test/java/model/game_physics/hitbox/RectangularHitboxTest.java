package model.game_physics.hitbox;

import org.junit.jupiter.api.Test;
import utils.Coordinates;

import static org.junit.jupiter.api.Assertions.*;

class RectangularHitboxTest {

    @Test
    void isInside() {
        Coordinates ownerCoordinates = new Coordinates(2,1);
        Coordinates centerCoordinates = new Coordinates(2,1);
        RectangularHitbox rectHitbox = new RectangularHitbox(centerCoordinates, 4,2);

        Coordinates point = new Coordinates(4,0);

        assertTrue(rectHitbox.isInside(ownerCoordinates, point));

        rectHitbox.rotate(90);
        assertFalse(rectHitbox.isInside(ownerCoordinates, point));

        point = new Coordinates(1.9,2);
        assertFalse(rectHitbox.isInside(ownerCoordinates, point));

        point = new Coordinates(3,1);
        assertFalse(rectHitbox.isInside(ownerCoordinates, point));

        rectHitbox.rotate(-90);
        assertTrue(rectHitbox.isInside(ownerCoordinates, point));

        point = new Coordinates(1.9,2);
        assertTrue(rectHitbox.isInside(ownerCoordinates, point));
    }
}