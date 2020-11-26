package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Molecule;
import model.game_entities.enums.MoleculeType;
import model.game_entities.enums.PowerupType;
import sun.security.provider.MD2;

import java.awt.*;

public class MoleculeDrawer implements Drawable {

    private Molecule molecule;

    public MoleculeDrawer(Molecule molecule) {
        this.molecule = molecule;
    }

    @Override
    public void draw(Graphics g) {

        int l = (int) (0.25 * Configuration.getInstance().getUnitL());
        int x = (int) (molecule.getCoordinate().getX() - 0.5 * l);
        int y = (int) (molecule.getCoordinate().getX() - 0.5 * l);
        int[] xpos = {(int) (x + 0.5 * l),x, x + l, (int) (x + 0.5 * l)};
        int[] ypos = {y, (int) (y + 0.5 * l),(int) (y + 0.5 * l), y};
        g.drawPolygon(xpos, ypos, 4);


        if (molecule.getType() == MoleculeType.ALPHA_) {
            g.setColor(Color.BLACK);
        } else if (molecule.getType() == MoleculeType.BETA_) {
            g.setColor(Color.BLUE);
        } else if (molecule.getType() == MoleculeType.GAMMA_) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.PINK);
        }


    }
}
