package model;

import java.util.ArrayList;
import java.util.List;

// Represents a shelter having a list of Cats and a list of Fosters that belong to the shelter
public class Shelter {
    private List<Cat> cats;
    private List<Foster> fosters;

    //EFFECTS: initializes a shelter with no cats and no fosters
    public Shelter() {
        cats = new ArrayList<Cat>();
        fosters = new ArrayList<Foster>();
    }

    //MODIFIES: this
    //EFFECTS: adds inputted foster to list of fosters belonging to shelter
    public void addFoster(Foster f) {
        fosters.add(f);
    }

    //MODIFIES: this
    //EFFECTS: adds inputted cat to list of cats belonging to shelter
    public void addCat(Cat c) {
        cats.add(c);
    }

    //EFFECTS: returns a list of fosters belonging to the shelter that are eligible to foster the inputted cat
    public List<Foster> eligibleFoster(Cat cat) {
        List<Foster> fostersEligible = new ArrayList<Foster>();
        for (int i = 0; fosters.size() > i; i++) {
            if (fosters.get(i).eligibleFoster(cat)) {
                fostersEligible.add(fosters.get(i));
            }
        }
        return fostersEligible;
    }

    //REQUIRES: inputted cat must be in cats
    //MODIFIES: this
    //EFFECTS: removes cat from cats
    public void deleteCat(Cat cat) {
        if (!(cat.getFosterFamily() == null)) {
            cat.getFosterFamily().getCurrentFosterCats().remove(cat);
        }
        cats.remove(cat);
    }

    //REQUIRES: inputted foster must be in fosters
    //MODIFIES: this
    //EFFECTS: removes foster from fosters
    public void deleteFoster(Foster fos) {
        List<Cat> fostersCats = fos.getCurrentFosterCats();
        for (Cat c: fostersCats) {
            c.setFosterNull();
        }
        fosters.remove(fos);
    }

    public List<Cat> getCats() {
        return cats;
    }

    public List<Foster> getFosters() {
        return fosters;
    }
}
