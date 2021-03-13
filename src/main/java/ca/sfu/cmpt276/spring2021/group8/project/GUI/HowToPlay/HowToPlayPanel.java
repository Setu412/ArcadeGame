package GUI.HowToPlay;

import javax.swing.*;
import java.awt.*;

public class HowToPlayPanel extends JPanel {
    JButton mainMenuButton=new JButton("Main Menu");

    public HowToPlayPanel()
    {
        this.add(mainMenuButton);
        this.setBackground(Color.DARK_GRAY);
    }

    public JButton getMainMenuButton() {
        return mainMenuButton;
    }
}
