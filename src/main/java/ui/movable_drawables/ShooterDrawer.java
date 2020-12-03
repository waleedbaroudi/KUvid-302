package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Shooter;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;

public class ShooterDrawer implements Drawable {

    private Shooter shooter;

    public ShooterDrawer(Shooter shooter) {
        this.shooter = shooter;
    }

    @Override
    public void draw(Graphics g) {

        int r = (int) (Configuration.getInstance().getUnitL() * 0.5);
        g.setColor(Color.MAGENTA);
        int h = (int) (r * 1.2);
        int w = (int) (r * 0.8);

        Coordinates drawingCoord = MathUtils.drawingCoordinates(shooter.getCoordinates(), (int) (0.5 * w), (int) (0.5 * h));

        Rectangle shooterRect = new Rectangle((int) drawingCoord.getX(),
                (int) drawingCoord.getY(), w, h);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.MAGENTA);
        g2d.rotate(Math.toRadians(shooter.getAngle()), (int) shooter.getCoordinates().getX(), (int) shooter.getCoordinates().getY());
        g2d.draw(shooterRect);
        g2d.fill(shooterRect);
    }
}
