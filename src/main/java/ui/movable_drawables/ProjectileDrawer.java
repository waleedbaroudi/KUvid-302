package ui.movable_drawables;

import model.game_entities.Powerup;
import model.game_entities.Projectile;
import utils.Coordinates;
import utils.MathUtils;

import java.awt.*;

public abstract class ProjectileDrawer implements Drawable {

    private  Projectile projectile;


    public ProjectileDrawer(Projectile projectile){
        this.projectile = projectile;

    }



}
