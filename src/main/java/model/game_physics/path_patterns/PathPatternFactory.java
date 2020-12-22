package model.game_physics.path_patterns;
import utils.Vector;
import utils.Velocity;

// TODO: get the entity speed from the configuration
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
    public PathPattern getMoleculePathPattern(){
        return new ZigzagPatten(new Velocity(3, 3), 100);
    }
    public PathPattern getPowerUpPathPattern(){
        return new ZigzagPatten(new Velocity(2, 2), 90);
    }
    public PathPattern getAtomPathPattern(double angle){
        // rotate the vector speed
        Vector rotatedVector = (new Vector(0, -10)).rotateVector(angle);
        return new StraightPattern(new Velocity(rotatedVector));
    }
    public PathPattern getAtomPathPattern(){
        return getAtomPathPattern(0);
    }


}
