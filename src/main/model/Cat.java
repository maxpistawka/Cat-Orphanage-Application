package model;

// Represents a cat with a name, a foster Family if they have one, their breed, their age (in months and year),
// whether they like cats, whether they like dogs, and whether they are an outdoor cat or not.
public class Cat {
    private String name;
    private Foster fosterFamily;
    private String breed;
    private int age;
    private int months;
    private Boolean likesCats;
    private Boolean likesDogs;
    private Boolean outdoor;

    //REQUIRES: age > 0 || months > 0
    //EFFECTS: initializes a cat with all given attributes
    public Cat(String name, String breed, int age, int months, boolean likesCats, boolean likesDogs, boolean outdoor) {
        this.name = name;
        fosterFamily = null;
        this.months = months;
        this.breed = breed;
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
            location = " outdoor";
        } else {
            location = " indoor";
        }
        String eyes = "cat with beautiful eyes.";
        String fostered;
        if (!(fosterFamily == null)) {
            fostered = "They currently live at one of our amazing foster houses.";
        } else {
            fostered = "They currently reside at out shelter in Vancouver looking for a foster.";
        }
        return (output + location + eyes + fostered);
    }

    //MODIFIES: this
    //EFFECTS: sets this cat's foster family to the inputted foster unless it already has a foster family
    public void assignFoster(Foster f) {
        if (fosterFamily == null) {
            fosterFamily = f;
            fosterFamily.addFosterCat(this);
        } else {
            System.out.println("ERROR! " + name + " already has a foster family.");
        }
    }

    //MODIFIES: this
    //EFFECTS: makes this cat have no current foster family
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

}
