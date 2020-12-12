package ui.movable_drawables;

import model.game_entities.*;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.SuperType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageResources {
    public static Image get(EntityType type, SuperType superType, int width, int height) {
        switch (superType) {

            //Entity is a Molecule, return an image according to its type
            case MOLECULE:
                switch (type) {
                    case ALPHA:
                        return getImage("molecules/alpha-1.png", width, height);
                    case BETA:
                        return getImage("molecules/beta-1.png", width, height);
                    case GAMMA:
                        return getImage("molecules/gamma-.png", width, height);
                    case SIGMA:
                        return getImage("molecules/sigma-.png", width, height);
                    default:
                        throw new IllegalArgumentException("Molecule type is not correct");
                }
                //Entity is an Atom, return an image according to its type
            case ATOM:
                switch (type) {
                    case ALPHA:
                        return getImage("atoms/alpha.png", width, height);
                    case BETA:
                        return getImage("atoms/beta.png", width, height);
                    case GAMMA:
                        return getImage("atoms/gama.png", width, height);
                    case SIGMA:
                        return getImage("atoms/sigma.png", width, height);
                    default:
                        throw new IllegalArgumentException("Atom type is not correct");
                }

                //Entity is a Blocker, return an image according to its type
            case BLOCKER:
                switch (type) {
                    case ALPHA:
                        return getImage("blockers/alpha-b.png", width, height);
                    case BETA:
                        return getImage("blockers/beta-b.png", width, height);
                    case GAMMA:
                        return getImage("blockers/gamma-b.png", width, height);
                    case SIGMA:
                        return getImage("blockers/sigma-b.png", width, height);
                    default:
                        throw new IllegalArgumentException("Blocker type is not correct");
                }

                //Entity is a powerup, return an image according to its type
            case POWERUP:
                switch (type) {
                    case ALPHA:
                        return getImage("powerups/+alpha-b.png", width, height);
                    case BETA:
                        return getImage("powerups/+beta-b.png", width, height);
                    case GAMMA:
                        return getImage("powerups/+gamma-b.png", width, height);
                    case SIGMA:
                        return getImage("powerups/+sigma-b.png", width, height);
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
     * @param image  the name of the image to be returned
     * @param width  the width of the image after scaling
     * @param height the height of the image after scaling
     * @return an image to draw in the space
     */
    private static Image getImage(String image, int width, int height) {
        BufferedImage img = null;
        System.out.println("directory: " + System.getProperty("user.dir"));
        try {
            img = ImageIO.read(new File(System.getProperty("user.dir") + "/assets/" + image));
        } catch (IOException e) {
            System.err.println("error retrieving image: " + e.getMessage());
        }

        if (img == null) {
            return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        }
        return img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public static Image getIcon(int icon, int iconWidth, int iconHeight) {
        switch (icon) {
            case 1:
                return getImage("image1", iconWidth, iconHeight);
            case 2:
                return getImage("image2", iconWidth, iconHeight);
            case 3:
                return getImage("image3", iconWidth, iconHeight);
            default:
                return getImage("default", iconWidth, iconHeight);
        }
    }
}
