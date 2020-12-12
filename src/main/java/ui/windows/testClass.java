package ui.windows;

import javax.swing.*;
import javax.swing.border.StrokeBorder;
import java.awt.*;

public class testClass {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(800, 800));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        StatisticsPanel s = new StatisticsPanel(this.runningMode); //TODO: StatisticsPanel constructor changed. edit accordingly.
//        StatisticsPanel a2s = new StatisticsPanel(this.runningMode); //TODO: StatisticsPanel constructor changed. edit accordingly.
        frame.setLayout(new BorderLayout());
//        frame.getContentPane().add(s, BorderLayout.LINE_START);//TODO: lines 12, 13 changed. edit accordingly.
        JSeparator sep = new JSeparator(JSeparator.VERTICAL);
        sep.setBorder(new StrokeBorder(new BasicStroke(5)));
        frame.getContentPane().add(sep, BorderLayout.CENTER);
//        frame.getContentPane().add(a2s, BorderLayout.LINE_END); //TODO: lines 12, 13 changed. edit accordingly.
        frame.pack();
        frame.setVisible(true);
    }
}
