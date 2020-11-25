package model.game_physics.path_patterns;

public class PathPatternFactory {
    private static PathPatternFactory instance = null;

    private PathPatternFactory(){}

    public static PathPatternFactory getInstance(){
        if(instance == null){
            instance = new PathPatternFactory();
        }
        return instance;
    }


}
