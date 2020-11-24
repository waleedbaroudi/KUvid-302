package ui.windows;

import model.game_building.BuildingMode;
import model.game_building.ConfigBundle;
import sun.awt.image.BufferedImageDevice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class draws the game building window.
 * through this window, the player can specify game parameters.
 */
public class BuildingWindow extends JFrame implements BuildingMode.ParametersValidationListener {
    ConfigBundle bundle;
    BuildingMode buildingMode;

    // JTextFields
    JTextField atomsTextField;
    JTextField moleculesTextField;
    JTextField powerupsTextField;
    JTextField blockersTextField;
    JTextField lengthTextField;
    JTextField difficultyTextField;

    // JCheckBoxes
    JCheckBox isLinearAlpha;
    JCheckBox isLinearBeta;
    JCheckBox isSpinningBeta;
    JCheckBox isSpinningAlpha;

    // Configuration variables
    int atomsNum, moleculesNum, blockersNum, powerupsNum, difficulty;
    double l;
    boolean isLinearA, isLinearB, isSpinningA, isSpinningB;

    /**
     * Constructor initiates the Scanner and BuildingMode instances
     */
    public BuildingWindow(String title) {
        buildingMode = new BuildingMode(this);

        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        this.add(panel);

        /* calling user defined method for adding components
         * to the panel.
         */
        placeComponents(panel);

        // Fits the borders to the content
        this.pack();
        // Setting the frame visibility to true.
        this.setVisible(true);
    }

    /**
     * Here we place our components to the panel that will be added to the JFrame after.
     * @param panel
     */
    private void placeComponents(JPanel panel){
        // Setting the layout of the panel
        panel.setLayout(new GridLayout(9, 2));
        panel.setBorder(BorderFactory.createTitledBorder("Building Window"));
        //GridBagConstraints c = new GridBagConstraints();

        /*
         * Creating labels and text fields
         * */
        JLabel atomsLabel = new JLabel("Atoms");
        panel.add(atomsLabel);

        atomsTextField = new JTextField(4);
        panel.add(atomsTextField);

        JLabel moleculesLabel = new JLabel("Molecules");
        panel.add(moleculesLabel);

        moleculesTextField = new JTextField(4);
        panel.add(moleculesTextField);

        JLabel powerupsLabel = new JLabel("Power-ups");
        panel.add(powerupsLabel);

        powerupsTextField = new JTextField(4);
        panel.add(powerupsTextField);

        JLabel blockersLabel = new JLabel("Blockers");
        panel.add(blockersLabel);

        blockersTextField = new JTextField(4);
        panel.add(blockersTextField);

        JLabel lengthLabel = new JLabel("L unit");
        panel.add(lengthLabel);

        lengthTextField = new JTextField(4);
        panel.add(lengthTextField);

        JLabel difficultyLabel = new JLabel("Difficulty (0-2)");
        panel.add(difficultyLabel);

        difficultyTextField = new JTextField(4);
        panel.add(difficultyTextField);

        /*
        * Checkboxes
        * */
        isLinearAlpha = new JCheckBox("Spinning Alpha Molecules");
        panel.add(isLinearAlpha);

        isSpinningAlpha = new JCheckBox("Spinning Alpha Molecules");
        isSpinningAlpha.setEnabled(false);
        panel.add(isSpinningAlpha);


        isLinearBeta = new JCheckBox("Spinning Alpha Molecules");
        panel.add(isLinearBeta);

        isSpinningBeta = new JCheckBox("Spinning Beta Molecules");
        isSpinningBeta.setEnabled(false);
        panel.add(isSpinningBeta);

        addAlphaCheckboxActionListener();
        addBetaCheckboxActionListener();

        /*
         * Building Game Button
         * */

        JButton buildGameButton = new JButton("Build Game!");
        addButtonActionListener(buildGameButton);
        panel.add(buildGameButton);

    }

    private void addButtonActionListener(JButton btn){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create bundle
                // TODO: ADD TRY AND CATCH TO CONFIRM THESE PARAMETERS HAVE THE CORRECT TYPE
//                try{
//                    confirmParameters(...)
//                } catch(NumberFormatException){
//                    // Create error window
//                    System.out.println("Please enter correct types");
//                }
                bundle = new ConfigBundle(Integer.parseInt(atomsTextField.getText()), Integer.parseInt(blockersTextField.getText()), Integer.parseInt(powerupsTextField.getText()),
                        Integer.parseInt(moleculesTextField.getText()), Double.parseDouble(lengthTextField.getText()), isLinearAlpha.isSelected(),
                        isLinearBeta.isSelected(), isSpinningAlpha.isSelected(), isSpinningBeta.isSelected(), Integer.parseInt(difficultyTextField.getText())
                );
                // Validate the fields.
                buildingMode.validateParameters(bundle);
            }
        });
    }

    /**
     * Sets the behavior for the checkbox to disable the corresponding spinning checkbox and un-tick it
     * if the linear Alpha option is un-ticked.
     */
    private void addAlphaCheckboxActionListener(){
        isLinearAlpha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSpinningAlpha.setEnabled(isLinearAlpha.isSelected());
                if(!isLinearAlpha.isSelected())
                    isSpinningAlpha.setSelected(false);
            }
        });
    }

    /**
     *  Sets the behavior for the checkbox to disable the corresponding spinning checkbox and un-tick it
     *  if the linear Beta option is un-ticked.
     */
    private void addBetaCheckboxActionListener(){
        isLinearBeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSpinningBeta.setEnabled(isLinearBeta.isSelected());
                if(!isLinearBeta.isSelected())
                    isSpinningBeta.setSelected(false);
            }
        });
    }

    public void onValidParameters() {
        // TODO: Pass the bundle to the confirmation window to have a little summary on the window.
        ConfirmationWindow confirmationWindow = new ConfirmationWindow(BuildingWindow.this, this.bundle);
    }

    public void onInvalidParameters(String message) {
        ErrorWindow errorWindow = new ErrorWindow(BuildingWindow.this, message);
    }
}
