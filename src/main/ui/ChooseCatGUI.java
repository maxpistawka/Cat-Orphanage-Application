package ui;

import model.Cat;
import model.Foster;
import model.NameCarrier;
import model.Shelter;
import javax.swing.*;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Represent the GUI for choosing a cat of the available cats
 */
class ChooseCatGUI extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private Shelter shelter;
    private JButton button;
    private JLabel catLabel;
    private JTextField catIndex;
    private int labelY = 20;
    private boolean unassign;

    // MODIFIES: this
    // EFFECTS: creates panel for user to choose cat
    public ChooseCatGUI(Shelter shelter, Boolean unassign) {
        this.unassign = unassign;
        this.shelter = shelter;
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        catLabel = new JLabel("Cat Number:");
        catLabel.setBounds(10,20,80,25);
        panel.add(catLabel);

        catIndex = new JTextField(20);
        catIndex.setBounds(100,20,165,25);
        panel.add(catIndex);

        makeButton();

        printNames(shelter.getCats());

        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates submit button
    public void makeButton() {
        button = new JButton("Submit");
        button.setBounds(10,100,80,25);
        button.addActionListener(new ChooseCatObserver(this));
        panel.add(button);
    }

    // EFFECTS: prints names of inputted nameCarriers
    public void printNames(List<Cat> cats) {
        int i = 0;
        for (Cat cat: cats) {
            i++;
            JLabel nameLabel = new JLabel(i + ":" + cat.getName());
            nameLabel.setBounds(10,i * 20 + 120,80,25);
            panel.add(nameLabel);
        }

    }

    // MODIFIES: this
    // EFFECTS: assigns foster to cat inputted
    public void action() {
        List<Cat> cats = shelter.getCats();
        int indexOfCat = parseInt(catIndex.getText()) - 1;
        if (unassign) {
            cats.get(indexOfCat).removeFoster();
        } else {
            new ChooseFosterGUI(shelter, shelter.getCats().get(indexOfCat));
        }
        frame.setVisible(false);
    }
}