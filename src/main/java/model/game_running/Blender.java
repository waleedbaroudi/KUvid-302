package model.game_running;

import model.game_entities.Atom;
import java.util.ArrayList;
import java.util.Arrays;

public class Blender {

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

    public interface BlenderListener{
        /**
         * this method is called after game parameters get checked and proved valid.
         */
        void onBlend();
        void onShow();
    }
}
