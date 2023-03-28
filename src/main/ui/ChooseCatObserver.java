package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Observer for ChooseCatGUI
public class ChooseCatObserver implements ActionListener {

    ChooseCatGUI chooseCatGUI;

    // EFFECTS: assign this gui to parameter
    public ChooseCatObserver(ChooseCatGUI gui) {
        this.chooseCatGUI = gui;
    }

    @Override
    // EFFECTS: execute the action for the observed chooseCatGUI
    public void actionPerformed(ActionEvent e) {
        chooseCatGUI.action();
    }
}
