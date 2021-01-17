package model.game_entities;

import model.game_entities.enums.EntityType;
import model.game_entities.enums.SuperType;
import model.game_running.ProjectileContainer;
import model.game_running.RunningMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ShooterTest {

    @Test
    void switchAtom() {

        ProjectileContainer container = new ProjectileContainer(
                null,
                10,
                10,
                10,
                10);

        RunningMode runningMode = new RunningMode(null, null, null, null);
        Shooter shooter = new Shooter(runningMode); // todo: fix this test (shooter used to take projectile container, now takes running mode)

        // testing when the current projectile is powerup. in this case , when we call switchAtom(), the current projectile set to atom.
        shooter.setCurrentProjectile(container.getPowerUp(shooter.getCoordinates(), EntityType.ALPHA));

        Projectile current = shooter.getCurrentProjectile();
        shooter.switchAtom();
        Projectile switched = shooter.getCurrentProjectile();

        assertEquals(switched.getSuperType(), SuperType.ATOM);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        // testing when the projectile container is empty. in this case , when we call switchAtom(), the current projectile is null.
        // this is unreachable case since we end the game if the container is empty, but we assure that it gives null values.
        container = new ProjectileContainer(null, 0, 0, 0, 0);
        shooter = new Shooter(null); // todo: fix (same as above)


        current = shooter.getCurrentProjectile();
        shooter.switchAtom();
        switched = shooter.getCurrentProjectile();

        assertEquals(current, null);
        assertEquals(switched, null);


        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        // testing when the projectile container has only one type of atoms. in this case , when we call switchAtom(), the current projectile is an atom of the same type.
        container = new ProjectileContainer(null, 10, 0, 0, 0);
        shooter = new Shooter(null); // todo: fix (same as above)

        // the current atom before switch.
        current = shooter.getCurrentProjectile();
        shooter.switchAtom();
        // the current atom after switch.
        switched = shooter.getCurrentProjectile();

        // they  should have the same type
        assertEquals(current.getEntityType(), switched.getEntityType());

        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        // testing when the current projectile is a random atom. in this case , when we call switchAtom(), the current projectile is switched to an atom of different type.
        container = new ProjectileContainer(null, 10, 15, 12, 11);
        shooter = new Shooter(null); // todo: fix (same as above)

        shooter.setCurrentProjectile(container.getRandomAtom(shooter.getCoordinates()));

        // the current atom before switch.
        current = shooter.getCurrentProjectile();
        EntityType currentType = current.getEntityType();

        shooter.switchAtom();

        // the current atom after switch.
        switched = shooter.getCurrentProjectile();
        EntityType switchedType = switched.getEntityType();


        // they  should have the same type
        assertFalse(switchedType == currentType);
    }
}