package model.game_running.listeners;

public interface ShooterEventListener {
    void onShot();

    void onMoved();

    void onStopped();
}
