package persistence;

import model.Cat;
import model.Foster;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCat(Cat cat, String name, String breed, int age, int months,
                            boolean likesCats, boolean likesDogs, boolean outdoor, Foster fosterFamily) {
        assertEquals(fosterFamily, cat.getFosterFamily());
        assertEquals(name, cat.getName());
        assertEquals(breed, cat.getBreed());
        assertEquals(age, cat.getAge());
        assertEquals(months, cat.getMonths());
        assertEquals(likesCats, cat.getLikesCats());
        assertEquals(likesDogs, cat.getLikesDogs());
        assertEquals(outdoor, cat.getOutdoor());

    }
    protected void checkFoster(Foster f, String name, Boolean outdoorAccess, Boolean hasCats, Boolean hasDogs,
                               int maxFosterCats) {
        assertEquals(name, f.getName());
        assertEquals(maxFosterCats, f.getMaxFosterCats());
        assertEquals(hasCats, f.getHasCats());
        assertEquals(hasDogs, f.getHasDogs());
        assertEquals(outdoorAccess, f.getOutdoorAccess());
    }
}