package persistence;

import model.Cat;
import model.Foster;
import model.Shelter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Shelter s = new Shelter();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Shelter s = new Shelter();
            JsonWriter writer = new JsonWriter("./data/testReaderWriterEmptyShelter.json");
            writer.open();
            writer.write(s);
            writer.close();
            JsonReader reader = new JsonReader("./data/testReaderWriterEmptyShelter.json");
            s = reader.read();
            assertEquals(0, s.catsSize());
            assertEquals(0, s.fostersSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Shelter s = new Shelter();
            Cat marbles = new Cat("Marbles", "White", 3,
                    2, true, true, true);
            Cat kilo = new Cat("Kilo", "Spotted", 5,
                    5, true, false, false);
            Foster max = new Foster("Max", true, true, false, 2);
            Foster paul = new Foster("Paul", true, false, true, 1);
            s.addCat(marbles);
            kilo.assignFoster(max);
            s.addCat(kilo);
            s.addFoster(max);
            s.addFoster(paul);
            JsonWriter writer = new JsonWriter("./data/testReaderWriterFullShelter.json");
            writer.open();
            writer.write(s);
            writer.close();
            JsonReader reader = new JsonReader("./data/testReaderWriterFullShelter.json");
            s = reader.read();
            List<Cat> cats = s.getCats();
            List<Foster> fosters = s.getFosters();
            assertEquals(2, cats.size());
            assertEquals(2, fosters.size());
            checkCat(cats.get(0),
                    "Marbles", "White", 3,
                    2, true, true, true, null);
            checkCat(cats.get(1),
                    "Kilo", "Spotted", 5,
                    5, true, false, false, fosters.get(0));
            checkFoster(fosters.get(0),"Max", true, true, false, 2);
            checkFoster(fosters.get(1),"Paul", true, false, true, 1);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
