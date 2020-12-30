package utils.database;

import model.game_entities.Atom;
import model.game_entities.Blocker;
import model.game_entities.enums.EntityType;
import model.game_entities.factories.BlockerFactory;
import model.game_physics.hitbox.CircularHitbox;
import model.game_physics.path_patterns.StraightPattern;
import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.Coordinates;
import utils.Velocity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MongoLocalDBAdapterTest {

    private MongoLocalDBAdapter database;

    @BeforeAll
    void connectToDB(){
        this.database = new MongoLocalDBAdapter();
    }

    @AfterAll
    void disconnect(){
        // delete the temporary document

    }

    @Test
    void saveLoadDocumentTest() {
        String tmpDBTitle = "documentTest";

        // create a collection
        this.database.registerCollection(tmpDBTitle);

        // insert a document
        this.database.save(tmpDBTitle, Document.class, new Document("key", "value"));
        System.out.println("saved..");

        //retrieve the document
        List<Document> docList = this.database.get(tmpDBTitle, Document.class);
        assertEquals("value", docList.get(docList.size()-1).get("key"));
    }

    @Test
    void saveLoadDummyTest() {
        String tmpDBTitle = "dummyTest";
        // create a collection
        this.database.registerCollection(tmpDBTitle);

        // insert a document
        this.database.save(tmpDBTitle, dummyClass.class, new dummyClass("Moayed", 21));
        System.out.println("saved..");

        //retrieve the document
        List<dummyClass> docList = this.database.get(tmpDBTitle, dummyClass.class);
        assertEquals("Moayed", docList.get(docList.size()-1).getName());
    }

    @Test
    void saveLoadAtomTest() {
        String tmpDBTitle = "atomTest";
        // create a collection
        this.database.registerCollection(tmpDBTitle);

        // insert a document
        Atom dummyAtom = new Atom(new Coordinates(0,0), new CircularHitbox(5), new StraightPattern(new Velocity(1,1)), EntityType.ALPHA);
        this.database.save(tmpDBTitle, Atom.class, dummyAtom);
        System.out.println("saved..");

        //retrieve the document
        List<Atom> docList = this.database.get(tmpDBTitle, Atom.class);
        assertEquals(EntityType.ALPHA, docList.get(docList.size()-1).getType());
    }
}