package ui.movable_drawables;

import model.game_entities.Blocker;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;

/**
 * This class is responsible for drawing an Blocker given the Blocker entity in the constructor
 */
public class BlockerDrawer implements Drawable {

    private final Blocker blocker;
    private final Image blockerImage;

    public BlockerDrawer(Blocker blocker) {
        this.blocker = blocker;
        this.blockerImage = ImageResources.get(blocker.getType(), blocker.getSuperType(), null,
                (int) blocker.getHitbox().getWidth(),
                (int) blocker.getHitbox().getHeight());
    }

    @Override
    public void draw(Graphics g) {
        Coordinates drawingCoord = MathUtils.drawingCoordinates(blocker.getCoordinates(),
                blocker.getHitbox().getWidth(),
                blocker.getHitbox().getHeight());
        g.drawImage(blockerImage, drawingCoord.getPoint().x, drawingCoord.getPoint().y, null);
    }


}
