package model.game_building;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {

    @Test
    void getInstance() {
        assertNotNull(Configuration.getInstance());
    }

    @Test
    void setConfig() {
        Configuration config = Configuration.getInstance();
        assertEquals(config.getDifficulty(), -1);
        assertEquals(config.getNumAlphaAtoms(), -1);

        ArrayList<Integer> atoms = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
        }};

        ConfigBundle bundle = new ConfigBundle(atoms,3,3,3,1.2,true, true, true ,true,2);
        Configuration.getInstance().setConfig(bundle);

        assertEquals(Configuration.getInstance().getDifficulty(), 2);
        assertEquals(Configuration.getInstance().getNumAlphaAtoms(), 1);
        assertNotEquals(Configuration.getInstance().getNumBetaAtoms(), 3);
    }
}