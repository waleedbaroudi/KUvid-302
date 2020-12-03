package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Powerup;
import model.game_entities.enums.AtomType;
import model.game_entities.enums.PowerupType;
import model.game_running.GameConstants;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;

public class PowerupDrawer implements Drawable {

    private final Powerup powerup;
    private final int radius;

    public PowerupDrawer(Powerup powerup) {
        this.powerup = powerup;
        this.radius = (int) (Configuration.getInstance().getUnitL() * GameConstants.POWERUP_SIZE);
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

        Coordinates drawingCoord = MathUtils.drawingCoordinates(powerup.getCoordinates(), radius / 2);
        int x = (int) (drawingCoord.getX());
        int y = (int) (drawingCoord.getY());
        int[] xPos = {(x + radius / 2), x, x + radius};
        int[] yPos = {y, (y + radius / 2), (y + radius / 2)};

        g.fillPolygon(xPos, yPos, 3);
    }
}
