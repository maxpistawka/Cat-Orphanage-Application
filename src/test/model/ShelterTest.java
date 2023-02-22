package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShelterTest {
    Cat cat1;
    Cat cat2;
    Cat cat3;
    Cat cat4;
    Cat cat5;
    Cat cat6;
    Foster fos1;
    Foster fos2;
    Foster fos3;
    Foster fos4;
    Foster fos5;
    Foster fos6;
    Shelter shelt1;
    Shelter shelt2;


    @BeforeEach
    void runBefore() {
        cat1 = new Cat("marbles", "birman", 0, 1, true, true, true);
        cat2 = new Cat("lock", "sphynx", 1, 0, false, true, false);
        cat3 = new Cat("kitten", "black", 2, 4, true, false, true);
        cat4 = new Cat("kilo", "calico", 41, 11, false, false, false);
        cat5 = new Cat("marbles", "birman", 0, 1, false, false, true);
        cat6 = new Cat("lock", "sphynx", 1, 0, true, true, false);
        fos1 = new Foster("max", true, true, true, 10);
        fos2 = new Foster("jj", false, false, false, 3);
        fos3 = new Foster("carly", true, true, true, 7);
        fos4 = new Foster("liz", false, false, false, 1);
        fos5 = new Foster("carly", true, true, false, 7);
        fos6 = new Foster("liz", false, false, true, 1);
        shelt1 = new Shelter();
        shelt2 = new Shelter();
    }

    @Test
    void testAddDeleteFoster() {
        assertEquals(0, shelt1.getFosters().size());
        cat1.assignFoster(fos1);
        shelt1.addFoster(fos1);
        assertEquals(fos1, shelt1.getFosters().get(0));
        assertEquals(1, shelt1.getFosters().size());
        shelt1.deleteFoster(fos1);
        assertEquals(0, shelt1.getFosters().size());
        assertEquals(null, cat1.getFosterFamily());
        shelt2.addFoster(fos2);
        assertEquals(fos2, shelt2.getFosters().get(0));
        assertEquals(1, shelt2.getFosters().size());
    }

    @Test
    void testAddDeleteCat() {
        assertEquals(0, shelt1.getCats().size());
        cat1.assignFoster(fos1);
        shelt1.addCat(cat1);
        assertEquals(1, fos1.getCurrentFosterCats().size());
        assertEquals(cat1, shelt1.getCats().get(0));
        assertEquals(1, shelt1.getCats().size());
        shelt1.deleteCat(cat1);
        assertEquals(0, shelt1.getCats().size());
        assertEquals(0, fos1.getCurrentFosterCats().size());
        shelt2.addCat(cat2);
        assertEquals(cat2, shelt2.getCats().get(0));
        assertEquals(1, shelt2.getCats().size());
        shelt2.deleteCat(cat2);
        assertEquals(0, shelt2.getCats().size());
    }

    @Test
    void testEligibleFosters() {
        assertEquals(0, shelt1.eligibleFoster(cat1).size());
        shelt2.addFoster(fos2);
        assertEquals(1, shelt2.eligibleFoster(cat2).size());
        shelt2.addFoster(fos1);
        shelt2.addFoster(fos3);
        shelt2.addFoster(fos4);
        shelt2.addFoster(fos5);
        shelt2.addFoster(fos6);
        assertEquals(0, shelt1.eligibleFoster(cat1).size());
        assertEquals(3, shelt2.eligibleFoster(cat2).size());
        assertEquals(1, shelt2.eligibleFoster(cat3).size());
        assertEquals(2, shelt2.eligibleFoster(cat4).size());
        assertEquals(0, shelt2.eligibleFoster(cat5).size());
        assertEquals(6, shelt2.eligibleFoster(cat6).size());
    }
}
