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
    private JLabel ageLabel;
    private JLabel breedLabel;
    private JLabel monthsLabel;
    private JLabel likesCatsLabel;
    private JLabel likesDogsLabel;
    private JLabel outdoorLabel;

    private JTextField userText1;
    private JTextField userText2;
    private JTextField userText3;
    private JTextField userText4;
    private JTextField userText5;
    private JTextField userText6;
    private JTextField userText7;

    private JButton button;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    // EFFECTS: creates panel for user to add new cat
    public CatGUI(Shelter shelter) {
        this.shelter = shelter;
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        nameLabel = new JLabel("Cat Name:");
        nameLabel.setBounds(10,20,80,25);
        panel.add(nameLabel);

        userText1 = new JTextField(20);
        userText1.setBounds(100,20,165,25);
        panel.add(userText1);

        breedLabel = new JLabel("Breed:");
        breedLabel.setBounds(10,60,80,25);
        panel.add(breedLabel);

        userText2 = new JTextField(20);
        userText2.setBounds(100,60,165,25);
        panel.add(userText2);

        ageLabel = new JLabel("Age (years):");
        ageLabel.setBounds(10,100,80,25);
        panel.add(ageLabel);

        userText3 = new JTextField(20);
        userText3.setBounds(100,100,165,25);
        panel.add(userText3);

        monthsLabel = new JLabel("Age (months):");
        monthsLabel.setBounds(10,140,80,25);
        panel.add(monthsLabel);

        userText4 = new JTextField(20);
        userText4.setBounds(100,140,165,25);
        panel.add(userText4);

        likesCatsLabel = new JLabel("Likes Cats (y/n):");
        likesCatsLabel.setBounds(10,180,80,25);
        panel.add(likesCatsLabel);

        userText5 = new JTextField(20);
        userText5.setBounds(100,180,165,25);
        panel.add(userText5);

        likesDogsLabel = new JLabel("Likes Dogs (y/n):");
        likesDogsLabel.setBounds(10,220,80,25);
        panel.add(likesDogsLabel);

        userText6 = new JTextField(20);
        userText6.setBounds(100,220,165,25);
        panel.add(userText6);

        outdoorLabel = new JLabel("Outdoor cat (y/n):");
        outdoorLabel.setBounds(10,260,80,25);
        panel.add(outdoorLabel);

        userText7 = new JTextField(20);
        userText7.setBounds(100,260,165,25);
        panel.add(userText7);

        button = new JButton("Submit");
        button.setBounds(10,310,80,25);
        button.addActionListener(new CatActionObserver(this));
        panel.add(button);

        frame.setVisible(true);

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
