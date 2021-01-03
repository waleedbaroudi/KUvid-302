package model.game_running;

import model.game_building.GameBundle;
import model.game_building.GameConstants;
import org.apache.log4j.Logger;
import utils.database.MongoDBAdapter;

import java.io.IOException;
import java.util.ArrayList;

public class SessionLoader {

    private SessionLoadListener loadListener;
    private MongoDBAdapter dbAdapter;
    private static Logger logger;

    public SessionLoader(SessionLoadListener loadListener) {
        this.loadListener = loadListener;
        dbAdapter = MongoDBAdapter.getInstance();
        logger = Logger.getLogger(this.getClass().getName());
    }

    public void fetchSavedSessions() {
        ArrayList<String> savedSessions = MongoDBAdapter.getInstance().getDocumentsIds(GameConstants.SESSION_COLLECTION_TITLE);
        loadListener.onSessionListFetched(savedSessions);
    }

    public void retrieveSession(String sessionID) {
        GameBundle loadedBundle = null;
        try {
            loadedBundle = dbAdapter.load(GameConstants.SESSION_COLLECTION_TITLE, sessionID, GameBundle.class);
        } catch (IOException e) {
            e.printStackTrace();
            loadListener.onLoadFailed("Could not load the selected game session.");
        }
        loadListener.onSessionRetrieved(loadedBundle);
    }

    public interface SessionLoadListener {
        void onSessionListFetched(ArrayList<String> sessions);

        void onSessionRetrieved(GameBundle bundle);

        void onLoadFailed(String errorMessage);

        void getSavedSessions();
    }

}
