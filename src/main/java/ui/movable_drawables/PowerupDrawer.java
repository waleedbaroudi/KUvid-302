package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Powerup;
import model.game_entities.enums.EntityType;
import model.game_building.GameConstants;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;

public class PowerupDrawer extends ProjectileDrawer implements Drawable {

   // private final Powerup powerup;
    //private final int radius = (int) (Configuration.getInstance().getUnitL() * GameConstants.POWERUP_RADIUS);
    // private final Image powerupImage = ImageResources.get(powerup.getType(), powerup.getSuperType(), 2 * radius, 2 * radius);

    public PowerupDrawer(Powerup powerup, Image powerupImage, int radius) {
        super(powerup, powerupImage, radius);

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
