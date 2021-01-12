package model.game_entities.shields;

import model.game_entities.enums.ShieldType;

public class ShieldTuple {
    private final int[] shields;

    public ShieldTuple() {
        shields = new int[4];
        shields[0] = 0;
        shields[1] = 0;
        shields[2] = 0;
        shields[3] = 0;
    }

    public ShieldTuple(ShieldTuple shieldTuple) {
        shields = new int[4];
        shields[0] = shieldTuple.getShieldsCount(ShieldType.ETA);
        shields[1] = shieldTuple.getShieldsCount(ShieldType.LOTA);
        shields[2] = shieldTuple.getShieldsCount(ShieldType.THETA);
        shields[3] = shieldTuple.getShieldsCount(ShieldType.ZETA);
    }

    public void addShield(ShieldType type) {
        shields[type.getValue()]++;
    }

    public int getShieldsCount(ShieldType type) {
        return shields[type.getValue()];
    }

    public void reset() {
        shields[0] = 0;
        shields[1] = 0;
        shields[2] = 0;
        shields[3] = 0;
    }

    @Override
    public String toString() {
        return "eta = " + shields[0] +
                ", lota = " + shields[1] +
                ", theta = " + shields[2] +
                ", zeta = " + shields[3];
    }

    public boolean isNotEmpty() {
        return shields[0] > 0 || shields[1] > 0 || shields[2] > 0 || shields[3] > 0;
    }

    public void setShieldsCount(int numOfShields, ShieldType type) {
        shields[type.getValue()] = numOfShields;
    }

    public void decreaseShieldCount(ShieldType type) {
        shields[type.getValue()]--;
    }
}