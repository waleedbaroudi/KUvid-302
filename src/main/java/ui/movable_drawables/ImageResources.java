package ui.movable_drawables;

import model.game_entities.AutonomousEntity;
import model.game_entities.Entity;
import model.game_entities.Molecule;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.SuperType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class is responsible for providing images of entities and icons
 */
public class ImageResources {

    /**
     * @param entity the entity that needs an image to draw itself
     * @return the corresponding image with the specified dimensions
     */
    public static Image get(Entity entity) {
        int width = (int) entity.getHitbox().getWidth();
        int height = (int) entity.getHitbox().getHeight();

        switch (entity.getSuperType()) {
            //Entity is a Blocker, atom, powerup, or molecule, return the corresponding image
            case ATOM:
            case BLOCKER:
            case POWERUP:
                AutonomousEntity a = (AutonomousEntity) entity;
                return getImage(a.getSuperType() + "/" + a.getEntityType() + ".png", width, height);

            case MOLECULE:
                Molecule b = (Molecule) entity;
                return getImage(b.getSuperType() + "/" + b.getEntityType() + b.getStructure() + ".png", width, height);

            //Entity is a Shooter, return shooter image
            case SHOOTER:
                return getImage("shooter.png", width, height);

            //A default black image will be returned in case of any error
            default:
                System.err.println("Error: ImageResources::get :" + entity.toString() + ", " + width + ", " + height);
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
     * @param type       of the icon to be returned
     * @param iconWidth  that will be used to scale the icon
     * @param iconHeight that will be used to scale the icon
     * @return the corresponding icon with the specified dimensions
     */
    public static Image getEntityIcon(SuperType superType, EntityType type, int iconWidth, int iconHeight, boolean background) {
        return getImage("statisticsIcons" + "/" + superType + "/" + type + background + ".png", iconWidth, iconHeight);
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
