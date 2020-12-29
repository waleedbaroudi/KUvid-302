package model.game_entities.enums;

public enum EntityType {
    ALPHA(1),
    BETA(2),
    GAMMA(3),
    SIGMA(4);

    private final int value;

    // constructor with value
    EntityType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EntityType forValue(int val) {
        for (EntityType type : values()) {
            if (type.getValue() == val) return type;
        }
        return null;
    }
}
