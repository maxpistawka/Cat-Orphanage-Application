package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FosterTest {
    Cat cat1;
    Cat cat2;
    Foster fos1;
    Foster fos2;
    Foster fos3;
    Foster fos4;

    @BeforeEach
    void runBefore() {
        cat1 = new Cat("marbles", "birman", 0, 1, true, true, true);
        cat2 = new Cat("lock", "sphynx", 1, 0, false, true, false);
        fos1 = new Foster("max", true, true, true, 10);
        fos2 = new Foster("jj", false, false, false, 3);
        fos3 = new Foster("carly", true, true, true, 7);
        fos4 = new Foster("liz", false, false, false, 1);
    }

    @Test
    void testFosConst() {
        assertEquals("max", fos1.getName());
        assertEquals("jj", fos2.getName());
        assertEquals(10, fos1.getMaxFosterCats());
        assertEquals(3, fos2.getMaxFosterCats());
        assertTrue(fos1.getHouse());
        assertFalse(fos2.getHouse());
        assertTrue(fos1.getHasCats());
        assertFalse(fos2.getHasDogs());
        assertTrue(fos1.getHasDogs());
        assertFalse(fos2.getHasDogs());
        assertEquals(0, fos1.getCurrentFosterCats().size());
        assertEquals(0, fos2.getCurrentFosterCats().size());
    }

    @Test
    void testAddAndRemoveCat() {
        fos1.addFosterCat(cat1);
        assertTrue(fos1.getCurrentFosterCats().contains(cat1));
        fos1.addFosterCat(cat2);
        assertTrue(fos1.getCurrentFosterCats().contains(cat1));
        assertTrue(fos1.getCurrentFosterCats().contains(cat2));
        assertEquals(2, fos1.getCurrentFosterCats().size());
        fos1.removeFosterCat(cat1);
        assertTrue(fos1.getCurrentFosterCats().contains(cat2));
        assertEquals(1, fos1.getCurrentFosterCats().size());
        fos1.removeFosterCat(cat2);
        assertEquals(0, fos1.getCurrentFosterCats().size());
    }
}
