package ui.movable_drawables;

import model.game_entities.Projectile;
import model.game_entities.Shooter;
import utils.Coordinates;
import utils.MathUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;

/**
 * This class is responsible for drawing the Shooter given the Shooter entity in the constructor
 */
public class ShooterDrawer implements Drawable {

    private final Shooter shooter;
    private final Image shooterImage;
    private Image shooterImageGif;

    public ShooterDrawer(Shooter shooter) {
        this.shooter = shooter;
        this.shooterImage = ImageResources.get(shooter);
        this.shooterImageGif = ImageResources.getGif();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();

        g2d.rotate(Math.toRadians(shooter.getAngle()),
                (int) shooter.getCoordinates().getX(),
                (int) shooter.getCoordinates().getY());

        Projectile projectile = shooter.getCurrentProjectile();
        Coordinates drawingCoordinates = MathUtils.drawingCoordinates(shooter.getCoordinates(),
                shooter.getHitbox().getWidth(),
                shooter.getHitbox().getHeight());

        g2d.drawImage(shooterImageGif, drawingCoordinates.getPoint().x, drawingCoordinates.getPoint().y, null);
        if (projectile != null) {
            Coordinates projectileCoord = MathUtils.drawingCoordinates(
                    shooter.getCoordinates(),
                    0, projectile.getHitbox().getHeight() + shooter.getHitbox().getHeight());

            projectile.setCoordinates(projectileCoord);
            DrawableFactory.get(projectile).draw(g);
        }
        g2d.setTransform(old);
    }

    @Override
    public void drawHitbox(Graphics g) {
        Coordinates drawingCoordinates = MathUtils.drawingCoordinates(shooter.getCoordinates(),
                shooter.getHitbox().getWidth(),
                shooter.getHitbox().getHeight());

        g.drawRect(
                drawingCoordinates.getPoint().x,
                drawingCoordinates.getPoint().y,
                (int) shooter.getHitbox().getWidth(),
                (int) shooter.getHitbox().getHeight());
    }

}
