package ui.windows;

import model.game_building.Configuration;
import model.game_running.GameCommandListener;
import model.game_running.GameConstants;
import model.game_running.RunningMode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

/**
 * This class draws the game running window.
 * all game animations and actions will happen in this window
 */
public class RunningWindow extends JFrame implements RunningMode.RunningStateListener {
    RunningMode runningMode;
    JPanel gameContentPanel;
    GameCommandListener commandListener;
    private boolean running;
    private boolean paused;
    Configuration config;

    public RunningWindow(String title) { // TODO: CLEAN: maybe move panel to a separate class.
        super(title);
        this.config = Configuration.getInstance();
        this.setSize(config.getGameWidth(), config.getGameHeight());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.runningMode = new RunningMode(this);
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
                draw();
//            runningMode.updateEntityState(); //give this an entity to move it and check collision
            } else {
                runningMode.stop();
                gameTimer.stop();
            }
        };
        gameTimer.addActionListener(listener);
        gameTimer.start();
    }

    /**
     * starts the loop that draws game elements.
     */
    private void draw() {
        if (paused)
            return;
        System.out.println("GAME IS NOT PAUSED. DRAWING ENTITY");
        // todo: draw all elements here and trigger entity state update from 'runningMode'
    }

    @Override
    public void onRunningStateChanged(int state) {
        System.out.println((state == GameConstants.GAME_STATE_PAUSED) ? "PAUSED" : "RESUMED");
        this.paused = (state == GameConstants.GAME_STATE_PAUSED);
    }
}
