package ui;

import model.Cat;
import model.Event;
import model.EventLog;
import model.Foster;
import model.Shelter;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the main GUI for this Shelter App
public class GUI extends JFrame implements WindowListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 1000;
    private Shelter shelter = new Shelter();
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private static final String JSON_STORE = "./data/shelter.json";

    // EFFECTS: creates GUI for this Shelter APP
    public GUI() {
        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        controlPanel = new JInternalFrame("Control Panel", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());

        setContentPane(desktop);
        setTitle("Cat Shelter Registry");
        setSize(WIDTH, HEIGHT);

        addButtonPanel();

        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();

        try {
            BufferedImage catPic = ImageIO.read(new File("./data/marbles.jpg"));
            JLabel catPicLabel = new JLabel();
            catPicLabel.setIcon(new ImageIcon(catPic));
            catPicLabel.setBounds(0, 0, catPicLabel.getPreferredSize().width, catPicLabel.getPreferredSize().height);
            add(catPicLabel);
        } catch (IOException e) {
            System.out.println("ERROR");
        }
        setVisible(true);
    }


    // MODIFIES: this
    // EFFECTS: creates buttons for all user actions
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,5));
        buttonPanel.add(new JButton(new AddCat()));
        buttonPanel.add(new JButton(new DisplayCats()));
        buttonPanel.add(new JButton(new AddFoster()));
        buttonPanel.add(new JButton(new DisplayFosters()));
        buttonPanel.add(new JButton(new Saver()));
        buttonPanel.add(new JButton(new Loader()));
        buttonPanel.add(new JButton(new Assigner()));
        buttonPanel.add(new JButton(new Unassigner()));
        addWindowListener(this);
        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }

   // Represents an action that assigns a foster to a cat
    private class Assigner extends AbstractAction {

        Assigner() {
            super("Assign Foster");
        }

        @Override
        // EFFECTS: prompts the user and assigns cat to foster based on given info
        public void actionPerformed(ActionEvent evt) {
            try {
                new ChooseCatGUI(shelter, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "FAILURE", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class Unassigner extends AbstractAction {

        Unassigner() {
            super("Unassign Foster");
        }

        @Override
        // EFFECTS: prompts the user and unassigns cat from any foster it potentially had
        public void actionPerformed(ActionEvent evt) {
            try {
                new ChooseCatGUI(shelter, true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "FAILURE", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Represents an action that has the user add a cat to the registry
    private class AddCat extends AbstractAction {

        AddCat() {
            super("Add Cat");
        }

        @Override
        // EFFECTS: prompts the user and adds cat based on given info
        public void actionPerformed(ActionEvent evt) {
            try {
                new CatGUI(shelter);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "FAILURE", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Represents an action that saves the registries info to file
    private class Saver extends AbstractAction {
        Saver() {
            super("Save to file");
        }

        @Override
        // EFFECTS: saves the shelter to file
        public void actionPerformed(ActionEvent evt) {
            try {
                jsonWriter.open();
                jsonWriter.write(shelter);
                jsonWriter.close();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "FAILURE", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Represents an action that loads the registries info from file
    private class Loader extends AbstractAction {
        Loader() {
            super("Loaded file");
        }

        @Override
        // EFFECTS: loads the shelter from file
        public void actionPerformed(ActionEvent evt) {
            try {
                shelter = jsonReader.read();

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "FAILURE", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }


   // Represents an action that has the user add a cat to the registry
    private class AddFoster extends AbstractAction {

        AddFoster() {
            super("Add Foster");
        }

        @Override
        // MODIFIES: this
        // EFFECTS: prompts the user and adds foster based on given info
        public void actionPerformed(ActionEvent evt) {
            try {
                new FosterGUI(shelter);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "FAILURE", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    // Represents an action that displays all cats in registry
    private class DisplayCats extends AbstractAction {

        DisplayCats() {
            super("Display Cats");
        }

        @Override
        // EFFECTS: Displays all cats in registry to user
        public void actionPerformed(ActionEvent evt)  {
            JFrame catFrame = new JFrame();
            JPanel catPanel = new JPanel();
            catFrame.setSize(300,shelter.getCats().size() * 45 + 100);
            catFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            catFrame.add(catPanel);

            catPanel.setLayout(null);
            int i = 0;
            for (Cat cat: shelter.getCats()) {
                i++;
                JLabel nameLabel = new JLabel(cat.getName());
                nameLabel.setBounds(10,i * 20,80,25);
                catPanel.add(nameLabel);
            }
            catFrame.setVisible(true);
        }
    }

    // Represents an action that displays all fosters in registry
    private class DisplayFosters extends AbstractAction {

        DisplayFosters() {
            super("Display Fosters");
        }

        @Override
        // EFFECTS: Displays all fosters in registry to user
        public void actionPerformed(ActionEvent evt)  {
            JFrame fosterFrame = new JFrame();
            JPanel fosterPanel = new JPanel();
            fosterFrame.setSize(300,shelter.getCats().size() * 45 + 100);
            fosterFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            fosterFrame.add(fosterPanel);

            fosterPanel.setLayout(null);
            int i = 0;
            for (Foster foster: shelter.getFosters()) {
                i++;
                JLabel nameLabel = new JLabel(foster.getName());
                nameLabel.setBounds(10,i * 20,80,25);
                fosterPanel.add(nameLabel);
            }
            fosterFrame.setVisible(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: centers the panel on the screen
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // Represents a mouse that can click and focus program on click
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        // EFFECTS: grabs the focus of the program
        public void mouseClicked(MouseEvent e) {
            GUI.this.requestFocusInWindow();
        }
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

}
