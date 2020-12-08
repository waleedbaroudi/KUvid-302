package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Shooter;
import model.game_running.GameConstants;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;

public class ShooterDrawer implements Drawable {

    private final Shooter shooter;
    private final int height;
    private final int width;
    private final Image shooterImage;

    public ShooterDrawer(Shooter shooter) {
        this.shooter = shooter;
        this.height = (int) (Configuration.getInstance().getUnitL() * GameConstants.SHOOTER_HEIGHT);
        this.width = (int) (Configuration.getInstance().getUnitL() * GameConstants.SHOOTER_WIDTH);
        this.shooterImage = ImageResources.get(this.shooter, width, height);
    }

    @Override
    public void draw(Graphics g) {

        Coordinates drawingCoord = MathUtils.drawingCoordinates(shooter.getCoordinates(), width / 2, height / 2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(shooter.getAngle()), (int) shooter.getCoordinates().getX(), (int) shooter.getCoordinates().getY());
        g2d.drawImage(shooterImage, drawingCoord.getPoint().x, drawingCoord.getPoint().y, null);
    }
}
