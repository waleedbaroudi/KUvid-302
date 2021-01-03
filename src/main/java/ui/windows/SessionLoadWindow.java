package ui.windows;

import model.game_building.GameBundle;
import model.game_running.RunningMode;
import model.game_running.SessionLoader;

import javax.swing.*;
import java.util.ArrayList;

public class SessionLoadWindow extends JFrame implements  SessionLoader.SessionLoadListener {
    private SessionLoader sessionLoader;
    private JFrame context;
    JList saveFilesNamesList;

    public SessionLoadWindow(JFrame context) {
        super("Load Session");
        this.context = context;

        setContentPane(new JPanel());
        this.pack(); // Pack the frame around the components
        this.setLocationRelativeTo(null); // Center the blender frame

        sessionLoader = new SessionLoader(this);
    }

    @Override
    public void getSavedSessions() {
        sessionLoader.fetchSavedSessions();
    }

    @Override
    public void onSessionListFetched(ArrayList<String> sessions) {
        // TODO: ADD THE LIST OF SAVES TO A JLIST
        saveFilesNamesList = new JList(sessions.toArray());
        JButton loadSessionButton = new JButton("Load Session");
        this.getContentPane().add(saveFilesNamesList);
        this.getContentPane().add(loadSessionButton);


        loadSessionButton.addActionListener(e -> {
            String sessionID = saveFilesNamesList.getSelectedValue().toString(); // get the id from the selected item
            sessionLoader.retrieveSession(sessionID);
            this.dispose();
        });
        setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
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
