package model.game_space;


import javafx.util.Pair;
import model.game_building.GameConstants;
import model.game_entities.Atom;
import model.game_running.ProjectileContainer;
import utils.MathUtils;

import java.util.ArrayList;
import java.util.HashMap;


public class Blender {

    private final ProjectileContainer projectileBlender;
    private BlenderListener blenderListener;

    public Blender(BlenderListener blenderListener, ProjectileContainer projectileBlender) {
        this.projectileBlender = projectileBlender;
        this.blenderListener = blenderListener;
    }

    public void blend(int sourceAtom, int destinationAtom) { // TODO: Delete this method after implementing multiple blending of a certain atom.
        boolean canBlend = false;
        System.out.println(canBlend = projectileBlender.decreaseAtoms(sourceAtom, (int) Math.ceil(sourceAtom *
                GameConstants.BLENDING_MATRIX[sourceAtom - 1][destinationAtom - 1])));
        if (canBlend)
            System.out.println(projectileBlender.increaseAtoms(destinationAtom, (int) Math.ceil(destinationAtom *
                    GameConstants.BLENDING_MATRIX[destinationAtom - 1][sourceAtom - 1])));
        System.out.println(projectileBlender.toString());

        blenderListener.onBlend();
    }

    public void blend(int numberOfSourceAtoms, int sourceAtom, int destinationAtom) {// TODO: Use this method to blend after implementing multiple blending of a certain atom.
        boolean canBlend = false;
        System.out.println(canBlend = projectileBlender.decreaseAtoms(sourceAtom, numberOfSourceAtoms *
                (int) Math.ceil(sourceAtom * GameConstants.BLENDING_MATRIX[sourceAtom - 1][destinationAtom - 1])));
        if (canBlend)
            System.out.println(projectileBlender.increaseAtoms(destinationAtom, numberOfSourceAtoms *
                    (int) Math.ceil(destinationAtom * GameConstants.BLENDING_MATRIX[destinationAtom - 1][sourceAtom - 1])));
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
