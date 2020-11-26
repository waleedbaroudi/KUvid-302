package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Atom;
import model.game_entities.enums.AtomType;
import model.game_entities.enums.MoleculeType;

import java.awt.*;

public class AtomDrawer implements Drawable {

    private Atom atom;

    public AtomDrawer(Atom atom) {
        this.atom = atom;
    }

    @Override
    public void draw(Graphics g) {
        int l = 400;
        int r = (int) (l * 0.05);

        g.fillOval((int) atom.getCoordinate().getX() - r,
                (int) atom.getCoordinate().getY() - r,
                2 * r,
                2 * r);

        if (atom.getType() == AtomType.ALPHA) {
            g.setColor(Color.BLACK);
        } else if (atom.getType() == AtomType.BETA) {
            g.setColor(Color.BLUE);
        } else if (atom.getType() == AtomType.SIGMA) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.PINK);
        }
    }
}
