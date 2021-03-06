package GUI;

import javax.swing.*;
import GUI.GUIPanelContainer;

public class GUIFrame extends JFrame {
    GUIPanelContainer panelContainer=new GUIPanelContainer();
    public GUIFrame()
    {
        this.add(panelContainer);
    }

    public void getUI()
    {
        setSize(700,700);
        setTitle("Test GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    public GUIPanelContainer getPanelContainer() {
        return panelContainer;
    }
}
