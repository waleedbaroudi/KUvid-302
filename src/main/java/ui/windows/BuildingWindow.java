package ui.windows;

import model.game_building.BuildingMode;
import model.game_building.ConfigBundle;
import model.game_building.ConfigPreset;
import model.game_building.GameConstants;
import utils.IOHandler;

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
    BuildingMode buildingMode;

    // Atoms JTextFields
    JTextField alphaAtomsTextField;
    JTextField betaAtomsTextField;
    JTextField gammaAtomsTextField;
    JTextField sigmaAtomsTextField;

    // Powerups JTextFields
    JTextField alphaPowerupsTextField;
    JTextField betaPowerupsTextField;
    JTextField gammaPowerupsTextField;
    JTextField sigmaPowerupsTextField;

    // Blockers JTextFields
    JTextField alphaBlockersTextField;
    JTextField betaBlockersTextField;
    JTextField gammaBlockersTextField;
    JTextField sigmaBlockersTextField;

    // Molecules JTextFields
    JTextField alphaMoleculesTextField;
    JTextField betaMoleculesTextField;
    JTextField gammaMoleculesTextField;
    JTextField sigmaMoleculesTextField;

    JTextField lengthTextField;

    // JCheckBoxes
    JCheckBox isLinearAlpha;
    JCheckBox isLinearBeta;
    JCheckBox isSpinningBeta;
    JCheckBox isSpinningAlpha;
    JCheckBox saveConfigPresetCheck;

    String[] difficultyLevels = {"Easy", "Medium", "Hard"};
    JComboBox<String> difficultyBox;
    ArrayList<Integer> atoms, powerups, blockers, molecules;

    double l;

    IOHandler ioHandler;

    /**
     * Constructor initiates the Scanner and BuildingMode instances
     */
    public BuildingWindow(String title) {
        super(title);
        this.atoms = new ArrayList<>();
        this.powerups = new ArrayList<>();
        this.blockers = new ArrayList<>();
        this.molecules = new ArrayList<>();

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
        this.setLocationRelativeTo(null); //centers the window in the middle of the screen
        // Setting the frame visibility to true.
        this.setResizable(false);
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
        panel.setLayout(new GridLayout(12, 4));
        panel.setBorder(BorderFactory.createTitledBorder("Building Window"));
        // GridBagConstraints c = new GridBagConstraints();


        // Atoms labels and text fields
        JLabel alphaAtomLabel = new JLabel("Alpha Atoms");
        panel.add(alphaAtomLabel);

        alphaAtomsTextField = new JTextField(4);
        alphaAtomsTextField.setText("5");
        panel.add(alphaAtomsTextField);

        JLabel betaAtomLabel = new JLabel("Beta Atoms");
        panel.add(betaAtomLabel);

        betaAtomsTextField = new JTextField(4);
        betaAtomsTextField.setText("5");
        panel.add(betaAtomsTextField);

        JLabel gammaAtomLabel = new JLabel("Gamma Atoms");
        panel.add(gammaAtomLabel);

        gammaAtomsTextField = new JTextField(4);
        gammaAtomsTextField.setText("5");
        panel.add(gammaAtomsTextField);

        JLabel sigmaAtomLabel = new JLabel("Sigma Atoms");
        panel.add(sigmaAtomLabel);

        sigmaAtomsTextField = new JTextField(4);
        sigmaAtomsTextField.setText("5");
        panel.add(sigmaAtomsTextField);

        // powerups labels and text fields
        JLabel alphaPowerupLabel = new JLabel("Alpha Powerups");
        panel.add(alphaPowerupLabel);

        alphaPowerupsTextField = new JTextField(4);
        alphaPowerupsTextField.setText("5");
        panel.add(alphaPowerupsTextField);

        JLabel betaPowerupLabel = new JLabel("Beta Powerups");
        panel.add(betaPowerupLabel);

        betaPowerupsTextField = new JTextField(4);
        betaPowerupsTextField.setText("5");
        panel.add(betaPowerupsTextField);

        JLabel gammaPowerupLabel = new JLabel("Gamma Powerups");
        panel.add(gammaPowerupLabel);

        gammaPowerupsTextField = new JTextField(4);
        gammaPowerupsTextField.setText("5");
        panel.add(gammaPowerupsTextField);

        JLabel sigmaPowerupLabel = new JLabel("Sigma Powerups");
        panel.add(sigmaPowerupLabel);

        sigmaPowerupsTextField = new JTextField(4);
        sigmaPowerupsTextField.setText("5");
        panel.add(sigmaPowerupsTextField);


        // Blockers labels and textfields
        JLabel alphaBlockerLabel = new JLabel("Alpha Blockers");
        panel.add(alphaBlockerLabel);

        alphaBlockersTextField = new JTextField(4);
        alphaBlockersTextField.setText("5");
        panel.add(alphaBlockersTextField);

        JLabel betaBlockerLabel = new JLabel("Beta Blockers");
        panel.add(betaBlockerLabel);

        betaBlockersTextField = new JTextField(4);
        betaBlockersTextField.setText("5");
        panel.add(betaBlockersTextField);

        JLabel gammaBlockerLabel = new JLabel("Gamma Blockers");
        panel.add(gammaBlockerLabel);

        gammaBlockersTextField = new JTextField(4);
        gammaBlockersTextField.setText("5");
        panel.add(gammaBlockersTextField);

        JLabel sigmaBlockerLabel = new JLabel("Sigma Blockers");
        panel.add(sigmaBlockerLabel);

        sigmaBlockersTextField = new JTextField(4);
        sigmaBlockersTextField.setText("5");
        panel.add(sigmaBlockersTextField);


        // Molecules labels and textfields
        JLabel alphaMoleculeLabel = new JLabel("Alpha Molecules");
        panel.add(alphaMoleculeLabel);

        alphaMoleculesTextField = new JTextField(4);
        alphaMoleculesTextField.setText("5");
        panel.add(alphaMoleculesTextField);

        JLabel betaMoleculeLabel = new JLabel("Beta Molecules");
        panel.add(betaMoleculeLabel);

        betaMoleculesTextField = new JTextField(4);
        betaMoleculesTextField.setText("5");
        panel.add(betaMoleculesTextField);

        JLabel gammaMoleculeLabel = new JLabel("Gamma Molecules");
        panel.add(gammaMoleculeLabel);

        gammaMoleculesTextField = new JTextField(4);
        gammaMoleculesTextField.setText("5");
        panel.add(gammaMoleculesTextField);

        JLabel sigmaMoleculeLabel = new JLabel("Sigma Molecules");
        panel.add(sigmaMoleculeLabel);

        sigmaMoleculesTextField = new JTextField(4);
        sigmaMoleculesTextField.setText("5");
        panel.add(sigmaMoleculesTextField);

        // Length label and textfield.
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
        isLinearAlpha = new JCheckBox("Linear Alpha Molecules");
        panel.add(isLinearAlpha);

        isSpinningAlpha = new JCheckBox("Spinning Alpha Molecules");
        isSpinningAlpha.setEnabled(false);
        panel.add(isSpinningAlpha);


        isLinearBeta = new JCheckBox("Linear Beta Molecules");
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
        addBuildGameActionListener(buildGameButton);
        panel.add(buildGameButton);

        saveConfigPresetCheck = new JCheckBox("Save Current Config");
        panel.add(saveConfigPresetCheck);

        JButton loadConfigPresetButton = new JButton("Load Preset");
        addLoadConfigActionListener(loadConfigPresetButton);
        panel.add(loadConfigPresetButton);

    }

    private void addBuildGameActionListener(JButton btn) {
        btn.addActionListener(e -> {
            // Create bundle
            try {
                // Validate the fields.
                buildingMode.validateParameters(collectConfigFields());

            } catch (NumberFormatException ex) {
                ArrayList<String> error = new ArrayList<>();
                error.add("One of the parameter has invalid format! .. recheck");
                onInvalidParameters(error);
            }

        });
    }

    private void addLoadConfigActionListener(JButton btn) { // todo: unnecessary, could be fitted into one line
        btn.addActionListener(e -> {
            // Create bundle
            new ConfigPresetWindow(this);
        });
    }


    private ConfigBundle collectConfigFields() {
        /*
        note: this method is only used once now. it will be used again when we make the player choose the saved
        file name.
         */
        getParametersValues();
        return new ConfigBundle(atoms, powerups, blockers, molecules, l,
                isLinearAlpha.isSelected(), isLinearBeta.isSelected(), isSpinningAlpha.isSelected(),
                isSpinningBeta.isSelected(), difficultyBox.getSelectedIndex());
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
        isLinearBeta.addActionListener(e -> {
            isSpinningBeta.setEnabled(isLinearBeta.isSelected());
            if (!isLinearBeta.isSelected())
                isSpinningBeta.setSelected(false);
        });
    }

    private void getParametersValues() throws NumberFormatException {
        // Storing atom types
        this.atoms.clear();
        atoms.add(Integer.parseInt(alphaAtomsTextField.getText()));
        atoms.add(Integer.parseInt(betaAtomsTextField.getText()));
        atoms.add(Integer.parseInt(gammaAtomsTextField.getText()));
        atoms.add(Integer.parseInt(sigmaAtomsTextField.getText()));

        // Storing powerups types
        this.powerups.clear();
        powerups.add(Integer.parseInt(alphaPowerupsTextField.getText()));
        powerups.add(Integer.parseInt(betaPowerupsTextField.getText()));
        powerups.add(Integer.parseInt(gammaPowerupsTextField.getText()));
        powerups.add(Integer.parseInt(sigmaPowerupsTextField.getText()));

        // Storing blockers types
        this.blockers.clear();
        blockers.add(Integer.parseInt(alphaBlockersTextField.getText()));
        blockers.add(Integer.parseInt(betaBlockersTextField.getText()));
        blockers.add(Integer.parseInt(gammaBlockersTextField.getText()));
        blockers.add(Integer.parseInt(sigmaBlockersTextField.getText()));

        // Storing molecules types
        this.molecules.clear();
        molecules.add(Integer.parseInt(alphaMoleculesTextField.getText()));
        molecules.add(Integer.parseInt(betaMoleculesTextField.getText()));
        molecules.add(Integer.parseInt(gammaMoleculesTextField.getText()));
        molecules.add(Integer.parseInt(sigmaMoleculesTextField.getText()));

        l = Double.parseDouble(lengthTextField.getText());
    }

    /**
     * fills the text fields using a preset configuration saved and selected by the player
     *
     * @param bundle the bundle containing the preset configuration.
     */
    public void loadPresetParameters(ConfigBundle bundle) {
        this.alphaAtomsTextField.setText("" + bundle.getNumOfAlphaAtoms());
        this.betaAtomsTextField.setText("" + bundle.getNumOfBetaAtoms());
        this.gammaAtomsTextField.setText("" + bundle.getNumOfGammaAtoms());
        this.sigmaAtomsTextField.setText("" + bundle.getNumOfSigmaAtoms());

        this.alphaBlockersTextField.setText("" + bundle.getNumOfAlphaBlockers());
        this.betaBlockersTextField.setText("" + bundle.getNumOfBetaBlockers());
        this.gammaBlockersTextField.setText("" + bundle.getNumOfGammaBlockers());
        this.sigmaBlockersTextField.setText("" + bundle.getNumOfSigmaBlockers());

        this.alphaPowerupsTextField.setText("" + bundle.getNumOfAlphaPowerups());
        this.betaPowerupsTextField.setText("" + bundle.getNumOfBetaPowerups());
        this.gammaPowerupsTextField.setText("" + bundle.getNumOfGammaPowerups());
        this.sigmaPowerupsTextField.setText("" + bundle.getNumOfSigmaPowerups());

        this.alphaMoleculesTextField.setText("" + bundle.getNumOfAlphaMolecules());
        this.betaMoleculesTextField.setText("" + bundle.getNumOfBetaMolecules());
        this.gammaMoleculesTextField.setText("" + bundle.getNumOfGammaMolecules());
        this.sigmaMoleculesTextField.setText("" + bundle.getNumOfSigmaMolecules());

        this.isLinearAlpha.setSelected(bundle.isLinearAlpha());
        this.isLinearBeta.setSelected(bundle.isLinearBeta());
        this.isSpinningAlpha.setSelected(bundle.isSpinningAlpha());
        this.isSpinningBeta.setSelected(bundle.isSpinningBeta());

        this.difficultyBox.setSelectedIndex(bundle.getDifficulty());
        this.lengthTextField.setText("" + bundle.getL());

    }

    @Override
    public void onValidParameters(ConfigBundle bundle) {
        new ConfirmationWindow(BuildingWindow.this, bundle, saveConfigPresetCheck.isSelected());

    }

    @Override
    public void onInvalidParameters(ArrayList<String> invalidFields) {
        new ErrorWindow(BuildingWindow.this, invalidFields);
    }

}
