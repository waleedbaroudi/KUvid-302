package ui.windows;

import model.game_building.Configuration;
import model.game_entities.AutonomousEntity;
import model.game_entities.Entity;
import model.game_running.GameConstants;
import model.game_running.RunningMode;
import ui.movable_drawables.Drawable;
import ui.movable_drawables.DrawableFactory;
import ui.movable_drawables.ShooterDrawer;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.StrokeBorder;
import java.awt.*;
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
    StatisticsWindow statisticsWindow;
    private boolean running;
    private boolean paused;
    Configuration config;
    private final Map<AutonomousEntity, Drawable> drawableMap;
    private final ShooterDrawer shooterDrawable;

    public RunningWindow(String title) { // TODO: CLEAN: maybe move panel to a separate class.
        super(title);
        drawableMap = new ConcurrentHashMap<>(); //concurrent so that it supports concurrent addition and deletion.
        this.config = Configuration.getInstance();
        this.setSize(config.getRunningWindowDimension());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.runningMode = new RunningMode(this, this);
        this.shooterDrawable = new ShooterDrawer(this.runningMode.getAtomShooter());
        gameContentPanel = new GamePanel(this.runningMode, drawableMap, this.shooterDrawable);
        statisticsWindow = new StatisticsWindow();

        getContentPane().add(gameContentPanel, BorderLayout.LINE_START);
        getContentPane().add(statisticsWindow, BorderLayout.LINE_END);

        //add separator
        JSeparator sep = new JSeparator(JSeparator.VERTICAL);
        sep.setBorder(new StrokeBorder(new BasicStroke(3)));
        getContentPane().add(sep, BorderLayout.CENTER);

        setVisible(true);
        pack();
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
