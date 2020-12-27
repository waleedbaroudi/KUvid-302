package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_building.GameConstants;
import model.game_entities.Molecule;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * This class is responsible for drawing a Molecule given a Molecule entity in the constructor
 */
public class MoleculeDrawer implements Drawable {

    private final Molecule molecule;
    private final int radius;
    private final Image moleculeImage;

    public MoleculeDrawer(Molecule molecule) {
        this.molecule = molecule;
        this.radius = (int) (Configuration.getInstance().getUnitL() * GameConstants.MOLECULE_RADIUS);
        this.moleculeImage = ImageResources.get(this.molecule.getType(), this.molecule.getSuperType(), 2 * radius, 2 * radius);
    }

    @Override
    public void draw(Graphics g) {
        System.out.println(molecule.getCoordinates());
        // rotate the molecule
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        g2d.rotate(Math.toRadians(molecule.getRotationDegree()), molecule.getCoordinates().getPoint().getX(), molecule.getCoordinates().getPoint().getY());
        Coordinates drawingCoord = MathUtils.drawingCoordinates(molecule.getCoordinates(), radius);
        g2d.drawImage(moleculeImage, drawingCoord.getPoint().x, drawingCoord.getPoint().y, null);
        g2d.setTransform(old);
    }
}
