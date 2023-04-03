package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a shelter having a list of Cats and a list of Fosters that belong to the shelter
public class Shelter implements Writable {
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
        EventLog.getInstance().logEvent(
                new Event("Cat (" + c.getName() + ") added to registry."));
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
    //EFFECTS: removes cat from cats, and removes it from its fosters information if it has a foster
    public void deleteCat(Cat cat) {
        if (!(cat.getFosterFamily() == null)) {
            cat.getFosterFamily().getCurrentFosterCats().remove(cat);
        }
        cats.remove(cat);
    }

    //REQUIRES: inputted foster must be in this shelters fosters
    //MODIFIES: this
    //EFFECTS: removes foster from fosters and makes all its fostered cats without a foster
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("fosters", fostersToJson());
        json.put("cats", catsToJson());
        return json;
    }

    // EFFECTS: returns fosters in this shelter as a JSON array
    private JSONArray fostersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Foster f: fosters) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns cats in this shelter as a JSON array
    private JSONArray catsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Cat c: cats) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns how many cats are in this shelter
    public int catsSize() {
        return cats.size();
    }

    // EFFECTS: returns how many fosters are in this shelter
    public int fostersSize() {
        return fosters.size();
    }
}
