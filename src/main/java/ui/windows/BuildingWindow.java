package main.java.ui.windows;

import main.java.model.game_building.BuildingMode;
import main.java.model.game_building.ConfigBundle;
import sun.awt.image.BufferedImageDevice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.Stack;

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
	String[] difficultyLevels = { "Easy", "Medium", "Hard" };
	JComboBox<String> difficultyBox;
	ArrayList<Integer> atoms = new ArrayList<Integer>();
	// JRadioButtons
	JRadioButton isLinearAlpha;
	JRadioButton isSpinningAlpha;
	JRadioButton isLinearBeta;
	JRadioButton isSpinningBeta;

	// Configuration variables
	int alphaatomsNum, betaatomsNum, sigmaatomsNum, gammaatomsNum, moleculesNum, blockersNum, powerupsNum;
	double l;

	/**
	 * Constructor initiates the Scanner and BuildingMode instances
	 */
	public BuildingWindow(String title) {
		buildingMode = new BuildingMode(this);

		this.setSize(800, 800);
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
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Building Window"));
		// GridBagConstraints c = new GridBagConstraints();

		/*
		 * Creating labels and text fields
		 */
		JLabel alphaLabel = new JLabel("Alpha Atoms");
		panel.add(alphaLabel);

		alphaAtomsTextField = new JTextField(4);
		panel.add(alphaAtomsTextField);

		JLabel betaLabel = new JLabel("Beta Atoms");
		panel.add(betaLabel);

		betaAtomsTextField = new JTextField(4);
		panel.add(betaAtomsTextField);

		JLabel sigmaLabel = new JLabel("Segma Atoms");
		panel.add(sigmaLabel);

		sigmaAtomsTextField = new JTextField(4);
		panel.add(sigmaAtomsTextField);

		JLabel gammaLabel = new JLabel("Gamma Atoms");
		panel.add(gammaLabel);

		gammaAtomsTextField = new JTextField(4);
		panel.add(gammaAtomsTextField);

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

		JLabel lengthLabel = new JLabel("L unit ");
		panel.add(lengthLabel);

		lengthTextField = new JTextField(4);
		panel.add(lengthTextField);

		JLabel difficultyLabel = new JLabel("Difficulty ");
		panel.add(difficultyLabel);

		difficultyBox = new JComboBox<String>(difficultyLevels);
		difficultyBox.setSelectedIndex(1);
		panel.add(difficultyBox);

		/*
		 * Radio Button Groups
		 */

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
				// TODO: ADD TRY AND CATCH TO CONFIRM THESE PARAMETERS HAVE THE CORRECT TYPE

				try {
					getParametersValues();
					bundle = new ConfigBundle(atoms, blockersNum, powerupsNum, moleculesNum, l,
							isLinearAlpha.isSelected(), isLinearBeta.isSelected(), isSpinningAlpha.isSelected(),
							isSpinningBeta.isSelected(), difficultyBox.getSelectedIndex());
					// Validate the fields.
					buildingMode.validateParameters(bundle);

				} catch (NumberFormatException ex) {
					
					onInvalidParameters("One of the parameter has invalid format! .. recheck");
				}

			}
		});
	}

	private void getParametersValues() throws IllegalArgumentException {
		
		alphaatomsNum = Integer.parseInt(alphaAtomsTextField.getText());
		atoms.add(alphaatomsNum);
		betaatomsNum = Integer.parseInt(betaAtomsTextField.getText());
		atoms.add(betaatomsNum);
		gammaatomsNum = Integer.parseInt(alphaAtomsTextField.getText());
		atoms.add(gammaatomsNum);
		sigmaatomsNum = Integer.parseInt(sigmaAtomsTextField.getText());
		atoms.add(sigmaatomsNum);
		moleculesNum = Integer.parseInt(moleculesTextField.getText());
		blockersNum = Integer.parseInt(blockersTextField.getText());
		powerupsNum = Integer.parseInt(powerupsTextField.getText());
		l = Double.parseDouble(lengthTextField.getText());

	}

	public void onValidParameters() {
		// TODO: Pass the bundle to the confirmation window to have a little summary on
		// the window.
		ConfirmationWindow confirmationWindow = new ConfirmationWindow(BuildingWindow.this);
	}

	public void onInvalidParameters(String message) {
		ErrorWindow errorWindow = new ErrorWindow(BuildingWindow.this, message);
	}
}
