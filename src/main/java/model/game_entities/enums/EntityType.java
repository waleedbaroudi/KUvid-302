package model.game_entities.enums;

public enum EntityType {
    ALPHA(0),
    BETA(1),
    GAMMA(2),
    SIGMA(3);

    private int value;

    EntityType() {
    }



    // constructor with value
    EntityType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String[] stringValues() {
        return new String[]{"Alpha", "Beta", "Gamma", "Sigma"};
    }

    public static EntityType forValue(int val) {
        for (EntityType type : values()) {
            if (type.getValue() == val) return type;
        }
        return null;
    }
}
