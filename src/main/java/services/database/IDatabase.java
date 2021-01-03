package services.database;


import java.io.IOException;
import java.util.List;

public interface IDatabase {
    /**
     * Remove the registered collection **if exists** from the DB
     * @param collectionTitle
     * @return deletion success state
     */
    boolean removeCollection(String collectionTitle);

    /**
     * register a new collection **if not exists** in the DB
     * @param collectionTitle
     * @return removement success state
     */
    boolean registerCollection(String collectionTitle);

    /**
     * Save a new instance under the given collectionTitle and bound the unique id. No two instance should be able to bound
     * to the same uniqueID
     * @param collectionTitle
     * @param uniqueID
     * @param instance
     * @param <T>
     * @return saving success state. Should return false in case the unique ID is already bounded to another instance
     * @throws IOException
     */
    <T> boolean save(String collectionTitle, String uniqueID, T instance) throws IOException;

    /**
     * update the instance bounded to the unique ID under the specific collection. In no instance is bounded to the
     * give unique id, it write a new instance
     * @param collectionTitle
     * @param uniqueID
     * @param instance
     * @param <T>
     * @return
     * @throws IOException
     */

    <T> boolean update(String collectionTitle, String uniqueID, T instance) throws IOException;

    /**
     * delete the instance **if exists** that is bounded to the give unique id.
     * @param collectionTitle
     * @param uniqueID
     * @return
     */
    boolean delete(String collectionTitle, String uniqueID);

    /**
     * load the instance bounded to the given unique id.
     * @param collectionTitle
     * @param uniqueID
     * @param tClass
     * @param <T>
     * @return a new instance of type T that was retrieved from the DB. In case no instance exits, it returns null.
     * @throws IOException
     */
    <T> T load(String collectionTitle, String uniqueID, Class<T> tClass) throws IOException;

    /**
     * get all the documents unique id in the collection
     * @param collectionTitle
     * @return
     */
    List<String> getDocumentsIds(String collectionTitle);

}
