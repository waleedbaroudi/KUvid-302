package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_building.GameConstants;
import model.game_entities.Projectile;
import model.game_entities.Shooter;
import model.game_entities.enums.SuperType;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;

public class ShooterDrawer implements Drawable {

    private final Shooter shooter;
    private Projectile projectile;
    private final int height;
    private final int width;
    private final Image shooterImage;
    private final double unitL;

    public ShooterDrawer(Shooter shooter) {
        this.shooter = shooter;
        this.unitL = Configuration.getInstance().getUnitL();
        this.height = (int) (unitL * GameConstants.SHOOTER_HEIGHT);
        this.width = (int) (unitL * GameConstants.SHOOTER_WIDTH);
        this.shooterImage = ImageResources.get(null, shooter.getSuperType(), width, height);

    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(shooter.getAngle()), (int) shooter.getCoordinates().getX(), (int) shooter.getCoordinates().getY());

        Projectile projectile = shooter.getCurrentProjectile();
        Coordinates drawingCoord = MathUtils.drawingCoordinates(shooter.getCoordinates(), width / 2, height / 2);
        g2d.drawImage(shooterImage, drawingCoord.getPoint().x, drawingCoord.getPoint().y, null);

        if (projectile != null) {
            int r = (int) (unitL * (projectile.getSuperType() == SuperType.ATOM ? GameConstants.ATOM_RADIUS : GameConstants.POWERUP_RADIUS));
            Coordinates projectileCoord = MathUtils.drawingCoordinates(shooter.getCoordinates(), 0,   r + height / 2);
            projectile.setCoordinates(projectileCoord);
            DrawableFactory.get(projectile).draw(g);
        }
    }
}
