package model.game_running;

import model.game_entities.Atom;
import utils.MathUtils;


public class Blender {
    private final int ALPHA = 1, BETA = 2, GAMMA = 3, SIGMA = 4;

    private RunningMode container;

    public Blender(RunningMode container){
        this.container = container;
    }

    public void blendAtom(Atom blendAtom, Atom resultAtom){

    }

    public void breakAtom(Atom blendAtom, Atom resultAtom){

    }

    private void addAtomsToContainer(Atom atom){
       // this.container.add(atom);
    }
    private void addAtomsToContainer(Atom[] atom){
      //  this.container.addAll(Arrays.asList(atom));
    }


    public interface BlenderListener{
        /**
         * this method is called after game parameters get checked and proved valid.
         */
        void onBlend();
    }
}
