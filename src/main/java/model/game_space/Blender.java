package model.game_space;


import javafx.util.Pair;
import model.game_entities.Atom;
import model.game_running.ProjectileContainer;
import utils.MathUtils;

import java.util.HashMap;


public class Blender {

    private HashMap<Pair<Integer, Integer>, Integer> blendingMap;

    private final double[][] BLENDING_MATRIX = {{1, 2, 3, 4}, {.5, 1, 1, 1.5}, {0.333, 0.333, 1, 0.666}, {.25, .25, .25, 1}}; // TODO: Change to something cleaner.
    private ProjectileContainer projectileBlender;

    private BlenderListener blenderListener;

    public Blender(BlenderListener blenderListener, ProjectileContainer projectileBlender) {
        // get the container through a static method since the runningMode will be a singleton
        // this.container = container;
        blendingMap = new HashMap<>();


        this.projectileBlender = projectileBlender;
        this.blenderListener = blenderListener;
    }

    public void blend(int sourceAtom, int destinationAtom) {
        Pair<Integer, Integer> blendPair = new Pair<>(sourceAtom, destinationAtom);
        Pair<Integer, Integer> blendPair2 = new Pair<>(destinationAtom, sourceAtom);

        boolean canBlend = false;
        System.out.println(canBlend = projectileBlender.decreaseAtoms(sourceAtom, (int) Math.ceil(sourceAtom * BLENDING_MATRIX[sourceAtom - 1][destinationAtom - 1])));
        if (canBlend)
            System.out.println(projectileBlender.increaseAtoms(destinationAtom, (int) Math.ceil(destinationAtom * BLENDING_MATRIX[destinationAtom - 1][sourceAtom - 1])));
        System.out.println(projectileBlender.toString());

        blenderListener.onBlend();
    }

    public void showBlender() {
        blenderListener.onShow();
    }

    public void setBlenderListener(BlenderListener blenderListener) {
        this.blenderListener = blenderListener;
    }

    public interface BlenderListener {
        /**
         * this method is called after game parameters get checked and proved valid.
         */
        void onBlend();

        void onShow();
    }
}
