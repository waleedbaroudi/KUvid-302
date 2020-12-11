package ui.windows;

import model.game_running.GameConstants;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.StrokeBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class testClass {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(800, 800));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        StatisticsWindow s = new StatisticsWindow();
        StatisticsWindow a2s = new StatisticsWindow();
        frame.setLayout(new BorderLayout());
        frame.getContentPane().add(s, BorderLayout.LINE_START);
        JSeparator sep = new JSeparator(JSeparator.VERTICAL);
        sep.setBorder(new StrokeBorder(new BasicStroke(5)));
        frame.getContentPane().add(sep, BorderLayout.CENTER);
        frame.getContentPane().add(a2s, BorderLayout.LINE_END);
        frame.pack();
        frame.setVisible(true);
    }
}
