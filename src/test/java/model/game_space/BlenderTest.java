package model.game_space;

import model.game_running.ProjectileContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlenderTest {

    @Test
    void blend() {

        ProjectileContainer container = new ProjectileContainer(null, 5, 5, 5, 5);
        Blender blender = new Blender(container);

        assertArrayEquals(container.getAtomMap(), new int[]{5, 5, 5, 5});

        blender.blendAtoms(1, 2, 1); // Blending 2 ALPHA into 1 BETA
        assertArrayEquals(container.getAtomMap(), new int[]{3, 6, 5, 5});

        blender.blendAtoms(1, 2, 2); // Cannot blend anymore: no enough ALPHA atoms.
        assertArrayEquals(container.getAtomMap(), new int[]{3, 6, 5, 5});

        blender.blendAtoms(4, 1, 2); // Breaking 2 SIGMA into ALPHA
        assertArrayEquals(container.getAtomMap(), new int[]{11, 6, 5, 3});

        blender.blendAtoms(2, 4, 2); // Blending 6 BETA into 2 SIGMA
        assertArrayEquals(container.getAtomMap(), new int[]{11, 0, 5, 5});


        container = new ProjectileContainer(null, 3, 3, 3, 3);
        blender = new Blender(container);

        assertArrayEquals(container.getAtomMap(), new int[]{3, 3, 3, 3});

        blender.blendAtoms(2, 4, 1); // Blending 3 BETA into 1 SIGMA
        assertArrayEquals(container.getAtomMap(), new int[]{3, 0, 3, 4});

        blender.blendAtoms(3, 2, 3); // Blending 3 GAMMA into 6 BETA
        assertArrayEquals(container.getAtomMap(), new int[]{3, 6, 0, 4});

        container = new ProjectileContainer(null, 0, 0, 0, 0);
        blender = new Blender(container);

        assertArrayEquals(container.getAtomMap(), new int[]{0, 0, 0, 0});

        blender.blendAtoms(1, 4, 1); // Blending 4 ALPHA into 1 SIGMA
        assertArrayEquals(container.getAtomMap(), new int[]{0, 0, 0, 0});

        blender.blendAtoms(2, 4, 1); // Blending 3 BETA into 1 SIGMA
        assertArrayEquals(container.getAtomMap(), new int[]{0, 0, 0, 0});

        blender.blendAtoms(3, 4, 1); // Blending 2 GAMMA into 1 SIGMA
        assertArrayEquals(container.getAtomMap(), new int[]{0, 0, 0, 0});

        blender.blendAtoms(4, 1, 1); // Blending SIGMA into 4 ALPHA
        assertArrayEquals(container.getAtomMap(), new int[]{0, 0, 0, 0});

    }
}