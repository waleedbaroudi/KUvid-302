package model.game_entities.shields;

import model.game_entities.Atom;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.ShieldType;
import model.game_entities.factories.AtomFactory;

public class ShieldedAtomFactory {
    public static Atom getShieldedAtom(ShieldTuple shieldTuple, EntityType type) {
        Atom atom = AtomFactory.getInstance().getAtom(type);
        atom = applyShields(shieldTuple, atom);
        return atom;
    }

    public static Atom applyShields(ShieldTuple shieldTuple, Atom nonShielded) {
        Atom atom = nonShielded;
        for (int i = 0; i < shieldTuple.getShieldsCount(ShieldType.ETA); i++)
            atom = new EtaShield(atom);
        for (int i = 0; i < shieldTuple.getShieldsCount(ShieldType.LOTA); i++)
            atom = new LotaShield(atom);
        for (int i = 0; i < shieldTuple.getShieldsCount(ShieldType.THETA); i++)
            atom = new ThetaShield(atom);
        for (int i = 0; i < shieldTuple.getShieldsCount(ShieldType.ZETA); i++)
            atom = new ZetaShield(atom);
        return atom;
    }
}
