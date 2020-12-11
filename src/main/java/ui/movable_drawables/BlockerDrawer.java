package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Blocker;
import model.game_entities.enums.EntityType;
import model.game_building.GameConstants;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;

public class BlockerDrawer implements Drawable {

    private final Blocker blocker;
    private final int radius;
    private Image blockerImage;

    public BlockerDrawer(Blocker blocker) {
        this.blocker = blocker;
        this.radius = (int) (Configuration.getInstance().getUnitL() * GameConstants.BLOCKER_RADIUS);
        this.blockerImage = ImageResources.get(blocker, 2 * radius, 2 * radius);
    }

    @Override
    public void draw(Graphics g) {

        if (blocker.getType() == EntityType.ALPHA) {
            g.setColor(Color.BLACK);
        } else if (blocker.getType() == EntityType.BETA) {
            g.setColor(Color.BLUE);
        } else if (blocker.getType() == EntityType.SIGMA) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.PINK);
        }

        Coordinates drawingCoord = MathUtils.drawingCoordinates(blocker.getCoordinates(), radius);
        g.drawImage(blockerImage, drawingCoord.getPoint().x, drawingCoord.getPoint().y, null);
    }
}
