package model.game_space;


import model.game_building.GameConstants;
import model.game_running.ProjectileContainer;



public class Blender {

    private final ProjectileContainer projectileContainer;
    private BlenderListener blenderListener;

    public Blender(BlenderListener blenderListener, ProjectileContainer projectileContainer) {
        this.projectileContainer = projectileContainer;
        this.blenderListener = blenderListener;
    }

    public void blend(int sourceAtom, int destinationAtom) { // TODO: Delete this method after implementing multiple blending of a certain atom.
        boolean canBlend = false;
        System.out.println(canBlend = projectileContainer.decreaseAtoms(sourceAtom, (int) Math.ceil(sourceAtom *
                GameConstants.BLENDING_MATRIX[sourceAtom - 1][destinationAtom - 1])));
        if (canBlend)
            System.out.println(projectileContainer.increaseAtoms(destinationAtom, (int) Math.ceil(destinationAtom *
                    GameConstants.BLENDING_MATRIX[destinationAtom - 1][sourceAtom - 1])));
        System.out.println(projectileContainer.toString());

        blenderListener.onBlend();
    }

    public void blend(int sourceAtom, int destinationAtom, int destinationAtomQuantity) {// TODO: Use this method to blend after implementing multiple blending of a certain atom.
        boolean canBlend;
        canBlend = projectileContainer.decreaseAtoms(sourceAtom, destinationAtomQuantity *
                (int) Math.ceil(sourceAtom * GameConstants.BLENDING_MATRIX[sourceAtom - 1][destinationAtom - 1]));
        if (canBlend) {
            projectileContainer.increaseAtoms(destinationAtom, destinationAtomQuantity *
                    (int) Math.ceil(destinationAtom * GameConstants.BLENDING_MATRIX[destinationAtom - 1][sourceAtom - 1]));
            blenderListener.onBlend();
        } else {
            blenderListener.onFailBlend();
        }
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
        void onFailBlend();
        void onShow();
    }
}
