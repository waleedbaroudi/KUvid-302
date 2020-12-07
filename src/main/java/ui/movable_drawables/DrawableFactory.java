package ui.movable_drawables;

import model.game_entities.*;

public class DrawableFactory {
    public static Drawable get(Entity entity) {
            if (entity instanceof  Molecule)
                return new MoleculeDrawer((Molecule) entity);
            else if (entity instanceof  Atom)
                return new AtomDrawer((Atom) entity);
            else if (entity instanceof  Blocker)
                return new BlockerDrawer((Blocker) entity);
            else if (entity instanceof  Shooter)
                return new ShooterDrawer((Shooter) entity);
            else if (entity instanceof  Powerup)
                return new PowerupDrawer((Powerup) entity);
            else
                throw new IllegalArgumentException("Entity type is not correct");
    }
}
