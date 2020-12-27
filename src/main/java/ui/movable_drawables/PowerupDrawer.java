package ui.movable_drawables;

import model.game_entities.Powerup;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;


/**
 * This class is responsible for drawing a Powerup given a Powerup entity in the constructor
 */
public class PowerupDrawer implements Drawable {

    private final Powerup powerup;
    private final Image powerupImage;

    public PowerupDrawer(Powerup powerup) {
        this.powerup = powerup;
        this.powerupImage = ImageResources.get(powerup.getType(), powerup.getSuperType(), null,
                (int) powerup.getHitbox().getWidth(),
                (int) powerup.getHitbox().getHeight());
    }

    @Override
    public void draw(Graphics g) {
        Coordinates drawingCoordinates = MathUtils.drawingCoordinates(powerup.getCoordinates(),
                powerup.getHitbox().getWidth(),
               powerup.getHitbox().getHeight());
        g.drawImage(powerupImage, drawingCoordinates.getPoint().x, drawingCoordinates.getPoint().y, null);
    }

    @Override
    public void drawHitbox(Graphics g) {
        Coordinates drawingCoordinates = MathUtils.drawingCoordinates(powerup.getCoordinates(),
                powerup.getHitbox().getWidth(),
                powerup.getHitbox().getHeight());

        g.drawOval(
                drawingCoordinates.getPoint().x,
                drawingCoordinates.getPoint().y,
                (int) powerup.getHitbox().getWidth(),
                (int) powerup.getHitbox().getHeight());
    }

}
