package ui;

import model.Cat;
import model.Shelter;

import javax.swing.*;

import static java.lang.Integer.parseInt;

// Represents GUI for adding a cat
class CatGUI extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private Shelter shelter;
    private JLabel nameLabel;

    private JTextField userText1;
    private JTextField userText2;
    private JTextField userText3;
    private JTextField userText4;
    private JTextField userText5;
    private JTextField userText6;
    private JTextField userText7;

    private JButton button;

    private int labelY = 20;

    // EFFECTS: creates panel for user to add new cat
    public CatGUI(Shelter shelter) {
        addLabelsAndButton(shelter);

        userText1 = new JTextField(20);
        userText1.setBounds(175,20,165,25);
        panel.add(userText1);

        userText2 = new JTextField(20);
        userText2.setBounds(175,60,165,25);
        panel.add(userText2);

        userText3 = new JTextField(20);
        userText3.setBounds(175,100,165,25);
        panel.add(userText3);

        userText4 = new JTextField(20);
        userText4.setBounds(175,140,165,25);
        panel.add(userText4);


        userText5 = new JTextField(20);
        userText5.setBounds(175,180,165,25);
        panel.add(userText5);

        userText6 = new JTextField(20);
        userText6.setBounds(175,220,165,25);
        panel.add(userText6);

        userText7 = new JTextField(20);
        userText7.setBounds(175,260,165,25);
        panel.add(userText7);

        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates a specific label based on string input and adds it to the panel
    public void labeler(String s) {
        nameLabel = new JLabel(s);
        nameLabel.setBounds(10,labelY,140,25);
        panel.add(nameLabel);
        labelY += 40;
    }

    // MODIFIES: this
    // EFFECTS: initalizes shelter, frame, panel, and creates labels and button
    public void addLabelsAndButton(Shelter shelter) {
        this.shelter = shelter;
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        labeler("Cat Name:");
        labeler("Breed:");
        labeler("Age(years):");
        labeler("Age (months):");
        labeler("Likes Cats (y/n):");
        labeler("Likes Dogs (y/n):");
        labeler("Outdoor cat (y/n):");
        button = new JButton("Submit");
        button.setBounds(10,310,80,25);
        button.addActionListener(new CatActionObserver(this));
        panel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: adds cat with given user info
    public void action() {
        Boolean likesCats = userText5.getText().toLowerCase().equals("y");
        Boolean likesDogs = userText6.getText().toLowerCase().equals("y");
        Boolean outdoor = userText7.getText().toLowerCase().equals("y");
        shelter.addCat(new Cat(userText1.getText(), userText2.getText(),
                parseInt(userText3.getText()),
                parseInt(userText4.getText()),
                likesCats,
                likesDogs,
                outdoor));
        frame.setVisible(false);
    }
}
