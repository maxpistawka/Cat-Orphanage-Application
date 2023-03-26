package ui;

import model.Cat;
import model.Foster;
import model.NameCarrier;
import model.Shelter;
import javax.swing.*;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Represent the GUI for Assigning a foster to a cat
 */
class AssignGUI extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private Shelter shelter;
    private JButton button;
    private JLabel fosterLabel;
    private JLabel catLabel;
    private JTextField fosterIndex;
    private JTextField catIndex;
    private int seperation = 0;
    private int labelY = 20;

    // MODIFIES: this
    // EFFECTS: creates panel for user to submit foster and cat to assign
    public AssignGUI(Shelter shelter) {
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

        fosterLabel = new JLabel("Foster Number:");
        fosterLabel.setBounds(10,60,80,25);
        panel.add(fosterLabel);

        fosterIndex = new JTextField(20);
        fosterIndex.setBounds(100,60,165,25);
        panel.add(fosterIndex);

        printNames((List<NameCarrier>)(List<?>) shelter.getCats());
        printNames((List<NameCarrier>)(List<?>) shelter.getFosters());

        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates submit button
    public void makeButton() {
        button = new JButton("Submit");
        button.setBounds(10,100,80,25);
        button.addActionListener(new AssignObserver(this));
        panel.add(button);
    }

    // EFFECTS: prints names of inputted nameCarriers
    public void printNames(List<NameCarrier> nameCarriers) {
        int i = 0;
        for (NameCarrier nc: nameCarriers) {
            i++;
            JLabel nameLabel = new JLabel(i + ":" + nc.getName());
            nameLabel.setBounds(10 + seperation,i * 20 + 120,80,25);
            panel.add(nameLabel);
        }
        seperation += 110;

    }

    // MODIFIES: this
    // EFFECTS: assigns foster to cat inputted
    public void action() {
        List<Foster> fosters = shelter.getFosters();
        List<Cat> cats = shelter.getCats();
        int indexOfFoster = parseInt(fosterIndex.getText()) - 1;
        int indexOfCat = parseInt(catIndex.getText()) - 1;
        cats.get(indexOfCat).assignFoster(fosters.get(indexOfFoster));
        frame.setVisible(false);
    }
}