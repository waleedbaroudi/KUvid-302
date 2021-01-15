package model.game_running;

import model.game_building.GameBundle;
import model.game_building.GameConstants;
import org.apache.log4j.Logger;
import services.database.IDatabase;
import services.database.LocalDBAdapter;
import services.database.MongoDBAdapter;
import services.utils.IOHandler;

import java.io.IOException;
import java.util.ArrayList;

public class SessionLoader {

    private SessionLoadListener loadListener;

    private IDatabase dbAdapter;
    private static Logger logger;

    ArrayList<String> localSessions, onlineSessions;

    public SessionLoader(SessionLoadListener loadListener) {
        this.loadListener = loadListener;
        dbAdapter = MongoDBAdapter.getInstance();
        logger = Logger.getLogger(this.getClass().getName());
    }


    public synchronized void fetchOnlineSavedSessions() {
        dbAdapter = MongoDBAdapter.getInstance();
        if (onlineSessions == null)
            onlineSessions = new ArrayList<>(dbAdapter.getDocumentsIds(GameConstants.SESSION_COLLECTION_TITLE));
        loadListener.onSessionListFetched(onlineSessions);
    }

    public synchronized void fetchLocallySavedSession() {
        dbAdapter = LocalDBAdapter.getInstance();
        if (localSessions == null)
            localSessions = new ArrayList<>(dbAdapter.getDocumentsIds("N/A"));
        loadListener.onSessionListFetched(localSessions);
    }

    public void retrieveSession(String sessionID) {
        GameBundle loadedBundle = null;
        try {
            loadedBundle = dbAdapter.load(GameConstants.SESSION_COLLECTION_TITLE, IOHandler.prettyToProperFileName(sessionID), GameBundle.class);
        } catch (IOException e) {
            e.printStackTrace();
            loadListener.onLoadFailed("Could not load the selected game session.");
        }
        loadListener.onSessionRetrieved(loadedBundle);
    }

    /**
     * resets the lists of saved sessions to null, so that they are updated on the next fetch call
     */
    public void resetLists() {
        localSessions = null;
        onlineSessions = null;
    }

    public interface SessionLoadListener {
        void onSessionListFetched(ArrayList<String> sessions);

        void onSessionRetrieved(GameBundle bundle);

        void onLoadFailed(String errorMessage);

        void getSavedSessions();
    }

}
