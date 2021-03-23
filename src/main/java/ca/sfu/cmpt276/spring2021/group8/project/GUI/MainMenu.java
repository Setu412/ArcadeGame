package ca.sfu.cmpt276.spring2021.group8.project.GUI;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel{
    JPanel titlePanel=new JPanel();
    JPanel howToPlayPanel=new JPanel();
    JPanel startGamePanel=new JPanel();
    JLabel titleText =new JLabel(); //Can change to picture of the designed title later on
    JButton startGameBtn=new JButton("Start Game");
    JButton howToPlayBtn=new JButton("How to Play");


    public MainMenu()
    {
        this.setBackground(GUIConfigurations.BACKGROUND_COLOR);
        this.setLayout(null);

        this.prepareTitlePanel();
        this.prepareStartGamePanel();
        this.prepareHowToPlayPanel();
    }

    public JButton getStartGameBtn() {
        return startGameBtn;
    }

    public JButton getHowToPlayBtn() {
        return howToPlayBtn;
    }

    private void prepareTitlePanel()
    {
        titlePanel.setBounds(165,110,950,250);
        titlePanel.setBackground(GUIConfigurations.COMPONENT_COLOR);
        this.add(titlePanel);

        titleText.setForeground(GUIConfigurations.TEXT_COLOR);
        titleText.setFont(GUIConfigurations.TITLE_FONT);
        titleText.setBackground(titlePanel.getBackground());
        titleText.setText("<html><body style='text-align:center'> How to Not Fail <br> University </body><html>");
        titleText.setHorizontalAlignment(JLabel.CENTER);
        titleText.setVerticalAlignment(JLabel.CENTER);
        titleText.setBounds(titlePanel.getBounds());
        //titleText.setBorder(BorderFactory.createLineBorder(Color.black));
        titleText.setAlignmentX(SwingConstants.CENTER);
        titleText.setAlignmentY(SwingConstants.CENTER);

        titlePanel.add(titleText);
    }
    
    private void prepareStartGamePanel()
    {
        startGamePanel.setBounds(370,430,539,87);
        startGamePanel.setBackground(this.getBackground());
        this.add(startGamePanel);
        GUIConfigurations.setBtnUI(startGameBtn);
        startGamePanel.add(startGameBtn);
    }
    
    private void prepareHowToPlayPanel()
    {
        howToPlayPanel.setBounds(370,550,539,87);
        howToPlayPanel.setBackground(this.getBackground());
        this.add(howToPlayPanel);
        GUIConfigurations.setBtnUI(howToPlayBtn);
        howToPlayPanel.add(howToPlayBtn);
    }

}