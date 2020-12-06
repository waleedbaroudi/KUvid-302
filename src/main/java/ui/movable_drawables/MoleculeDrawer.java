package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Molecule;
import model.game_entities.enums.MoleculeType;
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
        this.moleculeImage = ImageFactory.get(molecule, 2 * radius, 2 * radius);
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

        Coordinates drawingCoord = MathUtils.drawingCoordinates(molecule.getCoordinates(), radius);
        g.drawImage(moleculeImage, (int) drawingCoord.getX(), (int) drawingCoord.getY(), null);
    }
}
