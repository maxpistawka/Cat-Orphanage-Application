package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatTest {
    Cat cat1;
    Cat cat2;
    Cat cat3;
    Cat cat4;
    Foster fos1;
    Foster fos2;

    @BeforeEach
    void runBefore() {
        cat1 = new Cat("marbles", "birman", 0, 1, true, true, true);
        cat2 = new Cat("lock", "sphynx", 1, 0, false, true, false);
        cat3 = new Cat("kitten", "black", 2, 4, true, false, true);
        cat4 = new Cat("kilo", "calico", 41, 11, false, false, false);
        fos1 = new Foster("max", true, true, true, 10);
        fos2 = new Foster("jj", false, false, false, 3);
    }

    @Test
    void testConstructor() {
        assertEquals("Marbles", cat1.getName());
        assertEquals("Lock", cat2.getName());
        assertEquals("Birman", cat1.getBreed());
        assertEquals("Sphynx", cat2.getBreed());
        assertEquals(0, cat1.getAge());
        assertEquals(1, cat2.getAge());
        assertEquals(1, cat1.getMonths());
        assertEquals(0, cat2.getMonths());
        assertTrue(cat1.getLikesCats());
        assertFalse(cat4.getLikesCats());
        assertTrue(cat1.getLikesDogs());
        assertFalse(cat4.getLikesDogs());
        assertTrue(cat1.getOutdoor());
        assertFalse(cat4.getOutdoor());
        assertEquals(null, cat1.getFosterFamily());
        assertEquals(null, cat2.getFosterFamily());
    }

    @Test
    void testToString() {
        cat3.assignFoster(fos1);
        String descrip2 = cat2.toString();
        String descrip3 = cat3.toString();
        assertTrue(descrip2.contains("indoor"));
        assertTrue(descrip3.contains("outdoor"));
        assertTrue(descrip2.contains("1 year"));
        assertTrue(descrip3.contains("2 year"));
        assertTrue(descrip2.contains("0 month"));
        assertTrue(descrip3.contains("4 month"));
        assertTrue(descrip2.contains("Lock"));
        assertTrue(descrip3.contains("Kitten"));
        assertTrue(descrip2.contains("Sphynx"));
        assertTrue(descrip3.contains("Black"));
        assertTrue(descrip2.contains("Vancouver"));
        assertTrue(descrip3.contains("Max"));
    }
    @Test
    void testAssignAndRemoveFoster() {
        cat1.removeFoster();
        assertEquals(null, cat1.getFosterFamily());
        cat4.assignFoster(fos1);
        assertEquals(fos1, cat4.getFosterFamily());
        assertEquals(1,fos1.getCurrentFosterCats().size());
        cat4.assignFoster(fos2);
        assertEquals(fos1, cat4.getFosterFamily());
        cat4.removeFoster();
        assertEquals(null, cat4.getFosterFamily());
        assertEquals(0,fos1.getCurrentFosterCats().size());
        cat4.assignFoster(fos2);
        assertEquals(fos2, cat4.getFosterFamily());
        assertEquals(1,fos2.getCurrentFosterCats().size());
    }
}