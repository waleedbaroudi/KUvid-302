package model.game_physics.path_patterns;

import model.game_building.Configuration;
import model.game_building.GameConstants;
import utils.Coordinates;
import utils.Vector;

import java.util.List;

/**
 * This is a composite path pattern that alternate between set of path patterns based on the ration of the game
 * view
 */
public class RatioPattern extends PathPattern{

    private List<PathPattern> patterns;
    private List<Double> ratios;
    private PathPattern currentPattern;
    private int currentPatternIdx;
    private double lastYCoords;

    /**
     * @param patterns List of patterns to follow
     * @param ratios List of screen ration corresponding to the pattern
     */
    public RatioPattern(List<PathPattern> patterns, List<Double> ratios) {
        super(patterns.get(0).getCurrentCoords());
        this.patterns = patterns;
        this.ratios = ratios;
        this.lastYCoords = patterns.get(0).getCurrentCoords().getY();
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
    public Coordinates nextPosition() {
        if(getCurrentCoords().getY() - lastYCoords
                >= ratios.get(currentPatternIdx) * Configuration.getInstance().getGamePanelDimensions().height){
            getLogger().debug("[RatioPattern] ratio of the " + (this.currentPatternIdx+1) + "th pattern finished");
            getLogger().debug("[RatioPattern] transition coordinates are " + this.getCurrentCoords());
            // update the last y coordinates and the current pattern
            this.lastYCoords = getCurrentCoords().getY();
            this.currentPatternIdx += 1;
            this.currentPatternIdx %= getPatterns().size();
            setCurrentPattern(getPatterns().get(this.currentPatternIdx));
            getCurrentPattern().setCurrentCoords(getCurrentCoords());
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
