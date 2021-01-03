package model.game_running;

import model.game_building.Configuration;
import model.game_entities.Atom;
import model.game_entities.enums.ShieldType;
import model.game_entities.shields.EtaShield;
import model.game_entities.shields.LotaShield;
import model.game_entities.shields.ThetaShield;
import model.game_entities.shields.ZetaShield;

public class ShieldHandler {

    RunningMode runningMode;

    private int etaShields;
    private int lotaShields;
    private int thetaShields;
    private int zetaShields;

    public ShieldHandler(RunningMode runningMode) {
        this.runningMode = runningMode;
        etaShields = Configuration.getInstance().getNumOfEtaShields();
        lotaShields = Configuration.getInstance().getNumOfLotaShields();
        thetaShields = Configuration.getInstance().getNumOfThetaShields();
        zetaShields = Configuration.getInstance().getNumOfZetaShields();
    }

    public Atom applyEtaShield(Atom atom) {
        if (etaShields > 0) {
            etaShields--;
            runningMode.updateStatisticsShieldCount(ShieldType.ETA, etaShields);
            return new EtaShield(atom);
        }
        return atom;
    }

    public Atom applyLotaShield(Atom atom) {
        if (lotaShields > 0) {
            lotaShields--;
            runningMode.updateStatisticsShieldCount(ShieldType.LOTA, lotaShields);
            return new LotaShield(atom);
        }
        return atom;
    }

    public Atom applyThetaShield(Atom atom) {
        if (thetaShields > 0) {
            thetaShields--;
            runningMode.updateStatisticsShieldCount(ShieldType.THETA, thetaShields);
            return new ThetaShield(atom);
        }
        return atom;
    }

    public Atom applyZetaShield(Atom atom) {
        if (zetaShields > 0) {
            zetaShields--;
            runningMode.updateStatisticsShieldCount(ShieldType.ZETA, zetaShields);
            return new ZetaShield(atom);
        }
        return atom;
    }
}