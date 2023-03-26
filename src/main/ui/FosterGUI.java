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
    private JLabel outdoorLabel;
    private JLabel hasCatsLabel;
    private JLabel hasDogsLabel;
    private JLabel maxFosterCatsLabel;


    private JTextField userText1;
    private JTextField userText2;
    private JTextField userText3;
    private JTextField userText4;
    private JTextField userText5;


    private JButton button;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    // EFFECTS: creates panel for user to add new foster
    public FosterGUI(Shelter shelter) {
        this.shelter = shelter;
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        nameLabel = new JLabel("Foster Name:");
        nameLabel.setBounds(10,20,80,25);
        panel.add(nameLabel);

        userText1 = new JTextField(20);
        userText1.setBounds(100,20,165,25);
        panel.add(userText1);

        outdoorLabel = new JLabel("Outdoor Access (y/n):");
        outdoorLabel.setBounds(10,60,80,25);
        panel.add(outdoorLabel);

        userText2 = new JTextField(20);
        userText2.setBounds(100,60,165,25);
        panel.add(userText2);

        hasCatsLabel = new JLabel("Has Cats? (y/n):");
        hasCatsLabel.setBounds(10,100,80,25);
        panel.add(hasCatsLabel);

        userText3 = new JTextField(20);
        userText3.setBounds(100,100,165,25);
        panel.add(userText3);

        hasDogsLabel = new JLabel("Has Dogs? (y/n)");
        hasDogsLabel.setBounds(10,140,80,25);
        panel.add(hasDogsLabel);

        userText4 = new JTextField(20);
        userText4.setBounds(100,140,165,25);
        panel.add(userText4);

        maxFosterCatsLabel = new JLabel("Max Foster Cats:");
        maxFosterCatsLabel.setBounds(10,180,80,25);
        panel.add(maxFosterCatsLabel);

        userText5 = new JTextField(20);
        userText5.setBounds(100,180,165,25);
        panel.add(userText5);

        button = new JButton("Submit");
        button.setBounds(10,220,80,25);
        button.addActionListener(new FosterActionObserver(this));
        panel.add(button);

        frame.setVisible(true);

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
