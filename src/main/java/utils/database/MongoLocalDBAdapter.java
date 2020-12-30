package utils.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoLocalDBAdapter implements INonRationalDB{

    private String HOST_ADDRESS = "localhost";
    private int HOST_PORT = 27017;
    private String USER_NAME = "admin";
    private String DB_NAME = "KuvidGameDB";
    private String PASSWORD = "admin";


    public static Logger logger = Logger.getLogger(MongoLocalDBAdapter.class.getName());
    private MongoDatabase database;

    public MongoLocalDBAdapter(){
        logger.setLevel(Level.ALL);

        ConnectionString connectionString = new ConnectionString("mongodb://" + HOST_ADDRESS + ":" + String.valueOf(HOST_PORT));
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();

        // prepare the connection
        MongoClient mongoClient = MongoClients.create(clientSettings);

        // credentials
        MongoCredential credential = MongoCredential.createCredential(USER_NAME, DB_NAME, PASSWORD.toCharArray());
        logger.info("[MongoDBAtlasAdapter] connected to DB successfully");

        this.database = mongoClient.getDatabase(DB_NAME);
    }


    @Override
    public boolean registerCollection(String collectionTitle){
        logger.debug("[MongoDBAtlasAdapter] creating a new collection with title " + collectionTitle);
        // create collection if not available
        boolean exists = false;
        for(String title: this.database.listCollectionNames()){
            if(collectionTitle.equals(title))
                exists = true;
        }

        if(!exists){
            this.database.createCollection(collectionTitle);
            return true;
        }
        else {
            logger.warn("[MongoDBAtlasAdapter] a collection with title " + collectionTitle + " already exist");
            return false;
        }
    }

    @Override
    public <T> boolean save(String collectionTitle, Class<T> classType, T instance) {
        logger.debug("[MongoDBAtlasAdapter] saving instance of type " + classType.getName() + " to the mongoDB");
        MongoCollection<T> collection = this.database.getCollection(collectionTitle, classType);
        collection.insertOne(instance);
        logger.info("[MongoDBAtlasAdapter] instance of type " + classType.getName() + " was saved");
        return true;
    }

    @Override
    public <T> List<T> get(String collectionTitle, Class<T> classType) {
        logger.info("[MongoDBAtlasAdapter] retrieving instance of type " + classType.getName() + " to the mongoDB");
        MongoCollection<T> collection = this.database.getCollection(collectionTitle, classType);

        Iterator it = collection.find().iterator();
        Iterable<T> iterable = () -> it;
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
