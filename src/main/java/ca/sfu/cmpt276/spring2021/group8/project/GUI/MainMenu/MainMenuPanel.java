package GUI.MainMenu;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    JButton startGameButton=new JButton("Start Game");
    JButton howToPlayButton=new JButton("How To Play");

    public MainMenuPanel()
    {
        this.add(startGameButton);
        this.add(howToPlayButton);
        this.setBackground(Color.LIGHT_GRAY);
    }

    public JButton getHowToPlayButton() {
        return howToPlayButton;
    }
    public JButton getStartGameButton() { return startGameButton; }
}
