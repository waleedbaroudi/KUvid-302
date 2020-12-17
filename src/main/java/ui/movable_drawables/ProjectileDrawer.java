package ui.movable_drawables;

import model.game_entities.Powerup;
import model.game_entities.Projectile;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;

public abstract class ProjectileDrawer implements Drawable {

    private  Projectile projectile;
    private Image projectileImage;
    private int radius;

    public ProjectileDrawer(Projectile projectile, Image projectileImage, int radius ){
        this.projectile = projectile;
        this.projectileImage = projectileImage;
        this.radius = radius;

    }


    @Override
    public void draw(Graphics g) {

        Coordinates drawingCoord = MathUtils.drawingCoordinates(projectile.getCoordinates(), radius);
        g.drawImage(projectileImage, drawingCoord.getPoint().x, drawingCoord.getPoint().y, null);

    }
}
