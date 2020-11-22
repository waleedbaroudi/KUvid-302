package ui.windows;

import com.sun.javaws.util.JfxHelper;

import javax.swing.*;

public class ErrorWindow{


    public ErrorWindow(JFrame frame, String errorMessage){
        displayError(frame, errorMessage);
    }

    private void displayError(JFrame frame, String errorMessage){
        JOptionPane.showMessageDialog(frame, errorMessage,"Enter Valid Fields:", JOptionPane.ERROR_MESSAGE);
    }
}
