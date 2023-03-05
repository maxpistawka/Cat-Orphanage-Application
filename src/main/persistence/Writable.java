package persistence;

import org.json.JSONObject;

// All code design in the persistence package, aswell as the persistence test package is derived and based on the code
// from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
