package ui;

import model.Cat;
import model.Foster;
import model.Shelter;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.management.RuntimeErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Shelter application
public class ShelterApp {
    private static final String JSON_STORE = "./data/shelter.json";
    private Shelter shelter;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: initializes ShelterApp with a brand new empty Shelter, scanner for user input, a jsonWriter and
    //          jsonReader with JSON_STORE as their address and then runs the shelter app program.
    public ShelterApp() {
        shelter = new Shelter();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runShelterApp();
    }

    // The structure of runShelterApp, displayMenu, and processCommand is derived from the TellerApp given as a guide

    // MODIFIES: this
    // EFFECTS: process and utilize user input
    private void runShelterApp() {
        boolean running = true;
        String command;

        while (running) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                running = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSee you next time!");
    }

    //EFFECTS: displays menu of choices for user actions to choose from
    private void displayMenu() {
        System.out.println("\nChoose action:");
        System.out.println("\tf -> add foster to registry");
        System.out.println("\tc -> add cat to registry");
        System.out.println("\ta -> assign cat to compatible foster");
        System.out.println("\tv -> view description of cat");
        System.out.println("\tr -> remove foster from cat");
        System.out.println("\tdc -> remove cat from registry");
        System.out.println("\tdf -> remove foster from registry");
        System.out.println("\ts -> save your shelter to file");
        System.out.println("\tl -> load a previous shelter from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes and executes user action chosen. if user enters invalid input at any point, stop executing
    //          the code and return to the selection choices
    private void processCommand(String command) {
        try {
            if (command.equals("f")) {
                doAddFoster();
            } else if (command.equals("c")) {
                doAddCat();
            } else if (command.equals("a")) {
                doAssign();
            } else if (command.equals("v")) {
                doDescription();
            } else if (command.equals("r")) {
                doRemoveFoster();
            } else if (command.equals("dc")) {
                doDeleteCat();
            } else if (command.equals("df")) {
                doDeleteFoster();
            } else if (command.equals("s")) {
                doSave();
            } else if (command.equals("l")) {
                doLoad();
            }
        } catch (RuntimeErrorException | IndexOutOfBoundsException e) {
            System.out.print("Invalid input.");
        }
    }

    // EFFECTS: saves the shelter to file
    private void doSave() {
        try {
            jsonWriter.open();
            jsonWriter.write(shelter);
            jsonWriter.close();
            System.out.println("Saved your shelter to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads shelter from file into current program
    private void doLoad() {
        try {
            shelter = jsonReader.read();
            System.out.println("Loaded your shelter from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: removes cat and it's information from registry and potential fosters.
    private void doDeleteCat() {
        Cat desiredCat = chooseCat();
        shelter.deleteCat(desiredCat);
        System.out.println(desiredCat.getName() + " removed from registry.");
    }

    //MODIFIES: this
    //EFFECTS: removes foster and it's information from registry and potential fostered cats.
    private void doDeleteFoster() {
        Foster desiredFoster = chooseFoster();
        shelter.deleteFoster(desiredFoster);
        System.out.println(desiredFoster.getName() + " deleted from registry.");
    }

    //MODIFIES: this
    //EFFECTS: removes foster from cat chosen by user.
    private void doRemoveFoster() {
        Cat desiredCat = chooseCat();
        desiredCat.removeFoster();
        System.out.println("Foster Family removed from " + desiredCat.getName() + "'s information.");
    }

    //MODIFIES: this
    //EFFECTS: creates a foster with user given information and adds it to the shelters collection of fosters
    private void doAddFoster() {
        try {
            System.out.print("Insert name of foster: ");
            String name = input.next().toLowerCase();
            System.out.print("Insert maximum foster cat capacity: ");
            int maxFosterCats = Integer.parseInt(input.next());
            System.out.print("Do they have Cats? (y/n): ");
            Boolean hasCats = (input.next().toLowerCase().equals("y"));
            System.out.print("Do they have Dogs? (y/n): ");
            Boolean hasDogs = (input.next().toLowerCase().equals("y"));
            System.out.print("Does their residence have outdoor access for the cat? (y/n): ");
            Boolean house = (input.next().toLowerCase().equals("y"));
            Foster fosterToAdd = new Foster(name, house, hasCats, hasDogs, maxFosterCats);
            shelter.addFoster(fosterToAdd);
            System.out.println("The foster has been added to the registry!");
        } catch (RuntimeException e) {
            System.out.print("Addition failed, invalid input");
        }
    }

    //MODIFIES: this
    //EFFECTS: creates a cat with user given information and adds it to the shelters collection of cats
    private void doAddCat() {
        try {
            String response;
            System.out.print("Insert name: ");
            String name = input.next().toLowerCase();
            System.out.print("Insert breed: ");
            String breed = input.next().toLowerCase();
            System.out.print("Insert age (years): ");
            int age = Integer.parseInt(input.next());
            System.out.print("Insert age (months): ");
            int months = Integer.parseInt(input.next());
            System.out.print("Do they like Cats? (y/n): ");
            Boolean likesCats = (input.next().toLowerCase().equals("y"));
            System.out.print("Do they like Dogs? (y/n): ");
            Boolean likesDogs = (input.next().toLowerCase().equals("y"));
            System.out.print("Are they an outdoor cat? (y/n): ");
            Boolean outdoor = (input.next().toLowerCase().equals("y"));
            Cat catToAdd = new Cat(name, breed, age, months, likesCats, likesDogs, outdoor);
            shelter.addCat(catToAdd);
            System.out.println("The cat has been added to the registry!");
        } catch (RuntimeException e) {
            System.out.print("Addition failed, invalid input");
        }
    }

    //MODIFIES: this
    //EFFECTS: allows the user to choose and assign an eligible foster to a user chosen cat.
    private void doAssign() {
        Cat desiredCat = chooseCat();
        List<Foster> fostersEligible = shelter.eligibleFoster(desiredCat);
        System.out.println("Choose Eligible Foster:");
        if (fostersEligible.size() > 0) {
            int n = 1;
            for (int i = 0; fostersEligible.size() > i; i++) {
                System.out.println(n + ": " + fostersEligible.get(i).getName());
                n++;
            }
            int fosterIndex = Integer.parseInt(input.next()) - 1;
            desiredCat.assignFoster(fostersEligible.get(fosterIndex));
        } else {
            System.out.println("Sorry, no currently available fosters for this cat.");
        }
    }


    //EFFECTS: shows description of user chosen cat
    private void doDescription() {
        Cat desiredCat = chooseCat();
        System.out.print(desiredCat);
    }

    //REQUIRES: shelter.getCats().size() > 0
    //EFFECTS: prompts user to chose from all cats at the shelter.
    private Cat chooseCat() {
        System.out.println("Choose cat:");
        int n = 1;
        for (int i = 0; shelter.getCats().size() > i; i++) {
            System.out.println(n + ": " + shelter.getCats().get(i).getName());
            n++;
        }
        int catIndex = Integer.parseInt(input.next()) - 1;
        return shelter.getCats().get(catIndex);
    }

    //REQUIRES: shelter.getFosters().size() > 0
    //EFFECTS: prompts user to chose from all fosters at the shelter.
    private Foster chooseFoster() {
        System.out.println("Choose Foster:");
        int n = 1;
        for (int i = 0; shelter.getFosters().size() > i; i++) {
            System.out.println(n + ": " + shelter.getFosters().get(i).getName());
            n++;
        }
        int fosterIndex = Integer.parseInt(input.next()) - 1;
        return shelter.getFosters().get(fosterIndex);
    }
}
