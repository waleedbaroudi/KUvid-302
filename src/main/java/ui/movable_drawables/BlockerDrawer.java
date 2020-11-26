package ui.movable_drawables;

import model.game_building.Configuration;
import model.game_entities.Blocker;
import model.game_entities.enums.AtomType;
import model.game_entities.enums.BlockerType;

import java.awt.*;

public class BlockerDrawer implements Drawable {

    private Blocker blocker;

    public BlockerDrawer(Blocker blocker) {
        this.blocker = blocker;
    }

    @Override
    public void draw(Graphics g) {
        int r = (int) (Configuration.getInstance().getUnitL() * 0.1);

        g.drawRect((int) blocker.getCoordinate().getX() - r,
                (int) blocker.getCoordinate().getY() - r,
                2 * r,
                2 * r);

        if (blocker.getType() == BlockerType.ALPHA_B) {
            g.setColor(Color.BLACK);
        } else if (blocker.getType() == BlockerType.BETA_B) {
            g.setColor(Color.BLUE);
        } else if (blocker.getType() == BlockerType.SIGMA_B) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.PINK);
        }
    }
}
