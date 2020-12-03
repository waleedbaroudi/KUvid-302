package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Atom;
import model.game_entities.enums.AtomType;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;

public class AtomDrawer implements Drawable {

    private final Atom atom;

    public AtomDrawer(Atom atom) {
        this.atom = atom;
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

        double l = Configuration.getInstance().getUnitL();
        int r = (int) (l * 0.05);

        Coordinates drawingCoord = MathUtils.drawingCoordinates(atom.getCoordinates(), r);

        g.fillOval((int) drawingCoord.getX(),
                (int) drawingCoord.getY(),
                2 * r,
                2 * r);
    }
}
