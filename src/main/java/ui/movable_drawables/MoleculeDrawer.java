package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Molecule;
import model.game_entities.enums.EntityType;
import model.game_running.GameConstants;

import java.awt.*;

public class MoleculeDrawer implements Drawable {

    private final Molecule molecule;
    private final int radius;

    public MoleculeDrawer(Molecule molecule) {
        this.molecule = molecule;
        this.radius = (int) (Configuration.getInstance().getUnitL() * GameConstants.MOLECULE_SIZE);
    }

    @Override
    public void draw(Graphics g) {

        if (molecule.getType() == EntityType.ALPHA) {
            g.setColor(Color.BLACK);
        } else if (molecule.getType() == EntityType.BETA) {
            g.setColor(Color.BLUE);
        } else if (molecule.getType() == EntityType.GAMMA) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.PINK);
        }

        int l = (radius);
        int x = (int) (molecule.getCoordinates().getX() - 0.5 * l);
        int y = (int) (molecule.getCoordinates().getY() - 0.5 * l);
        int[] xPos = {(int) (x + 0.5 * l), x, (int) (x + 0.5 * l), x + l};
        int[] yPos = {y, (int) (y + 0.5 * l), y + l, (int) (y + 0.5 * l)};

        g.fillPolygon(xPos, yPos, 4);

    }
}
