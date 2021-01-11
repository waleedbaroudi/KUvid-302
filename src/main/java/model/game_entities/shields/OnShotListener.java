package model.game_entities.shields;

public interface OnShotListener {
    void emptyTempShields();

    ShieldTuple getTempShields();

    void setTempShields(ShieldTuple shields);
}