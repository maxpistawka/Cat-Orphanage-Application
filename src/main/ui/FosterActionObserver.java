package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents observer for fosterGUI
public class FosterActionObserver implements ActionListener {

    FosterGUI fosterGUI;

    // EFFECTS: assign this gui to parameter
    public FosterActionObserver(FosterGUI gui) {
        this.fosterGUI = gui;
    }

    @Override
    // EFFECTS: execute the action for the observed fosterGUI
    public void actionPerformed(ActionEvent e) {
        fosterGUI.action();
    }
}
