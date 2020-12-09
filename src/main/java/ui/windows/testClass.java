package ui.windows;

import model.game_running.GameConstants;

import javax.swing.*;

public class testClass {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(GameConstants.STATISTICS_WINDOW_SIZE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        StatisticsWindow s = new StatisticsWindow();
        frame.getContentPane().add(s);
        frame.setVisible(true);
    }
}
