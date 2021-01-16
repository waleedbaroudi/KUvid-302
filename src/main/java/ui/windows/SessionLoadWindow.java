package ui.windows;

import model.game_building.GameBundle;
import model.game_running.SessionLoader;
import services.exceptions.UnsupportedNameFormatException;
import services.utils.IOHandler;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class SessionLoadWindow extends JFrame implements SessionLoader.SessionLoadListener {
    private SessionLoader sessionLoader;
    private JFrame context;
    JList<String> saveFilesNamesList;

    public SessionLoadWindow(JFrame context) {// resets the lists in the load up window closing
        super("Load Session");
        this.context = context;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                sessionLoader.resetLists();
            }
        });

        setContentPane(new JPanel());
        this.pack(); // Pack the frame around the components
        this.setLocationRelativeTo(null); // Center the blender frame

        sessionLoader = new SessionLoader(this);
    }

    @Override
    public void getSavedSessions() {
        // Remove all the elements before appending the new sessions.
        this.getContentPane().removeAll();
        addDataBaseSelector();
        saveFilesNamesList = new JList<>();
        JButton loadSessionButton = new JButton("Load Session");
        this.getContentPane().add(saveFilesNamesList);
        this.getContentPane().add(loadSessionButton);

        loadSessionButton.addActionListener(e -> {
            String sessionID = saveFilesNamesList.getSelectedValue(); // get the id from the selected item
            sessionLoader.retrieveSession(sessionID);
            this.dispose();
            //reset loader lists when disposed
            sessionLoader.resetLists();
        });

        sessionLoader.fetchOnlineSavedSessions();
    }

    @Override
    public void onSessionListFetched(ArrayList<String> sessions) {
        String[] prettifiedSessionNames = sessions.stream().map(name -> {
            try {
                return IOHandler.prettifyYAMLFileName(name);
            } catch (UnsupportedNameFormatException ex) {
                return name;
            }
        }).toArray(String[]::new);
        saveFilesNamesList.setListData(prettifiedSessionNames);

        setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    private void addDataBaseSelector() {
        String[] databaseTypes = {"Online Saved Sessions", "Locally Saved Sessions"};
        JComboBox<String> databaseSelector = new JComboBox<>(databaseTypes);
        databaseSelector.setSelectedIndex(0);
        databaseSelector.addActionListener(e -> {
            if (databaseSelector.getSelectedIndex() == 0) {
                sessionLoader.fetchOnlineSavedSessions();
            } else
                sessionLoader.fetchLocallySavedSession();
        });

        getContentPane().add(databaseSelector);
    }

    @Override
    public void onSessionRetrieved(GameBundle bundle) {
        ((RunningWindow) this.context).loadGameSession(bundle);
    }

    @Override
    public void onLoadFailed(String errorMessage) {
        // todo: display error message window
        System.out.println("ERROR: " + errorMessage);
    }
}
