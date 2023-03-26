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
class ChooseFosterGUI extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private Shelter shelter;
    private JButton button;
    private JLabel fosterLabel;
    private JTextField fosterIndex;
    private int labelY = 20;
    private Cat cat;
    
    // MODIFIES: this
    // EFFECTS: creates panel for user to submit foster and cat to assign
    public ChooseFosterGUI(Shelter shelter, Cat cat) {
        this.shelter = shelter;
        this.cat = cat;
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        fosterLabel = new JLabel("Foster Number:");
        fosterLabel.setBounds(10,60,80,25);
        panel.add(fosterLabel);

        fosterIndex = new JTextField(20);
        fosterIndex.setBounds(100,60,165,25);
        panel.add(fosterIndex);

        makeButton();
        printNames(shelter.getFosters());

        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates submit button
    public void makeButton() {
        button = new JButton("Submit");
        button.setBounds(10,100,80,25);
        button.addActionListener(new ChooseFosterObserver(this));
        panel.add(button);
    }

    // EFFECTS: prints names of inputted nameCarriers
    public void printNames(List<Foster> nameCarriers) {
        int i = 0;
        for (Foster nc: nameCarriers) {
            if (nc.eligibleFoster(cat)) {
                i++;
                JLabel nameLabel = new JLabel(i + ":" + nc.getName());
                nameLabel.setBounds(10,i * 20 + 120,80,25);
                panel.add(nameLabel);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: assigns foster to cat inputted
    public void action() {
        List<Foster> fosters = shelter.getFosters();
        int indexOfFoster = parseInt(fosterIndex.getText()) - 1;
        cat.assignFoster(fosters.get(indexOfFoster));
        frame.setVisible(false);
    }
}