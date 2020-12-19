package model.game_physics.path_patterns;

import utils.Coordinates;
import utils.Vector;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Path pattern that follows a list of path patterns, each for some number of iterations. By default, after the number of iteration
 * for the last path finishes, it starts again from the first path pattern.
 */
public class CompositePattern extends PathPattern {
    private ArrayList<PathPattern> patterns;
    private ArrayList<Integer> iterations;
    private PathPattern currentPattern;
    private int currentIteration;
    private int currentPatternIdx;
    private boolean circulate;

    /**
     *
     * @param patterns arraylist of patterns to follow
     * @param iterations arraylist of step sizes corresponding to the pattern
     * @param circulate True if circulating around the patterns after the iterations of the last pattern finishes
     */
    public CompositePattern(ArrayList<PathPattern> patterns, ArrayList<Integer> iterations, boolean circulate) {
        super(patterns.get(0).getCurrentCoords());
        this.patterns = patterns;
        this.iterations = iterations;
        this.currentIteration = 0;
        this.circulate = circulate;
        // set the current pattern to the first pattern
        setCurrentPattern(patterns.get(0));
    }

    /**
     *
     * @param patterns arraylist of patterns to follow
     * @param iterationSize a fixed number of iteration that all paths follow
     * @param circulate True if circulating around the patterns after the iterations of the last pattern finishes
     */
    public CompositePattern(ArrayList<PathPattern> patterns, int iterationSize, boolean circulate) {
        this(patterns, new ArrayList<Integer>(Collections.nCopies(patterns.size(), iterationSize)), circulate);
    }

    /**
     * Default is circulating around the patterns after the iterations of the last pattern finishes
     * @param patterns arraylist of patterns to follow
     * @param iterations arraylist of step sizes corresponding to the pattern
     */
    public CompositePattern(ArrayList<PathPattern> patterns, ArrayList<Integer> iterations) {
        this(patterns, iterations, true);
    }

    /**
     * Default is circulating around the patterns after the iterations of the last pattern finishes
     * @param patterns arraylist of patterns to follow
     * @param iterationSize a fixed number of iteration that all paths follow
     */
    public CompositePattern(ArrayList<PathPattern> patterns, int iterationSize) {
        this(patterns, iterationSize, true);
    }

    public ArrayList<PathPattern> getPatterns() {
        return patterns;
    }

    public ArrayList<Integer> getIterations() {
        return iterations;
    }

    public PathPattern getCurrentPattern() {
        return currentPattern;
    }

    public void setCurrentPattern(PathPattern currentPattern) {
        this.currentPattern = currentPattern;
    }

    @Override
    public Coordinates nextPosition() {
        if(this.currentIteration >= getIterations().get(currentPatternIdx)){
            getLogger().debug("[CompositePattern] iteration of the " + (this.currentPatternIdx+1) + "th pattern finished");
            getLogger().debug("[CompositePattern] transition coordinates are " + this.getCurrentCoords());
            // update the current pattern with initial coordinates corresponding to the current coordinates
            this.currentIteration = 0;
            this.currentPatternIdx += 1;
            this.currentPatternIdx %= getPatterns().size();
            setCurrentPattern(getPatterns().get(this.currentPatternIdx));
            getCurrentPattern().setCurrentCoords(getCurrentCoords());
        }
        this.currentIteration += 1;
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
