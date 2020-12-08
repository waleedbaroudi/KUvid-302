package model.game_running;

import model.game_entities.Atom;
import java.util.ArrayList;
import java.util.Arrays;

public class Blender {

    private ArrayList<Atom> container;

    public Blender(ArrayList<Atom> container){
        this.container = container;
    }


    public void blendAtom(Atom blendAtom, Atom resultAtom){

    }

    public void breakAtom(Atom blendAtom, Atom resultAtom){

    }

    private void addAtomsToContainer(Atom atom){
        this.container.add(atom);
    }
    private void addAtomsToContainer(Atom[] atom){
        this.container.addAll(Arrays.asList(atom));
    }

    public interface BlenderListener{
        /**
         * this method is called after game parameters get checked and proved valid.
         */
        void onBlend();
    }
}
