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

//        Configuration.getInstance().getUnitL()
        int r = (int) (2000 * 0.05);
        g.setColor(Color.MAGENTA);
        int h = (int) (r);
        int w = (int) (r * 0.7);
        g.fillRoundRect((int) (shooter.getCoordinates().getX() - 0.5 * w),
                (int) (shooter.getCoordinates().getY() - 0.5 * h),
                w, h,
                10, 10);
    }
}
