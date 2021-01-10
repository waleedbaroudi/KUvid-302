package model.game_entities;

import model.game_running.ProjectileContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShooterTest {

    @Test
    void switchAtom() {

        ProjectileContainer container = new ProjectileContainer(null, 10, 10, 10, 10, 0, 0, 0, 0);
        Shooter shooter = new Shooter(container);

        assertArrayEquals(container.getAtomMap(), new int[]{10, 10, 10, 10});


    }
}