package model.game_entities.shields;

public abstract class ShieldDecorator implements Shieldable{
    Shieldable shield;
    public ShieldDecorator(Shieldable shield){
        this.shield = shield;
    }
}
