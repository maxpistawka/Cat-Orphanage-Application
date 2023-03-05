package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Locale;

// Represents a cat with a name, a foster Family if they have one, their breed, their age (in months and year),
// whether they like cats, whether they like dogs, and whether they are an outdoor cat or not.
public class Cat implements Writable {
    private String name;
    private Foster fosterFamily;
    private String breed;
    private int age;
    private int months;
    private Boolean likesCats;
    private Boolean likesDogs;
    private Boolean outdoor;

    //REQUIRES: age > 0 || months > 0 and age >= 0 and 12 > months >= 0
    //EFFECTS: initializes a cat with breed, age (years&months), name, animals they like, and if they are an outdoor
    //         cat, only the first letter of the name is capitalized. initially has no foster.
    public Cat(String name, String breed, int age, int months, boolean likesCats, boolean likesDogs, boolean outdoor) {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
        fosterFamily = null;
        this.months = months;
        this.breed = breed.substring(0, 1).toUpperCase() + breed.substring(1);
        this.age = age;
        this.likesCats = likesCats;
        this.likesDogs = likesDogs;
        this.outdoor = outdoor;
    }

    //EFFECTS: returns a string describing this cat
    public String toString() {
        String output = name + " is a " + age + " year and " + months + " month old " + breed + " cat. They are an";
        String location;
        if (outdoor) {
            location = " outdoor ";
        } else {
            location = " indoor ";
        }
        String eyes = "cat with beautiful eyes";
        String fostered;
        if (!(fosterFamily == null)) {
            fostered = ", currently living with our amazing foster, " + getFosterFamily().getName() + ".";
        } else {
            fostered = ", currently residing at out shelter in Vancouver looking for a foster!";
        }
        return (output + location + eyes + fostered);
    }

    //MODIFIES: this
    //EFFECTS: sets foster family to the inputted foster, adding this cat to the foster family's current cats
    //         unless it already has a foster family.
    public void assignFoster(Foster f) {
        if (fosterFamily == null) {
            fosterFamily = f;
            fosterFamily.addFosterCat(this);
            System.out.println("Your cat has been assigned a foster!");
        } else {
            System.out.println("ERROR! " + name + " already has a foster family.");
        }
    }

    //MODIFIES: this
    //EFFECTS: if this cat has no foster, do nothing,  if they have a foster,
    //         remove this cat from the fosters data, and remove the foster from being this cats foster family
    public void removeFoster() {
        if (!(fosterFamily == null)) {
            fosterFamily.removeFosterCat(this);
            fosterFamily = null;
        }

    }

    //MODIFIES: this
    //EFFECTS: makes this cat have no current foster family
    public void setFosterNull() {
        fosterFamily = null;
    }

    public Boolean getLikesCats() {
        return likesCats;
    }

    public Boolean getLikesDogs() {
        return likesDogs;
    }

    public Boolean getOutdoor() {
        return outdoor;
    }

    public String getName() {
        return name;
    }

    public Foster getFosterFamily() {
        return fosterFamily;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public int getMonths() {
        return months;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("breed", breed);
        json.put("age (years)", age);
        json.put("age (months)", months);
        json.put("outdoor cat", outdoor);
        json.put("likes cats", likesCats);
        json.put("likes dogs", likesDogs);
        if (!(fosterFamily == null)) {
            json.put("foster", fosterFamily.getName());
        } else {
            json.put("foster", "no foster");
        }

        return json;
    }
}
