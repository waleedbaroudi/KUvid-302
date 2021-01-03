package ui.movable_drawables;

import model.game_entities.Blocker;
import services.utils.Coordinates;
import services.utils.MathUtils;

import java.awt.*;

/**
 * This class is responsible for drawing an Blocker given the Blocker entity in the constructor
 */
public class BlockerDrawer implements Drawable {

    private final Blocker blocker;
    private final Image blockerImage;

    public BlockerDrawer(Blocker blocker) {
        this.blocker = blocker;
        this.blockerImage = ImageResources.get(blocker);
    }

    @Override
    public void draw(Graphics g) {
        Coordinates drawingCoordinates = MathUtils.drawingCoordinates(blocker.getCoordinates(),
                blocker.getHitbox().getWidth(),
                blocker.getHitbox().getHeight());
        g.drawImage(blockerImage, drawingCoordinates.getPoint().x, drawingCoordinates.getPoint().y, null);
    }

    @Override
    public void drawHitbox(Graphics g) {
        Coordinates drawingCoordinates = MathUtils.drawingCoordinates(blocker.getCoordinates(),
                blocker.getHitbox().getWidth(),
                blocker.getHitbox().getHeight());

        g.drawOval(
                drawingCoordinates.getPoint().x,
                drawingCoordinates.getPoint().y,
                (int) blocker.getHitbox().getWidth(),
                (int) blocker.getHitbox().getHeight());
    }

}
