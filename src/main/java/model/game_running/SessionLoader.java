package model.game_running;

import model.game_building.GameBundle;
import org.apache.log4j.Logger;
import utils.database.MongoDBAdapter;

import java.io.IOException;
import java.util.ArrayList;

public class SessionLoader {

    private SessionLoadListener loadListener;
    private MongoDBAdapter dbAdapter;
    private static Logger logger;

    public SessionLoader(SessionLoadListener listener) {
        this.loadListener = listener;
        dbAdapter = MongoDBAdapter.getInstance();
        logger = Logger.getLogger(this.getClass().getName());
    }

    public void fetchSavedSessions() {
        ArrayList<String> savedSessions = null;
//        savedSessions = //GET THEM FROM THE DATABASE
        loadListener.onSessionListFetched(savedSessions);
    }

    public void retrieveSession(String sessionID) {
        GameBundle loadedBundle = null;
        try {
            loadedBundle = dbAdapter.load("filename", sessionID, GameBundle.class); // todo: @M2yad, why do we need the name? can we just use the ID?
        } catch (IOException e) {
            loadListener.onLoadFailed("Could not load the selected game session.");
        }
        loadListener.onSessionRetrieved(loadedBundle);
    }

    public interface SessionLoadListener {
        void onSessionListFetched(ArrayList<String> sessions);

        void onSessionRetrieved(GameBundle bundle);

        void onLoadFailed(String errorMessage);
    }

}
