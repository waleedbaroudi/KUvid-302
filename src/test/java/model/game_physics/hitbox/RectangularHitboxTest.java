package model.game_physics.hitbox;

import org.junit.jupiter.api.Test;
import utils.Coordinates;
import utils.Vector;

import static org.junit.jupiter.api.Assertions.*;

class RectangularHitboxTest {

    @Test
    void isInside() {
        Coordinates ownerCoordinates = new Coordinates(2,1);
        Coordinates centerCoordinates = new Coordinates(2,1);
        RectangularHitbox rectHitbox = new RectangularHitbox(4, 2);

        assertTrue(rectHitbox.isInside(ownerCoordinates, new Coordinates(4,0)));
        assertTrue(rectHitbox.isInside(ownerCoordinates, new Coordinates(1,1)));
        assertFalse(rectHitbox.isInside(ownerCoordinates, new Coordinates(3,3)));
        assertFalse(rectHitbox.isInside(ownerCoordinates, new Coordinates(1,3)));

        rectHitbox.rotate(90);
//        assertFalse(rectHitbox.isInside(ownerCoordinates, new Coordinates(1,3)));
        
//        point = new Coordinates(1.9,2);
//        assertFalse(rectHitbox.isInside(ownerCoordinates, point));
//
//        point = new Coordinates(3,1);
//        assertFalse(rectHitbox.isInside(ownerCoordinates, point));
//
//        rectHitbox.rotate(-90);
//        assertTrue(rectHitbox.isInside(ownerCoordinates, point));
//
//        point = new Coordinates(1.9,2);
//        assertTrue(rectHitbox.isInside(ownerCoordinates, point));
    }

    @Test
    void isHitboxInside() {

        Coordinates hitboxCoordinates = new Coordinates(0,0);
        Coordinates targetCoordinates = new Coordinates(1,1);

    }
}