package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Observer for AssignGUI

public class AssignObserver implements ActionListener {
    AssignGUI assignGUI;

    // EFFECTS: assign this gui to parameter
    public AssignObserver(AssignGUI gui) {
        this.assignGUI = gui;
    }

    @Override
    // EFFECTS: execute the action for the observed assignGUI
    public void actionPerformed(ActionEvent e) {
        assignGUI.action();
    }
}
