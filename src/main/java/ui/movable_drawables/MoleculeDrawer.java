package ui.movable_drawables;

import model.game_entities.Molecule;
import model.game_entities.enums.MoleculeType;
import sun.security.provider.MD2;

import java.awt.*;

public class MoleculeDrawer implements Drawable {

    private Molecule molecule;

    public MoleculeDrawer(Molecule molecule){
        this.molecule = molecule;
    }

    @Override
    public void draw(Graphics g) {

    }
}
