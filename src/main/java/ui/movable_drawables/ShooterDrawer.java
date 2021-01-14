package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Projectile;
import model.game_entities.Shooter;
import model.game_running.GameCommandListener;
import services.utils.Coordinates;
import services.utils.MathUtils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is responsible for drawing the Shooter given the Shooter entity in the constructor
 */
public class ShooterDrawer implements Drawable {

    private final Shooter shooter;
    private final Image shooterImage;
    private final Image shooterImageGif;
    private final Image shooterBase;
    private final Image shootingAnim;
    private Image currentImg;
    private boolean shot;
    private final Configuration config;

    private final java.util.Timer timer = new Timer();

    public ShooterDrawer(Shooter shooter) {
        this.shooter = shooter;
        this.config = Configuration.getInstance();
        this.shooterImage = ImageResources.get(shooter);
        this.shooterBase = ImageResources.get("shooter_base", (int) (shooter.getHitbox().getHeight() * 1.5), (int) shooter.getHitbox().getHeight());
        this.shooterImageGif = ImageResources.getGif("shooter", (int) shooter.getHitbox().getWidth(), (int) shooter.getHitbox().getHeight());
        this.shootingAnim = ImageResources.getGif("shootinganim", (int) shooter.getHitbox().getWidth(), (int) shooter.getHitbox().getHeight());
        this.currentImg = shooterImageGif;
        this.shot = true;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();

        if (!GameCommandListener.canShoot && shot)
            shot();

        Projectile projectile = shooter.getCurrentProjectile();
        Coordinates drawingCoordinates = MathUtils.drawingCoordinates(
                shooter.getCoordinates(),
                shooter.getHitbox().getWidth(),
                shooter.getHitbox().getHeight());

        if (config.isDiscoTheme())
            g2d.drawImage(shooterBase,
                    (int) (shooter.getCoordinates().getX() - 0.5 * shooterBase.getWidth(null)),
                    (int) (config.getGameHeight() - config.getUnitL() * 1.25),
                    null);

        g2d.rotate(Math.toRadians(
                shooter.getAngle()),
                (shooter.getCoordinates().getPoint().x),
                (int) (shooter.getCoordinates().getPoint().y + shooter.getHitbox().getHeight() * 0.25));

        g2d.drawImage(
                currentImg,
                drawingCoordinates.getPoint().x,
                drawingCoordinates.getPoint().y,
                null);

        if (projectile != null) {
            Coordinates projectileCoord = MathUtils.drawingCoordinates(
                    shooter.getCoordinates(),
                    0, projectile.getHitbox().getHeight() + shooter.getHitbox().getHeight());

            projectile.setCoordinates(projectileCoord);
            DrawableFactory.get(projectile).draw(g2d);
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

    public void shot() {
        if (!config.isDiscoTheme())
            return;
        shot = false;
        currentImg = shootingAnim;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                currentImg = shooterImageGif;
                shot = true;
            }
        }, 250);
    }

}
