package model.game_entities.factories;

import model.game_building.Configuration;
import model.game_entities.Entity;
import model.game_entities.Molecule;

import model.game_entities.enums.MoleculeStructure;
import model.game_entities.enums.EntityType;

import model.game_physics.hitbox.HitboxFactory;
import model.game_physics.path_patterns.PathPatternFactory;
import utils.Coordinates;

public class MoleculeFactory {
    private static MoleculeFactory moleculeFactory = new MoleculeFactory();

    private MoleculeFactory() {
    }

    public static MoleculeFactory getInstance() {
        if (moleculeFactory == null) {
            moleculeFactory = new MoleculeFactory();
        }
        return moleculeFactory;
    }

    public Molecule getMolecule(EntityType type) {
        Coordinates defaultCoordinates = new Coordinates(0, 0);
        return new Molecule(defaultCoordinates, HitboxFactory.getInstance().getMoleculeHitbox(),
                PathPatternFactory.getInstance().getMoleculePathPattern(type), type, getStructure(type));
    }

    private MoleculeStructure getStructure(EntityType type) {
        switch (type) {
            case ALPHA:
                if (Configuration.getInstance().isLinearAlpha())
                    return Configuration.getInstance().isSpinningAlpha() ? MoleculeStructure.LINEAR_SPINNING : MoleculeStructure.LINEAR_NON_SPINNING;
            case BETA:
                if (Configuration.getInstance().isLinearBeta())
                    return Configuration.getInstance().isSpinningBeta() ? MoleculeStructure.LINEAR_SPINNING : MoleculeStructure.LINEAR_NON_SPINNING;
            default:
                return MoleculeStructure.CIRCULAR;
        }
    }
}


