package model.game_running;

import model.game_building.Configuration;
import model.game_entities.Atom;
import model.game_entities.shields.EtaShield;
import model.game_entities.shields.LotaShield;
import model.game_entities.shields.ThetaShield;
import model.game_entities.shields.ZetaShield;

public class ShieldHandler {

    private int etaShields;
    private int lotaShields;
    private int thetaShields;
    private int zetaShields;

    public ShieldHandler() {
        etaShields = Configuration.getInstance().getNumOfEtaShields();
        lotaShields = Configuration.getInstance().getNumOfLotaShields();
        thetaShields = Configuration.getInstance().getNumOfThetaShields();
        zetaShields = Configuration.getInstance().getNumOfZetaShields();
    }

    public Atom applyEtaShield(Atom atom) {
        if (etaShields > 0  atom != null) {
            etaShields--;
            return new EtaShield(atom);
        }
        return atom;
    }

    public Atom applyLotaShield(Atom atom) {
        if (lotaShields > 0 atom != null) {
            lotaShields--;
            return new LotaShield(atom);
        }
        return atom;
    }

    public Atom applyThetaShield(Atom atom) {
        if (thetaShields > 0 atom != null) {
            thetaShields--;
            return new ThetaShield(atom);
        }
        return atom;
    }

    public Atom applyZetaShield(Atom atom) {
        if (zetaShields > 0 atom != null) {
            zetaShields--;
            return new ZetaShield(atom);
        }
        return atom;
    }
}