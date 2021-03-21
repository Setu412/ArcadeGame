package ca.sfu.cmpt276.spring2021.group8.project.GUI;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class MainMenu extends JPanel{
    JPanel titlePanel=new JPanel();
    JPanel howToPlayPanel=new JPanel();
    JPanel startGamePanel=new JPanel();
    JLabel titleText =new JLabel(); //Can change to picture of the designed title later on
    Font titleFont= new Font("Times New Roman", Font.PLAIN, 103);
    Font buttonFont=new Font("Times New Roman",Font.PLAIN,64);
    final Color TITLEPANEL_COLOR=Color.gray;
    final Color TEXT_COLOR=Color.white;
    final Color BUTTON_COLOR=Color.red;
    JButton startGameBtn=new JButton("Start Game");
    JButton howToPlayBtn=new JButton("How to Play");


    public MainMenu()
    {
        this.setBackground(TITLEPANEL_COLOR);
        this.setSize(1280,720);
        this.setLayout(null);

        this.setTitlePanel();
        this.setStartGamePanel();
        this.setHowToPlayPanel();
    }

    private void setTitlePanel()
    {
        titlePanel.setBounds(165,110,950,250);
        titlePanel.setBackground(this.getBackground());
        this.add(titlePanel);
        titleText.setForeground(TEXT_COLOR);
        titleText.setFont(titleFont);
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
    
    private void setStartGamePanel()
    {
        startGamePanel.setBounds(370,430,539,87);
        startGamePanel.setBackground(this.getBackground());
        this.add(startGamePanel);
        this.setBtnConfig(startGameBtn);
        startGamePanel.add(startGameBtn);
    }
    
    private void setHowToPlayPanel()
    {
        howToPlayPanel.setBounds(370,550,539,87);
        howToPlayPanel.setBackground(this.getBackground());
        this.add(howToPlayPanel);
        this.setBtnConfig(howToPlayBtn);
        howToPlayPanel.add(howToPlayBtn);
    }
    
    private void setBtnConfig(JButton btn)
    {
        btn.setFont(buttonFont);
        btn.setBackground(BUTTON_COLOR);
        btn.setForeground(TEXT_COLOR);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
    }

}
