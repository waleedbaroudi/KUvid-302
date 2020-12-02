package ui.windows;

import model.game_building.Configuration;
import model.game_entities.AutonomousEntity;
import model.game_running.GameConstants;
import model.game_running.RunningMode;
import ui.movable_drawables.Drawable;
import ui.movable_drawables.DrawableFactory;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

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

    public RunningWindow(String title) { // TODO: CLEAN: maybe move panel to a separate class.
        super(title);
        drawableMap = new HashMap<AutonomousEntity, Drawable>();


//        // TODO: BELOW CODE FOR DEMONSTRATION. REMOVE LATER !!!!
//        ZigzagPatten path = new ZigzagPatten(new Coordinates(50, 50), new Velocity(3, 3), 150);
//        Atom atom = new Atom(new Coordinates(50, 50), null, path, AtomType.ALPHA);
//        Drawable d1 = DrawableFactory.get(atom);
//        drawableMap.put(atom, d1);
        this.config = Configuration.getInstance();
        this.setSize(config.getGameWidth(), config.getGameHeight());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.runningMode = new RunningMode(this, this);
        gameContentPanel = new GamePanel(this.runningMode, drawableMap);
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
                System.out.println("game running");
                System.out.println("PAUSED? " + paused);
                if (!paused) {
                    System.out.println("REPAINTING");
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
