package utils.database;

import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MongoDBAtlasAdapterTest {

    private MongoDBAtlasAdapter database;
    private String tmpDBTitle = "documentTest";

    @BeforeAll
    void connectToDB(){
        this.database = new MongoDBAtlasAdapter();
    }

    @AfterAll
    void disconnect(){
        // delete the temporary document

    }

    @Test
    void save_load_test() {
        // create a collection
        this.database.registerCollection(tmpDBTitle);

        // insert a document
        this.database.save(tmpDBTitle, Document.class, new Document("key", "value"));
        System.out.println("saved..");

        //retrieve the document
        List<Document> docList = this.database.get(tmpDBTitle, Document.class);
        assertEquals("value", docList.get(docList.size()-1).get("key"));
    }
}