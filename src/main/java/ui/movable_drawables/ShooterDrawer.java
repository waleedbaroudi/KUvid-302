package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Shooter;

import java.awt.*;

public class ShooterDrawer implements Drawable {

    private Shooter shooter;

    public ShooterDrawer(Shooter shooter) {
        this.shooter = shooter;
    }

    @Override
    public void draw(Graphics g) {
        int h = (int) (Configuration.getInstance().getUnitL());
        int w = (int) (Configuration.getInstance().getUnitL() * 0.5);

        g.drawRoundRect((int) (shooter.getCoordinate().getX() - 0.5 * w),
                (int) (shooter.getCoordinate().getY() - 0.5 * h),
                w, h,
                10, 10);
    }
}
