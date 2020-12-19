package model.game_physics.path_patterns;

import utils.Coordinates;
import utils.Vector;
import utils.Velocity;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A zigzag composite pattern that follows two interchanging straight pattern
 */

public class ZigzagPatten extends PathPattern{
    CompositePattern zigzagPattern;

    public ZigzagPatten(Velocity rightDiagonalVelocity, Velocity leftDiagonalVelocity, int rightDescendSteps, int leftDescentSteps, boolean rightFirst) {
        // prepare the composite pattern
        ArrayList<PathPattern> patterns = new ArrayList<>();
        ArrayList<Integer> iters = new ArrayList<>();

        // add the right diagonal pattern
        patterns.add(new StraightPattern(rightDiagonalVelocity));
        iters.add(rightDescendSteps);

        // add the left diagonal pattern
        patterns.add(new StraightPattern(leftDiagonalVelocity));
        iters.add(leftDescentSteps);

        if(!rightFirst){
            // swap the patterns, and corresponding iterations
            Collections.swap(patterns, 0, 1);
            Collections.swap(iters, 0, 1);
        }

        // set the initial coordinates of the first pattern to the given initial coordinates
        this.zigzagPattern = new CompositePattern(patterns, iters);
    }

    public ZigzagPatten(Velocity rightDiagonalVelocity, Velocity leftDiagonalVelocity, int rightDescendSteps, int leftDescentSteps){
        this(rightDiagonalVelocity, leftDiagonalVelocity, rightDescendSteps, leftDescentSteps, true);
    }

    public ZigzagPatten(Velocity diagonalVelocity, int iterationStep, boolean rightFirst) {
        // prepare the composite pattern
        ArrayList<PathPattern> patterns = new ArrayList<>();

        // add the right diagonal pattern
        patterns.add(new StraightPattern(diagonalVelocity));

        // add the left diagonal pattern
        patterns.add(new StraightPattern(new Velocity(-1 * diagonalVelocity.getXv(), diagonalVelocity.getYv())));

        if(!rightFirst){
            // swap the patterns, and corresponding iterations
            Collections.swap(patterns, 0, 1);
        }

        // set the initial coordinates of the first pattern to the given initial coordinates
        this.zigzagPattern = new CompositePattern(patterns, iterationStep);
    }

    public ZigzagPatten(Velocity diagonalVelocity, int iterationStep) {
        this(diagonalVelocity, iterationStep, true);
    }

    @Override
    public void setCurrentCoords(Coordinates currentCoords) {
        this.zigzagPattern.getCurrentPattern().setCurrentCoords(currentCoords);
    }

    @Override
    public Coordinates nextPosition() {
        return this.zigzagPattern.nextPosition();
    }

    @Override
    public void reflect(Vector n) {
        zigzagPattern.reflect(n);
    }
}