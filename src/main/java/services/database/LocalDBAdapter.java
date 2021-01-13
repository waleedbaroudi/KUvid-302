package services.database;

import java.io.IOException;
import java.util.List;

public class LocalDBAdapter implements IDatabase{
    @Override
    public <T> boolean save(String collectionTitle, String uniqueID, T instance) throws IOException {
        return false;
    }

    @Override
    public <T> T load(String collectionTitle, String uniqueID, Class<T> tClass) throws IOException {
        return null;
    }

    @Override
    public List<String> getDocumentsIds(String collectionTitle) {
        return null;
    }
}
