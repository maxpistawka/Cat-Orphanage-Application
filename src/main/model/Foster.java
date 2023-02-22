package model;

import java.util.ArrayList;
import java.util.List;

//Represents a foster with a name, whether they live in a house(have outdoor access), whether they have cats,
//whether they have dogs, the maximum amount of cats they are willing to foster at one time, and a list of all the cats
//they are currently fostering
public class Foster {
    private String name;
    private Boolean house;
    private Boolean hasCats;
    private Boolean hasDogs;
    private int maxFosterCats;
    private List<Cat> currentFosterCats;

    //REQUIRES: maxFosterCats > 0
    //EFFECTS: initializes a foster with all required information
    public Foster(String name, Boolean house, Boolean hasCats, Boolean hasDogs, int maxFosterCats) {
        this.name = name;
        this.house = house;
        this.hasCats = hasCats;
        this.hasDogs = hasDogs;
        this.maxFosterCats = maxFosterCats;
        this.currentFosterCats = new ArrayList<Cat>();
    }

    //EFFECTS: returns whether or not this foster is an eligible foster for the inputted cat.
    public Boolean eligibleFoster(Cat cat) {
        Boolean req1 = (!hasCats || cat.getLikesCats());
        Boolean req2 = (!hasDogs || cat.getLikesDogs());
        Boolean req3 = (house || !cat.getOutdoor());
        Boolean req4 = (currentFosterCats.size() < maxFosterCats);
        Boolean req5 = (!((currentFosterCats.size() > 0) && !cat.getLikesCats()));
        return req1 && req2 && req3 && req4 && req5;
    }

    public void addFosterCat(Cat cat) {
        currentFosterCats.add(cat);
    }

    public void removeFosterCat(Cat cat) {
        currentFosterCats.remove(cat);
    }

    public String getName() {
        return name;
    }

    public Boolean getHouse() {
        return house;
    }

    public Boolean getHasCats() {
        return hasCats;
    }

    public Boolean getHasDogs() {
        return hasDogs;
    }

    public int getMaxFosterCats() {
        return maxFosterCats;
    }

    public List<Cat> getCurrentFosterCats() {
        return currentFosterCats;
    }
}
