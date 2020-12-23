package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_building.GameConstants;
import model.game_entities.Atom;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;

/**
 * This class is responsible for drawing an Atom given the Atom entity in the constructor
 */
public class AtomDrawer implements Drawable{

    private final Atom atom;
    private final int radius;
    private final Image atomImage;

    public AtomDrawer(Atom atom) {
        this.atom = atom;
        this.radius = (int) (Configuration.getInstance().getUnitL() * GameConstants.ATOM_RADIUS);
        this.atomImage = ImageResources.get(this.atom.getType(), this.atom.getSuperType(), 2 * radius, 2 * radius);
    }

    @Override
    public void draw(Graphics g) {
        Coordinates drawingCoord = MathUtils.drawingCoordinates(atom.getCoordinates(), radius);
        g.drawImage(atomImage, drawingCoord.getPoint().x, drawingCoord.getPoint().y, null);
    }
}
