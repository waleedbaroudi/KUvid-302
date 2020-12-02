package ui.windows;

import model.game_entities.AutonomousEntity;
import model.game_entities.Entity;
import model.game_running.GameCommandListener;
import model.game_running.RunningMode;
import ui.movable_drawables.Drawable;
import ui.movable_drawables.ShooterDrawer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class GamePanel extends JPanel {
    RunningMode runningMode;
    GameCommandListener commandListener;
    private final Map<AutonomousEntity, Drawable> drawableMap;
    ShooterDrawer shooterDrawer;

    public GamePanel(RunningMode runningMode, Map<AutonomousEntity, Drawable> drawableMap, ShooterDrawer shooterDrawer) {
        this.runningMode = runningMode;
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.commandListener = new GameCommandListener(this.runningMode);
        this.addKeyListener(commandListener);
        this.drawableMap = drawableMap;
        this.shooterDrawer = shooterDrawer;
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        for (AutonomousEntity entity : drawableMap.keySet()) {
            drawableMap.get(entity).draw(g);
            runningMode.updateEntityState(entity);
        }
        shooterDrawer.draw(g);
    }
}
