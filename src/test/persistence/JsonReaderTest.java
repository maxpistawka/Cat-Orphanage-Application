package persistence;

import model.Cat;
import model.Foster;
import model.Shelter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class JsonReaderTest extends JsonTest {

        @Test
        void testReaderNonExistentFile() {
            JsonReader reader = new JsonReader("./data/noSuchFile.json");
            try {
                Shelter s = reader.read();
                fail("IOException expected, but none passed");
            } catch (IOException e) {
                // pass
            }
        }

        @Test
        void testReaderEmptyWorkRoom() {
            JsonReader reader = new JsonReader("./data/testReaderWriterEmptyShelter.json");
            try {
                Shelter s = reader.read();
                assertEquals(0, s.catsSize());
                assertEquals(0, s.fostersSize());
            } catch (IOException e) {
                fail("Couldn't read from file");
            }
        }

        @Test
        void testReaderGeneralWorkRoom() {
            JsonReader reader = new JsonReader("./data/testReaderWriterFullShelter.json");
            try {
                Shelter s = reader.read();
                List<Cat> cats = s.getCats();
                List<Foster> fosters = s.getFosters();
                assertEquals(2, s.catsSize());
                assertEquals(2, s.fostersSize());
                checkCat(cats.get(0),
                        "Marbles", "White", 3,
                        2, true, true, true, null);
                checkCat(cats.get(1),
                        "Kilo", "Spotted", 5,
                        5, true, false, false, fosters.get(0));
                checkFoster(fosters.get(0),"Max", true, true, false, 2);
                checkFoster(fosters.get(1),"Paul", true, false, true, 1);
            } catch (IOException e) {
                fail("Couldn't read from file");
            }
        }
}

