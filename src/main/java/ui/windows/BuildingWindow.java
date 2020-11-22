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

    // JRadioButtons
    JRadioButton isLinearAlpha;
    JRadioButton isSpinningAlpha;
    JRadioButton isLinearBeta;
    JRadioButton isSpinningBeta;

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
        panel.setLayout(new GridBagLayout());
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
         * Radio Button Groups
         * */

        ButtonGroup alphaMoleculesGroup = new ButtonGroup();
        ButtonGroup betaMoleculesGroup = new ButtonGroup();

        isLinearAlpha = new JRadioButton("Linear Alpha Molecules");
        alphaMoleculesGroup.add(isLinearAlpha);
        panel.add(isLinearAlpha);

        isSpinningAlpha = new JRadioButton("Spinning Alpha Molecules");
        alphaMoleculesGroup.add(isSpinningAlpha);
        panel.add(isSpinningAlpha);

        isLinearBeta = new JRadioButton("Linear Beta Molecules");
        betaMoleculesGroup.add(isLinearBeta);
        panel.add(isLinearBeta);

        isSpinningBeta = new JRadioButton("Spinning Beta Molecules");
        betaMoleculesGroup.add(isSpinningBeta);
        panel.add(isSpinningBeta);

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

    public void onValidParameters() {
        // TODO: Pass the bundle to the confirmation window to have a little summary on the window.
        ConfirmationWindow confirmationWindow = new ConfirmationWindow(BuildingWindow.this, this.bundle);
    }

    public void onInvalidParameters(String message) {
        ErrorWindow errorWindow = new ErrorWindow(BuildingWindow.this, message);
    }
}
