package ui.movable_drawables;

import model.game_entities.Molecule;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.MoleculeType;
import model.game_physics.path_patterns.StraightPattern;
import model.game_physics.path_patterns.ZigzagPatten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Coordinates;
import utils.Velocity;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PathWithDrawersTest {
    JFrame frame;

    @BeforeEach
    void setUp() {
        frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    int rand(int a) {
        return new Random().nextInt(a);
    }
    Coordinates randCord(int a, int b) {
        return new Coordinates(new Random().nextInt(a), new Random().nextInt(b));
    }

    @Test
    void StraitPathTest() throws InterruptedException {
        StraightPattern path = new StraightPattern(randCord(1199, 49), new Velocity(5, 3));
        Molecule molecule = new Molecule(new Coordinates(0, 0), null, path, MoleculeType.BETA_, null);
        Drawable d1 = DrawableFactory.get(molecule, EntityType.MOLECULE);
        Timer t = new Timer(15, e -> {
            molecule.move();
            Graphics g = frame.getGraphics();
            d1.draw(g);
            try {
                frame.repaint();
                Thread.sleep(50);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });

        t.start();

        Thread.sleep(15000);
        frame.removeAll();
    }


    @Test
    void ZigzagPathTest() throws InterruptedException {
        ZigzagPatten path = new ZigzagPatten(new Coordinates(100,0), new Velocity(5, 3), 15);
        Molecule molecule = new Molecule(new Coordinates(0, 0), null, path, MoleculeType.BETA_, null);
        Drawable d1 = DrawableFactory.get(molecule, EntityType.MOLECULE);
        Timer t = new Timer(15, e -> {
            molecule.move();
            Graphics g = frame.getGraphics();
            d1.draw(g);
            try {
                frame.repaint();
                Thread.sleep(50);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });

        t.start();

        Thread.sleep(15000);
        frame.removeAll();
    }


}
