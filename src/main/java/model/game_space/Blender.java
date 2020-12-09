package model.game_space;

import model.game_entities.Atom;
import model.game_running.ProjectileContainer;
import utils.MathUtils;


public class Blender {
    private final double[][] BLENDING_MATRIX = {{1,2,3,4}, {.5, 1, 1 ,1.5}, {0.333, 0.333, 1, 0.666}, {.25,.25,.25,1}}; // TODO: Change to something cleaner.
    private ProjectileContainer projectileBlender;

    private BlenderListener blenderListener;

    public Blender(BlenderListener blenderListener, ProjectileContainer projectileBlender){
        // get the container through a static method since the runningMode will be a singleton
        // this.container = container;
        this.projectileBlender = projectileBlender;
        this.blenderListener = blenderListener;
   }

    public void blend(int sourceAtom, int destinationAtom){
        projectileBlender.decreaseAtoms(sourceAtom,(int) Math.ceil(sourceAtom * BLENDING_MATRIX[sourceAtom - 1][destinationAtom - 1]));
        projectileBlender.increaseAtoms(destinationAtom, (int) Math.ceil(sourceAtom * BLENDING_MATRIX[destinationAtom - 1][sourceAtom - 1]));

        blenderListener.onBlend();
    }

    public void showBlender(){
        blenderListener.onShow();
    }

    public void setBlenderListener(BlenderListener blenderListener){
        this.blenderListener = blenderListener;
    }

    public interface BlenderListener{
        /**
         * this method is called after game parameters get checked and proved valid.
         */
        void onBlend();
        void onShow();
    }
}
