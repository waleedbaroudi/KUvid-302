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
        Vector cornerVector = new Vector(centerCoordinates, new Coordinates(4,2));
        RectangularHitbox rectHitbox = new RectangularHitbox(4, 2);

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

    @Test
    void isHitboxInside() {

        Coordinates hitboxCoordinates = new Coordinates(0,0);
        Coordinates targetCoordinates = new Coordinates(1,1);

        Vector hitboxVector = new Vector(hitboxCoordinates, new Coordinates(1,1));
        Vector targetVector = new Vector(targetCoordinates, new Coordinates(1,1));
//        RectangularHitbox hitbox = new RectangularHitbox(2, 2);
//        RectangularHitbox target = new RectangularHitbox(targetVector);
//
//        assertTrue(hitbox.isInside(hitboxCoordinates, target.getBoundaryCoordinates()));
//
//        hitboxCoordinates = new Coordinates(0,0);
//        targetCoordinates = new Coordinates(2,2);
//
//        hitboxVector = new Vector(hitboxCoordinates, new Coordinates(1,1));
//        targetVector = new Vector(targetCoordinates, new Coordinates(1,1));
//
//        hitbox = new RectangularHitbox(hitboxVector);
//        target = new RectangularHitbox(targetVector);
//
//        assertFalse(hitbox.isInside(hitboxCoordinates, target.getBoundaryCoordinates()));

    }
}