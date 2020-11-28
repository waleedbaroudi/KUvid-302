package ui.windows;

import model.game_building.BuildingMode;
import model.game_building.ConfigBundle;
import model.game_running.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * This class draws the game building window. through this window, the player
 * can specify game parameters.
 */
public class BuildingWindow extends JFrame implements BuildingMode.ParametersValidationListener {
    ConfigBundle bundle;
    BuildingMode buildingMode;

    // JTextFields
    JTextField gammaAtomsTextField;
    JTextField alphaAtomsTextField;
    JTextField betaAtomsTextField;
    JTextField sigmaAtomsTextField;
    JTextField moleculesTextField;
    JTextField powerupsTextField;
    JTextField blockersTextField;
    JTextField lengthTextField;

    // JCheckBoxes
    JCheckBox isLinearAlpha;
    JCheckBox isLinearBeta;
    JCheckBox isSpinningBeta;
    JCheckBox isSpinningAlpha;

    String[] difficultyLevels = {"Easy", "Medium", "Hard"};
    JComboBox<String> difficultyBox;
    ArrayList<Integer> atoms = new ArrayList<Integer>();

    // Configuration variables
    int alphaatomsNum, betaatomsNum, sigmaatomsNum, gammaatomsNum, moleculesNum, blockersNum, powerupsNum;
    double l;

    /**
     * Constructor initiates the Scanner and BuildingMode instances
     */
    public BuildingWindow(String title) {
        super(title);
        this.setSize(GameConstants.BUILDING_WINDOW_SIZE);
        this.buildingMode = new BuildingMode(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        this.add(panel);


        /*
         * calling user defined method for adding components to the panel.
         */
        placeComponents(panel);

        // Fits the borders to the content
        this.pack();
        // Setting the frame visibility to true.
        this.setVisible(true);
    }

    /**
     * Here we place our components to the panel that will be added to the JFrame
     * after.
     *
     * @param panel
     */
    private void placeComponents(JPanel panel) {
        // Setting the layout of the panel
        panel.setLayout(new GridLayout(12, 2));
        panel.setBorder(BorderFactory.createTitledBorder("Building Window"));
        // GridBagConstraints c = new GridBagConstraints();

        /*
         * Creating labels and text fields
         */
        JLabel alphaLabel = new JLabel("Alpha Atoms");
        panel.add(alphaLabel);

        alphaAtomsTextField = new JTextField(4);
        alphaAtomsTextField.setText("5");
        panel.add(alphaAtomsTextField);

        JLabel betaLabel = new JLabel("Beta Atoms");
        panel.add(betaLabel);

        betaAtomsTextField = new JTextField(4);
        betaAtomsTextField.setText("5");
        panel.add(betaAtomsTextField);

        JLabel sigmaLabel = new JLabel("Sigma Atoms");
        panel.add(sigmaLabel);

        sigmaAtomsTextField = new JTextField(4);
        sigmaAtomsTextField.setText("5");
        panel.add(sigmaAtomsTextField);

        JLabel gammaLabel = new JLabel("Gamma Atoms");
        panel.add(gammaLabel);

        gammaAtomsTextField = new JTextField(4);
        gammaAtomsTextField.setText("5");
        panel.add(gammaAtomsTextField);

        JLabel moleculesLabel = new JLabel("Molecules");
        panel.add(moleculesLabel);

        moleculesTextField = new JTextField(4);
        moleculesTextField.setText("5");
        panel.add(moleculesTextField);

        JLabel powerupsLabel = new JLabel("Power-ups");
        panel.add(powerupsLabel);

        powerupsTextField = new JTextField(4);
        powerupsTextField.setText("5");
        panel.add(powerupsTextField);

        JLabel blockersLabel = new JLabel("Blockers");
        panel.add(blockersLabel);

        blockersTextField = new JTextField(4);
        blockersTextField.setText("5");
        panel.add(blockersTextField);

        JLabel lengthLabel = new JLabel("L unit ");
        panel.add(lengthLabel);

        lengthTextField = new JTextField(4);
        lengthTextField.setText("90");
        panel.add(lengthTextField);

        JLabel difficultyLabel = new JLabel("Difficulty ");
        panel.add(difficultyLabel);

        difficultyBox = new JComboBox<String>(difficultyLevels);
        difficultyBox.setSelectedIndex(1);
        panel.add(difficultyBox);

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
         */
        JButton buildGameButton = new JButton("Build Game!");
        addButtonActionListener(buildGameButton);
        panel.add(buildGameButton);

    }

    // need to try an catch exceptions ... etc.
    private void addButtonActionListener(JButton btn) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create bundle
                try {
                    getParametersValues();
                    bundle = new ConfigBundle(atoms, blockersNum, powerupsNum, moleculesNum, l,
                            isLinearAlpha.isSelected(), isLinearBeta.isSelected(), isSpinningAlpha.isSelected(),
                            isSpinningBeta.isSelected(), difficultyBox.getSelectedIndex());
                    // Validate the fields.
                    buildingMode.validateParameters(bundle);

                } catch (NumberFormatException ex) {
                    ArrayList<String> error = new ArrayList<>();
                    error.add("One of the parameter has invalid format! .. recheck");
                    onInvalidParameters(error);
                }

            }
        });
    }

    /**
     * Sets the behavior for the checkbox to disable the corresponding spinning checkbox and un-tick it
     * if the linear Alpha option is un-ticked.
     */
    private void addAlphaCheckboxActionListener() {
        isLinearAlpha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSpinningAlpha.setEnabled(isLinearAlpha.isSelected());
                if (!isLinearAlpha.isSelected())
                    isSpinningAlpha.setSelected(false);
            }
        });
    }

    /**
     * Sets the behavior for the checkbox to disable the corresponding spinning checkbox and un-tick it
     * if the linear Beta option is un-ticked.
     */
    private void addBetaCheckboxActionListener() {
        isLinearBeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSpinningBeta.setEnabled(isLinearBeta.isSelected());
                if (!isLinearBeta.isSelected())
                    isSpinningBeta.setSelected(false);
            }
        });
    }

    private void getParametersValues() throws NumberFormatException {
        this.atoms.clear();
        alphaatomsNum = Integer.parseInt(alphaAtomsTextField.getText());
        atoms.add(alphaatomsNum);
        betaatomsNum = Integer.parseInt(betaAtomsTextField.getText());
        atoms.add(betaatomsNum);
        gammaatomsNum = Integer.parseInt(gammaAtomsTextField.getText());
        atoms.add(gammaatomsNum);
        sigmaatomsNum = Integer.parseInt(sigmaAtomsTextField.getText());
        atoms.add(sigmaatomsNum);
        moleculesNum = Integer.parseInt(moleculesTextField.getText());
        blockersNum = Integer.parseInt(blockersTextField.getText());
        powerupsNum = Integer.parseInt(powerupsTextField.getText());
        l = Double.parseDouble(lengthTextField.getText());
    }

    public void onValidParameters() {
        ConfirmationWindow confirmationWindow = new ConfirmationWindow(BuildingWindow.this, this.bundle);
    }

    public void onInvalidParameters(ArrayList<String> invalidFields) {
        ErrorWindow errorWindow = new ErrorWindow(BuildingWindow.this, invalidFields);
    }

}
