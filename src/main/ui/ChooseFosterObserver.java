package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Observer for ChooseFosterGUI

public class ChooseFosterObserver implements ActionListener {
    ChooseFosterGUI chooseFosterGUI;

    // EFFECTS: assign this gui to parameter
    public ChooseFosterObserver(ChooseFosterGUI gui) {
        this.chooseFosterGUI = gui;
    }

    @Override
    // EFFECTS: execute the action for the observed chooseFosterGUI
    public void actionPerformed(ActionEvent e) {
        chooseFosterGUI.action();
    }
}
