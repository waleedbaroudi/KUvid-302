package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Molecule;
import model.game_entities.enums.EntityType;
import model.game_running.GameConstants;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;

public class MoleculeDrawer implements Drawable {

    private final Molecule molecule;
    private final int radius;
    private final Image moleculeImage;


    public MoleculeDrawer(Molecule molecule) {
        this.molecule = molecule;
        this.radius = (int) (Configuration.getInstance().getUnitL() * GameConstants.MOLECULE_RADIUS);
        this.moleculeImage = ImageResources.get(molecule, 2 * radius, 2 * radius);
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

        Coordinates drawingCoord = MathUtils.drawingCoordinates(molecule.getCoordinates(), radius);
        g.drawImage(moleculeImage, drawingCoord.getPoint().x, drawingCoord.getPoint().y, null);
    }
}
