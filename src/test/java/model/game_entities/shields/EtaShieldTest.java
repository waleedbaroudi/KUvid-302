package model.game_entities.shields;

import model.game_building.GameConstants;
import model.game_entities.Atom;
import model.game_entities.enums.EntityType;
import model.game_entities.factories.AtomFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EtaShieldTest {

    @Test
    void getEfficiency() {
        Atom atom1 = AtomFactory.getInstance().getAtom(EntityType.ALPHA);

        System.out.println("Neutrons: " + atom1.getNumberOfNeutrons());
        System.out.println("Efficiency: " + atom1.getEfficiency());

        atom1 = new EtaShield(atom1);

        System.out.println("Neutrons: " + atom1.getNumberOfNeutrons());
        System.out.println("Efficiency: " + atom1.getEfficiency());

        atom1 = new EtaShield(atom1);

        System.out.println("Neutrons: " + atom1.getNumberOfNeutrons());
        System.out.println("Efficiency: " + atom1.getEfficiency());
    }
}