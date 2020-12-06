package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Atom;
import model.game_entities.enums.AtomType;
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
        this.atomImage = ImageFactory.get(atom, 2 * radius, 2 * radius);
    }

    @Override
    public void draw(Graphics g) {
        if (atom.getType() == AtomType.ALPHA) {
            g.setColor(Color.BLACK);
        } else if (atom.getType() == AtomType.BETA) {
            g.setColor(Color.BLUE);
        } else if (atom.getType() == AtomType.SIGMA) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.PINK);
        }

        Coordinates drawingCoord = MathUtils.drawingCoordinates(atom.getCoordinates(), radius);
        g.drawImage(atomImage,
                (int) drawingCoord.getX(),
                (int) drawingCoord.getY(),
                null);
    }
}
