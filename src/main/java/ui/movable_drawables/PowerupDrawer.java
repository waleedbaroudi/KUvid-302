package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Powerup;
import model.game_entities.enums.AtomType;
import model.game_entities.enums.PowerupType;

import java.awt.*;

public class PowerupDrawer implements Drawable {

    private Powerup powerup;

    public PowerupDrawer(Powerup powerup) {
        this.powerup = powerup;
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

        //int r = (int) (Configuration.getInstance().getUnitL() * 0.1);

        int r = (int) (4000 * 0.1); //replace r with L
        int l = (int) (0.25 * r);
        int x = (int) (powerup.getCoordinate().getX() - 0.5 * l);
        int y = (int) (powerup.getCoordinate().getY() - 0.5 * l);
        int[] xPos = {(int) (x + 0.5 * l), x, x + l};
        int[] yPos = {y, (int) (y + 0.5 * l), (int) (y + 0.5 * l)};

        g.fillPolygon(xPos, yPos, 3);
    }
}
