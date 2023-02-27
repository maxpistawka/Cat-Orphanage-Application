package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FosterTest {
    Cat cat1;
    Cat cat2;
    Cat cat3;
    Cat cat4;
    Foster fos1;
    Foster fos2;
    Foster fos3;
    Foster fos4;
    Foster fos5;
    Foster fos6;

    @BeforeEach
    void runBefore() {
        cat1 = new Cat("marbles", "birman", 0, 1, true, true, true);
        cat2 = new Cat("lock", "sphynx", 1, 0, false, true, false);
        cat3 = new Cat("marbles", "birman", 0, 1, true, true, true);
        cat4 = new Cat("lock", "sphynx", 1, 0, false, true, false);
        fos1 = new Foster("max", true, true, true, 10);
        fos2 = new Foster("jj", false, false, false, 3);
        fos3 = new Foster("carly", true, true, true, 7);
        fos4 = new Foster("liz", false, false, false, 1);
        fos5 = new Foster("carly", true, true, false, 1);
        fos6 = new Foster("liz", false, false, true, 1);

    }

    @Test
    void testFosConst() {
        assertEquals("Max", fos1.getName());
        assertEquals("Jj", fos2.getName());
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
    void testEligibleFos() {
        fos6.addFosterCat(cat1);
        fos6.addFosterCat(cat2);
        assertFalse(fos6.eligibleFoster(cat1));
        assertFalse(fos6.eligibleFoster(cat2));
        fos5.addFosterCat(cat1);
        assertFalse(fos5.eligibleFoster(cat1));
        assertFalse(fos5.eligibleFoster(cat2));
        fos5.addFosterCat(cat2);
        assertFalse(fos5.eligibleFoster(cat1));
        assertFalse(fos5.eligibleFoster(cat2));
        fos1.addFosterCat(cat1);
        fos1.addFosterCat(cat2);
        assertTrue(fos1.eligibleFoster(cat1));
        assertFalse(fos1.eligibleFoster(cat2));
        assertFalse(fos1.eligibleFoster(cat4));
        fos2.addFosterCat(cat1);
        assertFalse(fos2.eligibleFoster(cat1));
        assertFalse(fos2.eligibleFoster(cat2));
        fos2.addFosterCat(cat2);
        assertFalse(fos2.eligibleFoster(cat4));
        assertFalse(fos2.eligibleFoster(cat2));
        assertFalse(fos2.eligibleFoster(cat1));
        assertFalse(fos2.eligibleFoster(cat3));
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
