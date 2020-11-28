package ui.windows;

import model.game_building.Configuration;
import model.game_entities.Atom;
import model.game_entities.AutonomousEntity;
import model.game_entities.enums.AtomType;
import model.game_physics.path_patterns.StraightPattern;
import model.game_running.GameCommandListener;
import model.game_running.GameConstants;
import model.game_running.RunningMode;
import ui.movable_drawables.Drawable;
import ui.movable_drawables.DrawableFactory;
import utils.Coordinates;
import utils.Velocity;

import javax.swing.*;
import javax.swing.text.html.parser.Entity;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

/**
 * This class draws the game running window.
 * all game animations and actions will happen in this window
 */
public class RunningWindow extends JFrame implements RunningMode.RunningStateListener, RunningMode.GameEntitiesListener {
    RunningMode runningMode;
    JPanel gameContentPanel;
    GameCommandListener commandListener;
    private boolean running;
    private boolean paused;
    Configuration config;
    private Map<AutonomousEntity, Drawable> drawableMap;

    public RunningWindow(String title) { // TODO: CLEAN: maybe move panel to a separate class.
        super(title);
        drawableMap = new HashMap<AutonomousEntity, Drawable>();


        // TODO: DELETE ME !!!!
        StraightPattern path = new StraightPattern(new Coordinates(50, 50), new Velocity(1,1));
        Atom atom = new Atom(new Coordinates(50, 50), null, path, AtomType.ALPHA);
        Drawable d1 = DrawableFactory.get(atom);

        drawableMap.put(atom, d1);
        this.config = Configuration.getInstance();
        this.setSize(config.getGameWidth(), config.getGameHeight());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.runningMode = new RunningMode(this, this);
        this.commandListener = new GameCommandListener(this.runningMode);
        gameContentPanel = new JPanel();
        getContentPane().add(gameContentPanel);
        gameContentPanel.setFocusable(true);
        gameContentPanel.requestFocusInWindow();
        gameContentPanel.addKeyListener(commandListener);
        setVisible(true);
        start();
    }

    /**
     * starts the the game loop (drawing, movement, and collision checks)
     */
    private void start() {
        System.out.println("CALLED START");
        running = true; // this will be made false somewhere else (when health or time are over)
        runningMode.startThreads();
        startDrawingThread();
    }

    private void startDrawingThread() {
        Timer gameTimer = new Timer(GameConstants.GAME_THREAD_DELAY, null);
        ActionListener listener = e -> {
            if (running) {
                repaint();
            } else {
                runningMode.stop();
                gameTimer.stop();
            }
        };
        gameTimer.addActionListener(listener);
        gameTimer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        for(AutonomousEntity entity: drawableMap.keySet()) {
            draw(drawableMap.get(entity), g);
            runningMode.updateEntityState(entity); //give this an entity to move it and check collision
        }

    }

    /**
     * starts the loop that draws game elements.
     */
    private void draw(Drawable drawableEntity, Graphics g) {
        if (paused)
            return;

        drawableEntity.draw(g);
        // todo: draw all elements here and trigger entity state update from 'runningMode'

    }

    @Override
    public void onRunningStateChanged(int state) {
        System.out.println((state == GameConstants.GAME_STATE_PAUSED) ? "PAUSED" : "RESUMED");
        this.paused = (state == GameConstants.GAME_STATE_PAUSED);
    }

    @Override
    public void onEntityAdd(AutonomousEntity entity) {
        drawableMap.put(entity, DrawableFactory.get(entity));
    }

    @Override
    public void onEntityRemove(AutonomousEntity entity) {
        drawableMap.remove(entity);
    }
}
