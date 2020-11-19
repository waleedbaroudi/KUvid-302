package model.game_physics.path_patterns;

import org.junit.jupiter.api.Test;
import utils.Coordinates;
import utils.Velocity;

import static org.junit.jupiter.api.Assertions.*;

class StraightPatternTest {

    @Test
    void move() {
        // create a StraightPattern
        StraightPattern pattern = new StraightPattern(new Coordinates(1, 1), new Velocity(2, 3));
        assertEquals(pattern.move(), new Coordinates(3, 4));
        assertEquals(pattern.move(), new Coordinates(5, 7));
        for(int i = 0; i < 9;i++){
            pattern.move();
        }
        assertEquals(pattern.move(), new Coordinates(25, 37));

    }
}