package ca.sfu.cmpt276.spring2021.group8.project.GUI;

import javax.swing.*;
import java.awt.*;

public class HowToPlayMenu extends JPanel{
    JPanel storyPanel =new JPanel();
    JPanel rulesPanel =new JPanel();
    JPanel mainMenuPanel=new JPanel();
    
    JLabel storyText= new JLabel();
    JLabel rulesText= new JLabel();
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
        storyPanel.setBackground(GUIConfigurations.COMPONENT_COLOR);
        storyPanel.setBounds(53,25,1182,161);
        this.add(storyPanel);

        storyText.setText(
                "<html><body style='width: 900 px; text-align:center'>"+
                "You are a university student trying to collect the credits you need to graduate from university. " +
                "However, for one reason or another, the professors in this university wants you expelled! "+
                "Maybe you ask too many questions or snore too loud in class, but now the professors are chasing you down, and if they catch you, your life as a university student is over. "+
                "<br>"+
                "Do you have what it takes to collect all your required credits and graduate from university? "+
                "</body></html>");
        storyText.setFont(GUIConfigurations.TEXT_FONT);
        storyText.setBounds(storyPanel.getBounds());
        storyText.setForeground(GUIConfigurations.TEXT_COLOR);
        storyPanel.add(storyText);
    }

    public void prepareRulesPanel()
    {
        rulesPanel.setBackground(GUIConfigurations.COMPONENT_COLOR);
        rulesPanel.setBounds(53,205,1182,330);
        this.add(rulesPanel);

        rulesText.setText(
                "<html><body style='width: 900 px; text-align:left'>"
                +"Rules:"
                +"<br>"
                +GUIConfigurations.tab
                +"1. The goal is to collect all required credits without getting caught by professors."
                +"<br>"
                +GUIConfigurations.tab
                +"2. Credits:"
                +"<br>"
                +GUIConfigurations.tab+GUIConfigurations.tab
                +"- The P (passing) grades are your required credits, and are worth 2 points each."
                +"<br>"
                +GUIConfigurations.tab+GUIConfigurations.tab
                +"- The A+ grades randomly appear for a short period of time and are worth 5 points each."
                + "<br>"
                +GUIConfigurations.tab
                +"Punishments:"
                +"<br>"
                +GUIConfigurations.tab+GUIConfigurations.tab
                +"- F grades deduct 5 points from your score."
                +"<br>"
                +GUIConfigurations.tab+GUIConfigurations.tab
                +"- You lose the game is your score goes below 0."
                +"<br>"
                +GUIConfigurations.tab+GUIConfigurations.tab
                +"- If you are caught by a professor, you lose the game."
                +"<br>"
                +GUIConfigurations.tab
                +"Barriers:"
                +"<br>"
                +GUIConfigurations.tab+GUIConfigurations.tab
                +"- Watch out for construction zones! Construction zones will appear randomly, you won't be able to go through a construction zone"
                +"<br>"
                +GUIConfigurations.tab
                +"3. After collecting all the P grades, the exit is unlocked. Go through the exit to win the game"
                +"<br>"+"<br>"
                +"How to Play:"
                +"<br>"
                +GUIConfigurations.tab
                +"- You can navigate the player by WASD or arrow keys."
        );
        rulesText.setForeground(GUIConfigurations.TEXT_COLOR);
        rulesText.setFont(GUIConfigurations.RULES_FONT);

        rulesPanel.add(rulesText,BorderLayout.SOUTH);
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
