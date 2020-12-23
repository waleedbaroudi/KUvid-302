package ui.movable_drawables;

import model.game_entities.enums.EntityType;
import model.game_entities.enums.SuperType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageResources {
    /**
     * @param type      the subtype of the entity
     * @param superType the main type of the entity
     * @param width     that will be used to scale the image
     * @param height    that will be used to scale the image
     * @return the corresponding image with the specified dimensions
     */
    public static Image get(EntityType type, SuperType superType, int width, int height) {
        switch (superType) {

            //Entity is a Blocker, atom, powerup, or molecule, return the corresponding image
            case ATOM:
            case BLOCKER:
            case POWERUP:
            case MOLECULE:
                return getImage(superType + "/" + type + ".png", width, height);

            //Entity is a Shooter, return shooter image
            case SHOOTER:
                return getImage("shooter.png", width, height);

            //A default black image will be returned in case of any error
            default:
                System.err.println("Error: ImageResources::get :" + type + ", " + superType + ", " + width + ", " + height);
                return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        }
    }

    /**
     * @param icon       the icon to be returned
     * @param iconWidth  that will be used to scale the icon
     * @param iconHeight that will be used to scale the icon
     * @return the corresponding icon with the specified dimensions
     */
    public static Image getIcon(String icon, int iconWidth, int iconHeight) {
        return getImage(icon + ".png", iconWidth, iconHeight);
    }

    /**
     * @param image  the name of the image to be returned
     * @param width  the width of the image after scaling
     * @param height the height of the image after scaling
     * @return an image to draw in the space
     */
    private static Image getImage(String image, int width, int height) {
        BufferedImage img;
        try {
            img = ImageIO.read(new File(System.getProperty("user.dir") + "/assets/" + image));
        } catch (IOException e) {
            System.err.println("error retrieving image: " + e.getMessage() + " - image: " + image);
            return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        }
        return img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
}
