package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Atom;
import model.game_entities.enums.EntityType;
import model.game_running.GameConstants;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;

public class AtomDrawer implements Drawable {

    private final Atom atom;
    private final int radius;
    private final Image atomImage;

    public AtomDrawer(Atom atom) {
        this.atom = atom;
        this.radius = (int) (Configuration.getInstance().getUnitL() * GameConstants.ATOM_RADIUS);
        this.atomImage = ImageResources.get(atom, 2 * radius, 2 * radius);
    }

    @Override
    public void draw(Graphics g) {

        Coordinates drawingCoord = MathUtils.drawingCoordinates(atom.getCoordinates(), radius);
        g.drawImage(atomImage, drawingCoord.getPoint().x, drawingCoord.getPoint().y, null);
    }
}
