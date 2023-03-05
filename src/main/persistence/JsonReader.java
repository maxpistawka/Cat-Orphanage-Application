package persistence;

import model.Cat;
import model.Foster;
import model.Shelter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads a shelter from a stored file of JSON data
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads shelter from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public Shelter read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseShelter(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Shelter parseShelter(JSONObject jsonObject) {
        Shelter s = new Shelter();
        addFosters(s, jsonObject);
        addCats(s, jsonObject);
        return s;
    }

    // MODIFIES: s
    // EFFECTS: parses fosters from JSON object and adds them to shelter
    private void addFosters(Shelter s, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("fosters");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addFoster(s, nextThingy);
        }
    }

    // MODIFIES: s
    // EFFECTS: parses foster from JSON object and adds it to shelter
    private void addFoster(Shelter s, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Boolean outdoorAccess = jsonObject.getBoolean("outdoor access");
        Boolean hasCats = jsonObject.getBoolean("has cats");
        Boolean hasDogs = jsonObject.getBoolean("has dogs");
        int maxFosterCapacity = jsonObject.getInt("max foster cats");
        Foster f = new Foster(name, outdoorAccess, hasCats, hasDogs, maxFosterCapacity);
        s.addFoster(f);
    }

    // MODIFIES: s
    // EFFECTS: parses cats from JSON object and adds them to shelter
    private void addCats(Shelter s, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cats");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addCat(s, nextThingy);
        }
    }


    // MODIFIES: s
    // EFFECTS: parses cat from JSON object and adds it to shelter
    private void addCat(Shelter s, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String breed = jsonObject.getString("breed");
        Boolean outdoor = jsonObject.getBoolean("outdoor cat");
        Boolean likesCats = jsonObject.getBoolean("likes cats");
        Boolean likesDogs = jsonObject.getBoolean("likes dogs");
        int age = jsonObject.getInt("age (years)");
        int months = jsonObject.getInt("age (months)");
        String fosterFamilyName = jsonObject.getString("foster");
        Cat c = new Cat(name, breed, age, months, likesCats, likesDogs, outdoor);
        s.addCat(c);
        if (!(fosterFamilyName.equals("no foster"))) {
            c.assignFoster(findFoster(s, fosterFamilyName));
        }
    }

    // REQUIRES: if a cat has a foster family name in the data, there must have been one and only one
    //           foster with that name in the data
    // EFFECTS: returns foster in shelter s with name fosterFamilyName
    public Foster findFoster(Shelter s, String fosterFamilyName) {
        int index = 0;
        for (Foster f: s.getFosters()) {
            if (fosterFamilyName.equals(f.getName())) {
                index = s.getFosters().indexOf(f);
            }
        }
        return s.getFosters().get(index);
    }
}
