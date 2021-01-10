package model.game_entities;

import model.game_entities.enums.EntityType;
import model.game_entities.enums.SuperType;
import model.game_running.ProjectileContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShooterTest {

    @Test
    void switchAtom() {

        ProjectileContainer container = new ProjectileContainer(null, 10, 10, 10, 10, 1, 1, 1, 1);
        Shooter shooter = new Shooter(container);

        // testing when the current projectile is powerup. in this case , when we call switchAtom(), the current projectile set to atom.
        shooter.setCurrentProjectile(container.getPowerUp(shooter.getCoordinates(), EntityType.ALPHA));

        Projectile current = shooter.getCurrentProjectile();
        shooter.switchAtom();
        Projectile switched = shooter.getCurrentProjectile();

        assertEquals(switched.getSuperType(), SuperType.ATOM);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        container = new ProjectileContainer(null, 0, 0, 0, 0, 0, 0, 0, 0);
        shooter = new Shooter(container);
         

        Projectile current = shooter.getCurrentProjectile();
        shooter.switchAtom();
        Projectile switched = shooter.getCurrentProjectile();

        assertEquals(switched.getSuperType(), SuperType.ATOM);


    }
}