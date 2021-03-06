package GUI;

import GUI.HowToPlay.HowToPlayPanel;
import GUI.MainMenu.MainMenuPanel;

import javax.swing.*;
import java.awt.*;

public class GUIPanelContainer extends JPanel {
    CardLayout cardLayout=new CardLayout();
    MainMenuPanel mainMenuPanel=new MainMenuPanel();
    HowToPlayPanel howToPlayPanel=new HowToPlayPanel();

    public GUIPanelContainer()
    {
        this.setLayout(cardLayout);
        this.add(mainMenuPanel,"1");
        this.add(howToPlayPanel,"2");
        cardLayout.show(this,"1");
        mainMenuPanel.getHowToPlayButton().addActionListener(e->
                cardLayout.show(this,"2"));
        howToPlayPanel.getMainMenuButton().addActionListener(e->
                cardLayout.show(this,"1"));
    }

    public MainMenuPanel getMainMenuPanel() {
        return mainMenuPanel;
    }
}
