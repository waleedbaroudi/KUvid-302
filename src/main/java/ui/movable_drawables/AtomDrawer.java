package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Atom;
import model.game_entities.enums.EntityType;
import model.game_building.GameConstants;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;

public class AtomDrawer extends ProjectileDrawer implements Drawable {

    /*
    private final Atom atom;
    private final int radius = (int) (Configuration.getInstance().getUnitL() * GameConstants.ATOM_RADIUS);
    private final Image atomImage = ImageResources.get(atom.getType(), atom.getSuperType(), 2 * radius, 2 * radius);
    */

    public AtomDrawer(Atom atom, Image atomImage, int radius) {
        super(atom, atomImage, radius);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
