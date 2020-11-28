package ui.movable_drawables;

import model.game_entities.*;
import model.game_entities.enums.*;
import model.game_physics.path_patterns.StraightPattern;
import model.game_physics.path_patterns.ZigzagPatten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Coordinates;
import utils.Velocity;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

class DrawerTest {


    JFrame frame;

    @BeforeEach
    void setUp() {
        frame = new JFrame();
        frame.setSize(1200, 1200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    Coordinates randCord(int a, int b) {
        return new Coordinates(new Random().nextInt(a), new Random().nextInt(b));
    }

    int rand(int a) {
        return new Random().nextInt(a);
    }

    @Test
    void AtomDrawerTest() throws InterruptedException {

        int y = 10;
        StraightPattern path = new StraightPattern(randCord(1199, 49), new Velocity(0, y * 0.2));
        ZigzagPatten path2 = new ZigzagPatten(randCord(1200, 50), new Velocity(0, y * 0.4), 100);
        StraightPattern path3 = new StraightPattern(randCord(1200, 50), new Velocity(0, y * 0.8));
        StraightPattern path4 = new StraightPattern(randCord(1200, 50), new Velocity(0, y * 1.1));

        Atom atom = new Atom(new Coordinates(rand(1200), rand(1201)), null, path, AtomType.ALPHA);
        Atom atom2 = new Atom(new Coordinates(rand(1200), rand(1200)), null, path2, AtomType.BETA);
        Atom atom3 = new Atom(new Coordinates(rand(1200), rand(1200)), null, path3, AtomType.SIGMA);
        Atom atom4 = new Atom(new Coordinates(rand(1200), rand(1200)), null, path4, AtomType.GAMMA);

        Drawable d1 = DrawableFactory.get(atom, EntityType.ATOM);
        Drawable d2 = DrawableFactory.get(atom2, EntityType.ATOM);
        Drawable d3 = DrawableFactory.get(atom3, EntityType.ATOM);
        Drawable d4 = DrawableFactory.get(atom4, EntityType.ATOM);

        Timer t = new Timer(15, e -> {
            atom.move();
            atom2.move();
            atom3.move();
            atom4.move();

            Graphics g = frame.getGraphics();
            d1.draw(g);
            d2.draw(g);
            d3.draw(g);
            d4.draw(g);

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
    void MoleculeDrawerTest() throws InterruptedException {

        int y = 10;
        StraightPattern path = new StraightPattern(randCord(1199, 49), new Velocity(0, y * 0.2));
        StraightPattern path2 = new StraightPattern(randCord(1200, 50), new Velocity(0, y * 0.4));
        StraightPattern path3 = new StraightPattern(randCord(1200, 50), new Velocity(0, y * 0.8));
        StraightPattern path4 = new StraightPattern(randCord(1200, 50), new Velocity(0, y * 1.1));

        Molecule molecule = new Molecule(new Coordinates(rand(1200), rand(1201)), null, path, MoleculeType.ALPHA_, null);
        Molecule molecule2 = new Molecule(new Coordinates(rand(1200), rand(1200)), null, path2, MoleculeType.BETA_, null);
        Molecule molecule3 = new Molecule(new Coordinates(rand(1200), rand(1200)), null, path3, MoleculeType.SIGMA_, null);
        Molecule molecule4 = new Molecule(new Coordinates(rand(1200), rand(1200)), null, path4, MoleculeType.GAMMA_, null);

        Drawable d1 = DrawableFactory.get(molecule, EntityType.MOLECULE);
        Drawable d2 = DrawableFactory.get(molecule2, EntityType.MOLECULE);
        Drawable d3 = DrawableFactory.get(molecule3, EntityType.MOLECULE);
        Drawable d4 = DrawableFactory.get(molecule4, EntityType.MOLECULE);

        Timer t = new Timer(15, e -> {
            molecule.move();
            molecule2.move();
            molecule3.move();
            molecule4.move();

            Graphics g = frame.getGraphics();
            d1.draw(g);
            d2.draw(g);
            d3.draw(g);
            d4.draw(g);

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
    void PowerupDrawerTest() throws InterruptedException {

        int y = 10;
        StraightPattern path = new StraightPattern(randCord(1199, 49), new Velocity(0, y * 0.2));
        StraightPattern path2 = new StraightPattern(randCord(1200, 50), new Velocity(0, y * 0.4));
        StraightPattern path3 = new StraightPattern(randCord(1200, 50), new Velocity(0, y * 0.8));
        StraightPattern path4 = new StraightPattern(randCord(1200, 50), new Velocity(0, y * 1.1));

        Powerup Powerupe = new Powerup(new Coordinates(rand(1200), rand(1201)), null, path, PowerupType._ALPHA_B);
        Powerup Powerupe2 = new Powerup(new Coordinates(rand(1200), rand(1200)), null, path2, PowerupType._BETA_B);
        Powerup Powerupe3 = new Powerup(new Coordinates(rand(1200), rand(1200)), null, path3, PowerupType._SIGMA_B);
        Powerup Powerupe4 = new Powerup(new Coordinates(rand(1200), rand(1200)), null, path4, PowerupType._GAMMA_B);

        Drawable d1 = DrawableFactory.get(Powerupe, EntityType.POWERUP);
        Drawable d2 = DrawableFactory.get(Powerupe2, EntityType.POWERUP);
        Drawable d3 = DrawableFactory.get(Powerupe3, EntityType.POWERUP);
        Drawable d4 = DrawableFactory.get(Powerupe4, EntityType.POWERUP);

        Timer t = new Timer(15, e -> {
            Powerupe.move();
            Powerupe2.move();
            Powerupe3.move();
            Powerupe4.move();

            Graphics g = frame.getGraphics();
            d1.draw(g);
            d2.draw(g);
            d3.draw(g);
            d4.draw(g);

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
    void BlockerDrawerTest() throws InterruptedException {

        int y = 10;
        StraightPattern path = new StraightPattern(randCord(1199, 49), new Velocity(0, y * 0.2));
        StraightPattern path2 = new StraightPattern(randCord(1200, 50), new Velocity(0, y * 0.4));
        StraightPattern path3 = new StraightPattern(randCord(1200, 50), new Velocity(0, y * 0.8));
        StraightPattern path4 = new StraightPattern(randCord(1200, 50), new Velocity(0, y * 1.1));

        Blocker blocker = new Blocker(new Coordinates(rand(1200), rand(1201)), null, path, BlockerType.ALPHA_B, 0, 0);
        Blocker blocker2 = new Blocker(new Coordinates(rand(1200), rand(1200)), null, path2, BlockerType.BETA_B, 0, 0);
        Blocker blocker3 = new Blocker(new Coordinates(rand(1200), rand(1200)), null, path3, BlockerType.SIGMA_B, 0, 0);
        Blocker blocker4 = new Blocker(new Coordinates(rand(1200), rand(1200)), null, path4, BlockerType.GAMMA_B, 0, 0);

        Drawable d1 = DrawableFactory.get(blocker, EntityType.BLOCKER);
        Drawable d2 = DrawableFactory.get(blocker2, EntityType.BLOCKER);
        Drawable d3 = DrawableFactory.get(blocker3, EntityType.BLOCKER);
        Drawable d4 = DrawableFactory.get(blocker4, EntityType.BLOCKER);

        Timer t = new Timer(15, e -> {
            blocker.move();
            blocker2.move();
            blocker3.move();
            blocker4.move();

            Graphics g = frame.getGraphics();
            d1.draw(g);
            d2.draw(g);
            d3.draw(g);
            d4.draw(g);

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
    void ShooterDrawerTest() throws InterruptedException {
        int y = 10;
        StraightPattern path = new StraightPattern(randCord(1199, 49), new Velocity(5, 5));

        Shooter shooter = new Shooter(new Coordinates(rand(1200), rand(1201)), null, path);

        Drawable d1 = DrawableFactory.get(shooter, EntityType.SHOOTER);

        Timer t = new Timer(15, e -> {
            shooter.move();

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