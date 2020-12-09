package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Blocker;
import model.game_entities.enums.EntityType;
import model.game_running.GameConstants;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;

public class BlockerDrawer implements Drawable {

    private final Blocker blocker;
    private final int radius;


    public BlockerDrawer(Blocker blocker) {
        this.blocker = blocker;
        this.radius = (int) (Configuration.getInstance().getUnitL() * GameConstants.BLOCKER_RADIUS);
    }

    @Override
    public void draw(Graphics g) {

        Coordinates drawingCoord = MathUtils.drawingCoordinates(blocker.getCoordinates(), radius);
        g.fillRect(drawingCoord.getPoint().x, drawingCoord.getPoint().y, 2 * radius, 2 * radius);
    }
}
