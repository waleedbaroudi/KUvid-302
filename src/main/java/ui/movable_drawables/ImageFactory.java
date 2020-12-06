package ui.movable_drawables;

import model.game_entities.*;
import model.game_entities.enums.EntityType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFactory {
    public static Image get(Entity entity, int width, int height) {
        switch (entity.getSuperType()) {

            //Entity is a Molecule, return an image according to its type
            case MOLECULE:
                switch (((Molecule) entity).getType()) {
                    case ALPHA_:
                        return getImage("moleculeA.png", width, height);
                    case BETA_:
                        return getImage("moleculeB.png", width, height);
                    case GAMMA_:
                        return getImage("moleculeG.png", width, height);
                    case SIGMA_:
                        return getImage("moleculeS.png", width, height);
                    default:
                        throw new IllegalArgumentException("Molecule type is not correct");
                }
                //Entity is an Atom, return an image according to its type
            case ATOM:
                switch (((Atom) entity).getType()) {
                    case ALPHA:
                        return getImage("atomA.png", width, height);
                    case BETA:
                        return getImage("atomB.png", width, height);
                    case GAMMA:
                        return getImage("atomG.png", width, height);
                    case SIGMA:
                        return getImage("atomS.png", width, height);
                    default:
                        throw new IllegalArgumentException("Atom type is not correct");
                }

                //Entity is a Blocker, return an image according to its type
            case BLOCKER:
                switch (((Blocker) entity).getType()) {
                    case ALPHA_B:
                        return getImage("blockerA.png", width, height);
                    case BETA_B:
                        return getImage("blockerB.png", width, height);
                    case GAMMA_B:
                        return getImage("blockerG.png", width, height);
                    case SIGMA_B:
                        return getImage("blockerS.png", width, height);
                    default:
                        throw new IllegalArgumentException("Blocker type is not correct");
                }

                //Entity is a powerup, return an image according to its type
            case POWERUP:
                switch (((Powerup) entity).getType()) {
                    case _ALPHA_B:
                        return getImage("powerupA.png", width, height);
                    case _BETA_B:
                        return getImage("powerupB.png", width, height);
                    case _GAMMA_B:
                        return getImage("powerupG.png", width, height);
                    case _SIGMA_B:
                        return getImage("powerupS.png", width, height);
                    default:
                        throw new IllegalArgumentException("Powerup type is not correct");
                }

                //Entity is a Shooter, return shooter image
            case SHOOTER:
                return getImage("shooter.png", width, height);
            default:
                throw new IllegalArgumentException("Entity type is not correct");
        }
    }

    /**
     *
     * @param image the name of the image to be returned
     * @param width the width of the image after scaling
     * @param height the height of the image after scaling
     * @return an image to draw in the space
     */
    private static Image getImage(String image, int width, int height) {
        BufferedImage img = null;

        try {
            img = ImageIO.read(new File("./images/" + image));
        } catch (IOException e) {
            System.err.println("error retrieving image: " + e.getMessage());
        }

        if (img == null){
            System.out.println(new File("./images/" + image).getPath());
            return null;}
        return img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
}
