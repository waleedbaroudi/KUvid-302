package ui.movable_drawables;

import model.game_entities.*;
import model.game_entities.enums.EntityType;

public class DrawableFactory {
    public static Drawable get(AutonomousEntity autonomous, EntityType type) {
        switch (type) {
            case MOLECULE:
                return new MoleculeDrawer((Molecule) autonomous);
            case ATOM:
                return new AtomDrawer((Atom) autonomous);
            case BLOCKER:
                return new BlockerDrawer((Blocker) autonomous);
            case SHOOTER:
                return new ShooterDrawer((Shooter) autonomous);
            case POWERUP:
                return new PowerupDrawer((Powerup) autonomous);
            default:
                throw new IllegalArgumentException("Entity type is not correct");
        }
    }
}
