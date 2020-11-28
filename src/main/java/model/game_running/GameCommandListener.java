package model.game_running;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameCommandListener implements KeyListener {

    private RunningMode runningMode;

    public GameCommandListener(RunningMode runningMode) {
        this.runningMode = runningMode;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_P:
                runningMode.setRunningState(GameConstants.GAME_STATE_PAUSED);
                System.out.println("PAUSING THE GAME///////////////////////////////////////////////////////////");
                break;
            case KeyEvent.VK_R:
                runningMode.setRunningState(GameConstants.GAME_STATE_RESUMED);
                break;
            case KeyEvent.VK_B:
                //open Blender
                break;
            case KeyEvent.VK_C:
//                switchAtom
                break;
            case KeyEvent.VK_UP:
                //shoot
                break;
            case KeyEvent.VK_LEFT:
                runningMode.moveShooter(GameConstants.SHOOTER_MOVEMENT_LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                runningMode.moveShooter(GameConstants.SHOOTER_MOVEMENT_RIGHT);
                break;
            case KeyEvent.VK_D:
                runningMode.rotateShooter(GameConstants.SHOOTER_ROTATION_RIGHT);
                break;
            case KeyEvent.VK_A:
                runningMode.rotateShooter(GameConstants.SHOOTER_ROTATION_LEFT);
                break;
            default:
                System.out.println("AIN'T CLICKIN");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                runningMode.moveShooter(GameConstants.SHOOTER_MOVEMENT_STILL);
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
