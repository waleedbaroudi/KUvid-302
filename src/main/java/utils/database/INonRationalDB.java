package utils.database;

import java.util.List;

public interface INonRationalDB {
//    boolean deleteCollection(String collectionTitle)
    boolean registerCollection(String collectionTitle);
    <T> boolean save(String collectionTitle, Class<T> classType, T instance);
    <T> List<T> get(String collectionTitle, Class<T> classType);
}
