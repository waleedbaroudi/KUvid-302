package model.game_running;

import model.game_building.Configuration;
import model.game_entities.Atom;
import model.game_entities.Shooter;
import model.game_entities.enums.ShieldType;
import model.game_entities.shields.EtaShield;
import model.game_entities.shields.LotaShield;
import model.game_entities.shields.ThetaShield;
import model.game_entities.shields.ZetaShield;

public class ShieldHandler {

    private final RunningMode runningMode;
    private final Shooter shooter;

    private int etaShields;
    private int lotaShields;
    private int thetaShields;
    private int zetaShields;

    public ShieldHandler(RunningMode runningMode, Shooter shooter) {
        this.shooter = shooter;
        this.runningMode = runningMode;
        etaShields = Configuration.getInstance().getNumOfEtaShields();
        lotaShields = Configuration.getInstance().getNumOfLotaShields();
        thetaShields = Configuration.getInstance().getNumOfThetaShields();
        zetaShields = Configuration.getInstance().getNumOfZetaShields();
    }

    public void applyEtaShield() {
        if (etaShields > 0) {
            etaShields--;
            runningMode.updateStatisticsShieldCount(ShieldType.ETA, etaShields);
            if (shooter.projectileIsAtom()) {
                Atom shieldedAtom = new EtaShield(shooter.getAtomProjectile());
                shooter.setCurrentProjectile(shieldedAtom);
            }
        }
    }

    public void applyLotaShield() {
        if (lotaShields > 0) {
            lotaShields--;
            runningMode.updateStatisticsShieldCount(ShieldType.LOTA, lotaShields);
            if (shooter.projectileIsAtom()) {
                Atom shieldedAtom = new LotaShield(shooter.getAtomProjectile());
                shooter.setCurrentProjectile(shieldedAtom);
            }
        }
    }

    public void applyThetaShield() {
        if (thetaShields > 0) {
            thetaShields--;
            runningMode.updateStatisticsShieldCount(ShieldType.THETA, thetaShields);
            if (shooter.projectileIsAtom()) {
                Atom shieldedAtom = new ThetaShield(shooter.getAtomProjectile());
                shooter.setCurrentProjectile(shieldedAtom);
            }
        }
    }

    public void applyZetaShield() {
        if (zetaShields > 0) {
            zetaShields--;
            runningMode.updateStatisticsShieldCount(ShieldType.ZETA, zetaShields);
            if (shooter.projectileIsAtom()) {
                Atom shieldedAtom = new ZetaShield(shooter.getAtomProjectile());
                shooter.setCurrentProjectile(shieldedAtom);
            }
        }
    }
}