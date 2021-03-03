package GUI;

import javax.swing.*;
import java.awt.*;

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
}
