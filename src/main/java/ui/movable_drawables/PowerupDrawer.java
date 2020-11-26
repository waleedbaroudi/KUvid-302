package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Powerup;
import model.game_entities.enums.AtomType;
import model.game_entities.enums.PowerupType;

import java.awt.*;

public class PowerupDrawer implements Drawable {

    private Powerup powerup;

    public PowerupDrawer(Powerup powerup){
        this.powerup = powerup;
    }

    @Override
    public void draw(Graphics g) {

        int r = (int) (Configuration.getInstance().getUnitL() * 0.1);

        g.drawRect((int) powerup.getCoordinate().getX() - r,
                (int) powerup.getCoordinate().getY() - r,
                2 * r,
                4 * r);

        if (powerup.getType() == PowerupType._ALPHA_B) {
            g.setColor(Color.BLACK);
        } else if (powerup.getType() == PowerupType._BETA_B) {
            g.setColor(Color.BLUE);
        } else if (powerup.getType() == PowerupType._SIGMA_B) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.PINK);
        }

    }
}
