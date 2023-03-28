package ui;

import model.Cat;
import model.Foster;
import model.Shelter;

import javax.swing.*;

import static java.lang.Integer.parseInt;

// Represents GUI for adding a foster
class FosterGUI extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private Shelter shelter;
    private JLabel nameLabel;

    private JTextField userText1;
    private JTextField userText2;
    private JTextField userText3;
    private JTextField userText4;
    private JTextField userText5;

    private JButton button;

    private int labelY = 20;



    public FosterGUI(Shelter shelter) {
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
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        labeler("Foster Name:");
        labeler("Outdoor Access (y/n):");
        labeler("Has Cats? (y/n):");
        labeler("Has Dogs? (y/n)");
        labeler("Max Foster Cats:");
        button = new JButton("Submit");
        button.setBounds(10,310,80,25);
        button.addActionListener(new FosterActionObserver(this));
        panel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: adds foster with given user info
    public void action() {
        Boolean outdoorAccess = userText2.getText().toLowerCase().equals("y");
        Boolean hasCats = userText3.getText().toLowerCase().equals("y");
        Boolean hasDogs = userText4.getText().toLowerCase().equals("y");
        shelter.addFoster(new Foster(userText1.getText(), outdoorAccess,
                hasCats,
                hasDogs,
                parseInt(userText5.getText())));
        frame.setVisible(false);
    }
}
