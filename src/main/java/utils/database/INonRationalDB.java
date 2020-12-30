package utils.database;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface INonRationalDB {
    boolean deleteCollection(String collectionTitle);
    boolean registerCollection(String collectionTitle);
    <T> boolean save(String collectionTitle, String uniqueID, T instance) throws IOException;
    <T> boolean update(String collectionTitle, String uniqueID, T instance) throws IOException;
    boolean delete(String collectionTitle, String uniqueID);
    <T> T load(String collectionTitle, String uniqueID, Class<T> tClass) throws IOException;

}
