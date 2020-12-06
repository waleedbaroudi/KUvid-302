package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Powerup;
import model.game_entities.enums.PowerupType;
import model.game_running.GameConstants;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;

public class PowerupDrawer implements Drawable {

    private final Powerup powerup;
    private final int radius;
    private final Image powerupImage;

    public PowerupDrawer(Powerup powerup) {
        this.powerup = powerup;
        this.radius = (int) (Configuration.getInstance().getUnitL() * GameConstants.POWERUP_RADIUS);
        this.powerupImage = ImageFactory.get(powerup, 2 * radius, 2 * radius);

    }

    @Override
    public void draw(Graphics g) {

        if (powerup.getType() == PowerupType._ALPHA_B) {
            g.setColor(Color.BLACK);
        } else if (powerup.getType() == PowerupType._BETA_B) {
            g.setColor(Color.BLUE);
        } else if (powerup.getType() == PowerupType._SIGMA_B) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.PINK);
        }

        Coordinates drawingCoord = MathUtils.drawingCoordinates(powerup.getCoordinates(), radius);
        g.drawImage(powerupImage, drawingCoord.getPoint().x, drawingCoord.getPoint().y, null);
    }
}
