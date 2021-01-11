package model.game_entities.enums;

public enum ShieldType {
    ETA(0),
    LOTA(1),
    THETA(2),
    ZETA(3);

    private int value;

    ShieldType() {
    }

    ShieldType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
