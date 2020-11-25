package ui.movable_drawables;

import model.game_entities.Powerup;

import java.awt.*;

public class PowerupDrawer implements Drawable {

    private Powerup powerup;

    public PowerupDrawer(Powerup powerup){
        this.powerup = powerup;
    }

    @Override
    public void draw(Graphics g) {

    }
}
