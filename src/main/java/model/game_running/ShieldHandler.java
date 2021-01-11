package model.game_running;

import model.game_building.Configuration;
import model.game_entities.Atom;
import model.game_entities.Shooter;
import model.game_entities.enums.ShieldType;
import model.game_entities.shields.*;

public class ShieldHandler implements OnShotListener {

    private final RunningMode runningMode;
    private final Shooter shooter;

    private ShieldTuple tempShields;
    private final ShieldTuple shields;

    public ShieldHandler(RunningMode runningMode, Shooter shooter) {
        this.shooter = shooter;
        this.runningMode = runningMode;
        shields = new ShieldTuple();
        tempShields = new ShieldTuple();

        shields.setShieldsCount(Configuration.getInstance().getNumOfEtaShields(), ShieldType.ETA);
        shields.setShieldsCount(Configuration.getInstance().getNumOfLotaShields(), ShieldType.LOTA);
        shields.setShieldsCount(Configuration.getInstance().getNumOfThetaShields(), ShieldType.THETA);
        shields.setShieldsCount(Configuration.getInstance().getNumOfZetaShields(), ShieldType.ZETA);
    }

    public void applyShield(ShieldType type) {
        if (shields.getShieldsCount(type) > 0) {
            shields.decreaseShieldCount(type);
            tempShields.addShield(type);
            runningMode.updateStatisticsShieldCount();
            if (shooter.projectileIsAtom()) {
                Atom shieldedAtom = applyShield(shooter.getAtomProjectile(), type);
                shooter.setCurrentProjectile(shieldedAtom);
            }
        }
    }

    private Atom applyShield(Atom atom, ShieldType type) {
        switch (type) {
            case ETA:
                return new EtaShield(atom);
            case LOTA:
                return new LotaShield(atom);
            case THETA:
                return new ThetaShield(atom);
            case ZETA:
                return new ZetaShield(atom);
        }
        return atom;
    }

    @Override
    public void emptyTempShields() {
        tempShields.reset();
    }

    @Override
    public ShieldTuple getTempShields() {
        return new ShieldTuple(this.tempShields);
    }

    @Override
    public void setTempShields(ShieldTuple shields) {
        this.tempShields = shields;
    }

    public String getShieldsCount(ShieldType type) {
        return shields.getShieldsCount(type) + "";
    }
}




