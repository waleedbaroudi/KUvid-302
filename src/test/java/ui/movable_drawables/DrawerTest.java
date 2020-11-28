//package ui.movable_drawables;
//
//import model.game_entities.Atom;
//import model.game_entities.enums.AtomType;
//import model.game_entities.enums.EntityType;
//import model.game_physics.hitbox.Hitbox;
//import model.game_physics.path_patterns.PathPattern;
//import model.game_physics.path_patterns.StraightPattern;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import utils.Coordinates;
//import utils.Velocity;
//
//import javax.swing.*;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.Random;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class DrawerTest {
//
//    JFrame frame;
//    JPanel panel;
//    CardLayout cardLayout;
//
//    @BeforeEach
//    void setUp() {
//        frame = new JFrame();
//
//        panel = new JPanel();
//        cardLayout = new CardLayout();
//        panel.setLayout(cardLayout);
//
//        cardLayout.show(panel, "NAME");
//        frame.setFocusable(true);
//        frame.setTitle("KUVID");
//        frame.add(panel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setBackground(new Color(12, 24, 60));
//        frame.setSize(800, 800);
//        frame.setVisible(true);
//        frame.setFocusable(true);
//    }
//
//    @Test
//    void AtomDrawerTest() throws InterruptedException {
//
//        frame.setVisible(true);
//
//        StraightPattern path = new StraightPattern(new Coordinates(new Random().nextInt(50), new Random().nextInt(50)), new Velocity(1, 2));
//        StraightPattern path2 = new StraightPattern(new Coordinates(new Random().nextInt(50), new Random().nextInt(50)), new Velocity(2, 1));
//        StraightPattern path3 = new StraightPattern(new Coordinates(new Random().nextInt(50), new Random().nextInt(50)), new Velocity(2, 2));
//        StraightPattern path4 = new StraightPattern(new Coordinates(new Random().nextInt(50), new Random().nextInt(50)), new Velocity(1, 1));
//
//        Atom atom = new Atom(new Coordinates(new Random().nextInt(800), new Random().nextInt(800)), null, path, AtomType.ALPHA);
//        Atom atom2 = new Atom(new Coordinates(new Random().nextInt(800), new Random().nextInt(800)), null, path2, AtomType.BETA);
//        Atom atom3 = new Atom(new Coordinates(new Random().nextInt(800), new Random().nextInt(800)), null, path3, AtomType.SIGMA);
//        Atom atom4 = new Atom(new Coordinates(new Random().nextInt(800), new Random().nextInt(800)), null, path4, AtomType.GAMMA);
//
//        frame.repaint();
//
//        while (true) {
//            Drawable d1 = DrawableFactory.get(atom, EntityType.ATOM);
//            Drawable d2 = DrawableFactory.get(atom2, EntityType.ATOM);
//            Drawable d3 = DrawableFactory.get(atom3, EntityType.ATOM);
//            Drawable d4 = DrawableFactory.get(atom4, EntityType.ATOM);
//
//            d1.draw(frame.getGraphics());
//            d2.draw(frame.getGraphics());
//            d3.draw(frame.getGraphics());
//            d4.draw(frame.getGraphics());
//
//            atom.move();
//            atom2.move();
//            atom3.move();
//            atom4.move();
//
//            frame.repaint();
//            Thread.sleep(15);
//        }
//    }
//}