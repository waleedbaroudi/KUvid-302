package model.game_physics.path_patterns;

import com.fasterxml.jackson.annotation.JsonTypeName;
import model.game_building.Configuration;
import utils.Coordinates;
import utils.Vector;

import java.util.List;

/**
 * This is a composite path pattern that alternate between set of path patterns based on the ration of the game
 * view
 */
@JsonTypeName("ratio-pattern")
public class RatioPattern extends PathPattern{

    private List<PathPattern> patterns;
    private List<Double> ratios;
    private PathPattern currentPattern;
    private int currentPatternIdx;
    private double lastYCoords;

    public RatioPattern(){}
    /**
     * @param patterns List of patterns to follow
     * @param ratios List of screen ration corresponding to the patterns
     */
    public RatioPattern(List<PathPattern> patterns, List<Double> ratios) {
        this.patterns = patterns;
        this.ratios = ratios;
        this.lastYCoords = 0;
        // set the current pattern to the first pattern
        setCurrentPattern(patterns.get(0));
    }
    
    
    public List<PathPattern> getPatterns() {
        return patterns;
    }

    public PathPattern getCurrentPattern() {
        return currentPattern;
    }

    public void setCurrentPattern(PathPattern currentPattern) {
        this.currentPattern = currentPattern;
    }


    @Override
    public void setCurrentCoords(Coordinates currentCoords) {
        super.setCurrentCoords(currentCoords);
        getCurrentPattern().setCurrentCoords(currentCoords);
    }

    @Override
    public Coordinates nextPosition() {
        if(getCurrentCoords().getY() - lastYCoords
                >= ratios.get(currentPatternIdx) * Configuration.getInstance().getGamePanelDimensions().height){
            getLogger().debug("[RatioPattern] ratio of the " + (this.currentPatternIdx+1) + "th pattern finished");
            getLogger().debug("[RatioPattern] transition coordinates are " + this.getCurrentCoords());
            // update the last y coordinates and the current pattern
            Coordinates tmpCoords = getCurrentCoords();
            this.lastYCoords = tmpCoords.getY();
            this.currentPatternIdx += 1;
            this.currentPatternIdx %= getPatterns().size();
            setCurrentPattern(getPatterns().get(this.currentPatternIdx));
            setCurrentCoords(tmpCoords);
        }
        setCurrentCoords(getCurrentPattern().nextPosition());
        return this.getCurrentCoords();
    }

    @Override
    public void reflect(Vector n) {
        try {
            currentPattern = (PathPattern) currentPattern.clone();
            currentPattern.reflect(n);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
