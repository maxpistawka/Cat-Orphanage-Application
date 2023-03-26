package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Observer for CatGUI
public class CatActionObserver implements ActionListener {

    CatGUI catGUI;

    // EFFECTS: assign this gui to parameter
    public CatActionObserver(CatGUI gui) {
        this.catGUI = gui;
    }

    @Override
    // EFFECTS: execute the action for the observed catGUI
    public void actionPerformed(ActionEvent e) {
        catGUI.action();
    }
}
