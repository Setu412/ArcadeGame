package ca.sfu.cmpt276.spring2021.group8.project.GUI;

import javax.swing.*;
import java.awt.*;

public class HowToPlayMenu extends JPanel{
    JPanel storyPanel =new JPanel();
    JPanel rulesPanel =new JPanel();
    JPanel mainMenuPanel=new JPanel();
    
    JTextArea storyText= new JTextArea();
    JTextArea rulesText= new JTextArea();
    JButton mainMenuBtn=new JButton("Back to Main Menu");

    Font buttonFont=new Font("Times New Roman",Font.PLAIN,60);

    public HowToPlayMenu()
    {
        this.setBackground(GUIConfigurations.BACKGROUND_COLOR);
        this.setLayout(null);
        
        this.prepareStoryPanel();
        this.prepareMainMenuPanel();
        this.prepareRulesPanel();

    }

    public JButton getMainMenuBtn() {
        return mainMenuBtn;
    }

    public void prepareStoryPanel()
    {
        
    }

    public void prepareRulesPanel()
    {

    }

    public void prepareMainMenuPanel()
    {
        mainMenuPanel.setBounds(370,550,539,87);
        mainMenuPanel.setBackground(this.getBackground());
        this.add(mainMenuPanel);
        mainMenuBtn.setFont(buttonFont);
        mainMenuBtn.setBackground(GUIConfigurations.COMPONENT_COLOR);
        mainMenuBtn.setForeground(GUIConfigurations.TEXT_COLOR);
        mainMenuBtn.setBorderPainted(false);
        mainMenuBtn.setFocusPainted(false);
        mainMenuPanel.add(mainMenuBtn);
    }
}
