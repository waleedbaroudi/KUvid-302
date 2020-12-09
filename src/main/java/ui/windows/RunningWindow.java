package ui.windows;

import model.game_building.Configuration;
import model.game_entities.AutonomousEntity;
import model.game_entities.Entity;
import model.game_running.Blender;
import model.game_running.GameConstants;
import model.game_running.RunningMode;
import ui.movable_drawables.Drawable;
import ui.movable_drawables.DrawableFactory;
import ui.movable_drawables.ShooterDrawer;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class draws the game running window.
 * all game animations and actions will happen in this window
 */
public class RunningWindow extends JFrame implements RunningMode.RunningStateListener, RunningMode.GameEntitiesListener {
    RunningMode runningMode;
    GamePanel gameContentPanel;
    private boolean running;
    private boolean paused;
    Configuration config;
    private final Map<AutonomousEntity, Drawable> drawableMap;
    private final ShooterDrawer shooterDrawable;
    private Blender blender;
    private BlenderWindow blenderWindow;

    public RunningWindow(String title) { // TODO: CLEAN: maybe move panel to a separate class.
        super(title);
        drawableMap = new ConcurrentHashMap<>(); // concurrent so that it supports concurrent addition and deletion.
        this.config = Configuration.getInstance();
        this.setSize(config.getGameWidth(), config.getGameHeight());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.runningMode = new RunningMode(this, this);
        System.out.println("in running window" + runningMode.getBlender());
        blenderWindow = new BlenderWindow(runningMode.getBlender()); // Window that implements the blending listener for the observer pattern
        this.shooterDrawable = new ShooterDrawer(this.runningMode.getAtomShooter());
        gameContentPanel = new GamePanel(this.runningMode, drawableMap, this.shooterDrawable);
        getContentPane().add(gameContentPanel);
        setVisible(true);
        start();
    }

    /**
     * starts the the game loop (drawing, movement, and collision checks)
     */
    private void start() {
        running = true; // this will be made false somewhere else (when health or time are over)
        runningMode.startThreads();
        startDrawingThread();
    }

    private void startDrawingThread() {
        Timer gameTimer = new Timer(15, null);
        ActionListener listener = e -> {
            if (running) {
                if (!paused) {
                    repaint();
                }
            } else {
                runningMode.setRunningState(GameConstants.GAME_STATE_STOP);
                gameTimer.stop();
            }
        };
        gameTimer.addActionListener(listener);
        gameTimer.start();
    }


    /**
     * starts the loop that draws game elements.
     */
    @Override
    public void onRunningStateChanged(int state) {
        this.paused = (state == GameConstants.GAME_STATE_PAUSED);
    }

    @Override
    public void onEntityAdd(AutonomousEntity entity) {
        drawableMap.put(entity, DrawableFactory.get(entity));
    }

    @Override
    public void onEntitiesRemove(Collection<AutonomousEntity> entities) {
        for (AutonomousEntity entity : entities)
            drawableMap.remove(entity);
    }
}
