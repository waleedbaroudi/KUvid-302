package model.game_physics.path_patterns;

import utils.Velocity;

public class PathPatternFactory {
    private static PathPatternFactory instance = null;

    private PathPatternFactory(){}

    public static PathPatternFactory getInstance(){
        if(instance == null){
            instance = new PathPatternFactory();
        }
        return instance;
    }

    public PathPattern getBlockerPathPattern(){
        return new StraightPattern(new Velocity(0, 10));
    }
    public PathPattern getAtomPathPattern(){
        return new StraightPattern(new Velocity(0, -10));
    }
    public PathPattern getMoleculePathPattern(){
        return new ZigzagPatten(new Velocity(3, 3), 100);
    }
    public PathPattern getPowerUpPathPattern(){
        return new ZigzagPatten(new Velocity(2, 2), new Velocity(-1, 1), 100, 50);
    }


}
