package ui.windows;

import model.game_running.Blender;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class BlenderWindow extends JFrame implements Blender.BlenderListener {
    private JPanel contentPane;
    private JLabel sourceLabel;
    private JComboBox sourceComboBox;
    private JLabel destinationLabel;
    private JComboBox destinationComboBox;
    private JButton blendButton;

    Map<String, Integer> atomTypesWeights; // This map contains a mapping between atom types and their weights (1 to 4)
    Blender blender;
    public BlenderWindow(Blender blender) {
        super("blender");
        this.blender = blender;
        blender.setBlenderListener(this); // Pass this listener to Blender for the observer pattern
        this.atomTypesWeights = new HashMap<>();
        this.contentPane = new JPanel();

        getContentPane().add(contentPane);
        addComponents(contentPane); // Add components to the panel
        this.pack(); // Pack the frame around the components
        this.setVisible(false); // Keep it invisible by default
    }

    private void addComponents(JPanel contentPane) {
        this.atomTypesWeights.put("Alpha", 1);
        this.atomTypesWeights.put("Beta", 2);
        this.atomTypesWeights.put("Gamma", 3);
        this.atomTypesWeights.put("Sigma", 4);

        sourceLabel = new JLabel("Source");
        sourceComboBox = new JComboBox((atomTypesWeights.keySet().toArray()));
        destinationLabel = new JLabel("Destination");
        destinationComboBox = new JComboBox(atomTypesWeights.keySet().toArray());

        blendButton = new JButton("Blend");
        addButtonActionListener(blendButton);

        contentPane.add(sourceLabel);
        contentPane.add(sourceComboBox);
        contentPane.add(destinationLabel);
        contentPane.add(destinationComboBox);
        contentPane.add(blendButton);
    }

    public Blender getBlender(){
        return this.blender;
    }

    private void addButtonActionListener(JButton btn) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String source = String.valueOf(sourceComboBox.getSelectedItem());
                String destination = String.valueOf(sourceComboBox.getSelectedItem());
                int sourceWeight = atomTypesWeights.get(source);
                int destinationWeight = atomTypesWeights.get(destination);
                System.out.println(blender);
                blender.blend(sourceWeight, destinationWeight);
            }
        });
    }

    @Override
    public void onBlend() {
       this.setVisible(false);
       // this.dispose();
    }

    @Override
    public void onShow() {
        this.setVisible(true);
    }
}

