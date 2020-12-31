package model.game_entities.shields;

import model.game_entities.enums.ShieldType;

public class ShieldTuple {
    private int eta;
    private int lota;
    private int theta;
    private int zeta;

    public ShieldTuple() {
        this.eta = 0;
        this.lota = 0;
        this.theta = 0;
        this.zeta = 0;
    }

    public void addShield(ShieldType type) {
        switch (type) {
            case ETA:
                ++eta;
                break;
            case LOTA:
                ++lota;
                break;
            case THETA:
                ++theta;
                break;
            case ZETA:
                ++zeta;
                break;
        }
    }

    public int getEta() {
        return eta;
    }

    public int getLota() {
        return lota;
    }

    public int getTheta() {
        return theta;
    }

    public int getZeta() {
        return zeta;
    }

    @Override
    public String toString() {
        return "eta = " + eta +
                ", lota = " + lota +
                ", theta = " + theta +
                ", zeta = " + zeta;
    }

    public boolean isNotZeros() {
        return eta > 0 || lota > 0 || theta > 0 || zeta > 0;
    }
}