package ui.windows;

import model.game_building.GameBundle;
import model.game_running.RunningMode;
import model.game_running.SessionLoader;

import javax.swing.*;
import java.util.ArrayList;

public class SessionLoadWindow extends JFrame implements RunningMode.SaveLoadListener, SessionLoader.SessionLoadListener {
    private SessionLoader sessionLoader;
    private JFrame context;

    public SessionLoadWindow(JFrame context) {
        super("Load Session");
        this.context = context;

        getContentPane().add(new JPanel());
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
        JButton loadSessionButton = new JButton("Load Session");
        loadSessionButton.addActionListener(e -> {
            String sessionID = null; // get the id from the selected item
            sessionLoader.retrieveSession(sessionID);
            this.dispose();
        });
        this.getContentPane().add(loadSessionButton);

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
        // todo: display error message
    }
}
