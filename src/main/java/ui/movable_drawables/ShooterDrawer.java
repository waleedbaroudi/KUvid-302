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
    private final int atomRadius;
    private final int powerupRadius;

    public ShooterDrawer(Shooter shooter) {
        this.shooter = shooter;
        this.height = (int) (Configuration.getInstance().getUnitL() * GameConstants.SHOOTER_HEIGHT);
        this.width = (int) (Configuration.getInstance().getUnitL() * GameConstants.SHOOTER_WIDTH);
        this.shooterImage = ImageResources.get(null, shooter.getSuperType(), width, height);
        this.atomRadius = (int) (Configuration.getInstance().getUnitL() * GameConstants.ATOM_RADIUS);
        this.powerupRadius = (int) (Configuration.getInstance().getUnitL() * GameConstants.POWERUP_RADIUS);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(shooter.getAngle()), (int) shooter.getCoordinates().getX(), (int) shooter.getCoordinates().getY());
        projectile = shooter.getCurrentProjectile();

        if (projectile != null) {
            int r = projectile.getSuperType() == SuperType.ATOM ? atomRadius : powerupRadius;
            Coordinates projectileCoord = MathUtils.drawingCoordinates(shooter.getCoordinates(), r, 2*r + height / 2 );
            g2d.drawImage(
                    ImageResources.get(projectile.getType(), projectile.getSuperType(), 2*r, 2*r),
                    projectileCoord.getPoint().x,
                    projectileCoord.getPoint().y,
                    null);
        }

        Coordinates drawingCoord = MathUtils.drawingCoordinates(shooter.getCoordinates(), width / 2, height / 2);
        g2d.drawImage(shooterImage, drawingCoord.getPoint().x, drawingCoord.getPoint().y, null);

    }
}
