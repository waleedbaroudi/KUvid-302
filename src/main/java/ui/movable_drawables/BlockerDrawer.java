package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Blocker;
import model.game_entities.enums.AtomType;
import model.game_entities.enums.BlockerType;
import model.game_running.GameConstants;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;

public class BlockerDrawer implements Drawable {

    private final Blocker blocker;
    private final int radius;


    public BlockerDrawer(Blocker blocker) {
        this.blocker = blocker;
        this.radius = (int) (Configuration.getInstance().getUnitL() * GameConstants.BLOCKER_SIZE);
    }

    @Override
    public void draw(Graphics g) {

        if (blocker.getType() == BlockerType.ALPHA_B) {
            g.setColor(Color.BLACK);
        } else if (blocker.getType() == BlockerType.BETA_B) {
            g.setColor(Color.BLUE);
        } else if (blocker.getType() == BlockerType.SIGMA_B) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.PINK);
        }

        Coordinates drawingCoord = MathUtils.drawingCoordinates(blocker.getCoordinates(), radius);

        g.fillRect((int) drawingCoord.getX(),
                (int) drawingCoord.getY(),
                2 * radius,
                2 * radius);
    }
}
