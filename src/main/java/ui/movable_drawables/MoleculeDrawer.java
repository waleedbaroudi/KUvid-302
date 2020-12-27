package ui.movable_drawables;

import model.game_entities.Molecule;
import model.game_entities.enums.MoleculeStructure;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * This class is responsible for drawing a Molecule given a Molecule entity in the constructor
 */
public class MoleculeDrawer implements Drawable {

    private final Molecule molecule;
    private final Image moleculeImage;

    public MoleculeDrawer(Molecule molecule) {
        this.molecule = molecule;
        this.moleculeImage = ImageResources.get(
                this.molecule.getType(),
                this.molecule.getSuperType(),
                molecule.getStructure(),
                (int) molecule.getHitbox().getWidth(),
                (int) molecule.getHitbox().getHeight());
    }

    @Override
    public void draw(Graphics g) {
        // rotate the molecule
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        g2d.rotate(Math.toRadians(molecule.getRotationDegree()), molecule.getCoordinates().getPoint().x, molecule.getCoordinates().getPoint().y);
        Coordinates drawingCoord = MathUtils.drawingCoordinates(molecule.getCoordinates(),
                molecule.getHitbox().getWidth(), molecule.getHitbox().getHeight());
        g2d.drawImage(moleculeImage, drawingCoord.getPoint().x, drawingCoord.getPoint().y, null);
        g2d.setTransform(old);

    }

    @Override
    public void drawHitbox(Graphics g) {
        Coordinates drawingCoord = MathUtils.drawingCoordinates(molecule.getCoordinates(),
                molecule.getHitbox().getWidth(),
                molecule.getHitbox().getHeight());

        if (molecule.getStructure() == MoleculeStructure.CIRCULAR)
            g.drawOval(
                    drawingCoord.getPoint().x,
                    drawingCoord.getPoint().y,
                    (int) molecule.getHitbox().getWidth(),
                    (int) molecule.getHitbox().getHeight());
        else
            g.drawRect(
                    drawingCoord.getPoint().x,
                    drawingCoord.getPoint().y,
                    (int) molecule.getHitbox().getWidth(),
                    (int) molecule.getHitbox().getHeight());
    }
}
