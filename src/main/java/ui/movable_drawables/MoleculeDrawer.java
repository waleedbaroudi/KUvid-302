package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Molecule;
import model.game_entities.enums.MoleculeType;

import java.awt.*;

public class MoleculeDrawer implements Drawable {

    private Molecule molecule;

    public MoleculeDrawer(Molecule molecule) {
        this.molecule = molecule;
    }

    @Override
    public void draw(Graphics g) {

        if (molecule.getType() == MoleculeType.ALPHA_) {
            g.setColor(Color.BLACK);
        } else if (molecule.getType() == MoleculeType.BETA_) {
            g.setColor(Color.BLUE);
        } else if (molecule.getType() == MoleculeType.GAMMA_) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.PINK);
        }

        int r = (int) (4000 * 0.05); //replace r with L

        int l = (int) (0.25 * r);
        int x = (int) (molecule.getCoordinates().getX() - 0.5 * l);
        int y = (int) (molecule.getCoordinates().getY() - 0.5 * l);
        int[] xPos = {(int) (x + 0.5 * l), x, (int) (x + 0.5 * l), x + l};
        int[] yPos = {y, (int) (y + 0.5 * l), y + l, (int) (y + 0.5 * l)};

        g.fillPolygon(xPos, yPos, 4);

    }
}
