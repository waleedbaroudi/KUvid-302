package ui.windows;

import model.game_building.Configuration;
import model.game_entities.AutonomousEntity;
import model.game_running.GameCommandListener;
import model.game_running.RunningMode;
import ui.movable_drawables.Drawable;
import ui.movable_drawables.ShooterDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Map;

public class GamePanel extends JPanel {
    RunningMode runningMode;
    GameCommandListener commandListener;
    private Map<AutonomousEntity, Drawable> drawableMap;
    ShooterDrawer shooterDrawer;

    public GamePanel(RunningMode runningMode, Map<AutonomousEntity, Drawable> drawableMap) {
        this.setPreferredSize(Configuration.getInstance().getGamePanelDimensions());
        this.runningMode = runningMode;
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.drawableMap = drawableMap;
        this.setOpaque(false);
        this.shooterDrawer = new ShooterDrawer(runningMode.getShooter());
        this.commandListener = new GameCommandListener(this.runningMode, shooterDrawer);
        this.addKeyListener(commandListener);
    }

    public void unregisterInputListeners() {
            removeKeyListener(commandListener);
    }


    public void reset(Map<AutonomousEntity, Drawable> drawableMap) {
        this.drawableMap = drawableMap;
        this.shooterDrawer = new ShooterDrawer(runningMode.getShooter());
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        shooterDrawer.draw(g);
        shooterDrawer.drawHitbox(g);
        drawableMap.values().forEach(drawable -> {
            drawable.draw(g);
            //for demonstration, draw the hitboxes of the entities
            drawable.drawHitbox(g);
        });
    }
}
