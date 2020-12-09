package model.game_running;

import model.game_entities.Atom;
import utils.MathUtils;


public class Blender {
    private final int ALPHA = 1, BETA = 2, GAMMA = 3, SIGMA = 4;


    private BlenderListener blenderListener;

    public Blender(BlenderListener blenderListener){
        // get the container through a static method since the runningMode will be a singleton
        // this.container = container;
        this.blenderListener = blenderListener;
    }


    public void blend(int sourceAtom, int destinationAtom){

        blenderListener.onBlend();
    }

    public void showBlender(){
        blenderListener.onShow();
    }

    public void breakAtom(Atom blendAtom, Atom resultAtom){

    }

    private void addAtomsToContainer(Atom atom){
       // this.container.add(atom);
    }
    private void addAtomsToContainer(Atom[] atom){
      //  this.container.addAll(Arrays.asList(atom));
    }

    /**
     * Given the indices of the source and target atoms, returns the number of source atoms needed for the blend.
     *
     * @param sourceIndex the index of the source atom.
     * @param targetIndex the index of the target atom.
     * @return the number of source atoms needed for the blend.
     */
    private double getBlendCoefficient(int sourceIndex, int targetIndex){
        double[][] defaultMatrix = MathUtils.blendMatrix(new int[]{ALPHA, BETA, GAMMA, SIGMA}, 4);
        return defaultMatrix[sourceIndex][targetIndex];
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
