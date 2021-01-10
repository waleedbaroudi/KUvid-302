package model.game_space;


import model.game_building.GameConstants;
import model.game_running.ProjectileContainer;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Blender {

    private static final Logger logger = Logger.getLogger(Blender.class.getName());

    private ProjectileContainer projectileContainer;
    private BlenderListener blenderListener;

    public Blender(ProjectileContainer projectileContainer) {
        this.projectileContainer = projectileContainer;
        logger.setLevel(Level.ALL);
    }

    /**
     * Blends/Breaks a number of source atoms into a number of target atoms
     *
     * @param sourceAtom       The atom to be blended
     * @param destinationAtom  The result atom
     * @param numOfConversions The number of the desired atom.
     */
//    public void blend(int sourceAtom, int destinationAtom, int destinationAtomQuantity) {
//        boolean canBlend;
//        System.out.println(sourceAtom + " " + " ");
//        canBlend = projectileContainer.decreaseAtoms(sourceAtom, destinationAtomQuantity *
//                (int) Math.ceil(sourceAtom * GameConstants.BLENDING_MATRIX[sourceAtom][destinationAtom]));
//        if (canBlend) {
//            projectileContainer.increaseAtoms(destinationAtom, destinationAtomQuantity *
//                    (int) Math.ceil(destinationAtom * GameConstants.BLENDING_MATRIX[destinationAtom][sourceAtom]));
//            if (blenderListener != null)
//                blenderListener.onBlend();
//        } else {
//            if (blenderListener != null)
//                blenderListener.onFailBlend();
//        }
//    }
    public void convert(int sourceAtom, int destinationAtom, int numOfConversions) {
        if (sourceAtom > destinationAtom) {
            breakAtoms(sourceAtom, destinationAtom, numOfConversions);
        } else if (sourceAtom < destinationAtom) {
            blendAtoms(sourceAtom, destinationAtom, numOfConversions);
        }
        blenderListener.onBlend();
    }

    public void blendAtoms(int sourceAtom, int destinationAtom, int numOfConversions) {
        boolean canBlend;
        for (int i = 0; i < numOfConversions; i++) {
            canBlend = projectileContainer.decreaseAtoms(sourceAtom, GameConstants.BLENDING_MATRIX[sourceAtom][destinationAtom]);
            if (!canBlend)
                break;
            projectileContainer.increaseAtoms(destinationAtom, 1, null);
        }
    }

    private void breakAtoms(int sourceAtom, int destinationAtom, int numOfConversions) {
        boolean canBlend;
        for (int i = 0; i < numOfConversions; i++) {
            canBlend = projectileContainer.decreaseAtoms(sourceAtom, 1);
            if (!canBlend)
                break;
            projectileContainer.increaseAtoms(destinationAtom, GameConstants.BLENDING_MATRIX[sourceAtom][destinationAtom], null);
        }
    }

    public void showBlender() {
        blenderListener.onShow();
    }

    public void setBlenderListener(BlenderListener blenderListener) {
        this.blenderListener = blenderListener;
    }

    public void setProjectileContainer(ProjectileContainer projectileContainer) {
        this.projectileContainer = projectileContainer;
    }

    public interface BlenderListener {
        /**
         * this method is called after game parameters get checked and proved valid.
         */
        default void onBlend() {
            logger.warning("Listener is not yet initialized");
        }

        default void onFailBlend() {
            logger.warning("Listener is not yet initialized");
        }

        default void onShow() {
            logger.warning("Listener is not yet initialized");
        }
    }
}
