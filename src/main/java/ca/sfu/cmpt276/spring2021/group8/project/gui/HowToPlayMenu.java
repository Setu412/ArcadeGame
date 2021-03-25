package ca.sfu.cmpt276.spring2021.group8.project.gui;

import javax.swing.*;
import java.awt.*;

/**
 * The GUI menu that has the instructions on how to play the game
 * Contains:
 * A summary of the story the game is set
 * The rules of the game and how to play the game
 * A button to go back to the main menu
 */
public class HowToPlayMenu extends JPanel{
    JPanel storyPanel =new JPanel();
    JPanel rulesPanel =new JPanel();
    JPanel mainMenuPanel=new JPanel();
    
    JLabel storyText= new JLabel();
    JLabel rulesText= new JLabel();
    JButton mainMenuBtn=new JButton("Back to Main Menu");

    /**
     * Creates a new how to play menu
     * @see #prepareMainMenuPanel()
     * @see #prepareRulesPanel()
     * @see #prepareStoryPanel()
     */
    public HowToPlayMenu()
    {
        this.setBackground(GUIConfigurations.BACKGROUND_COLOR);
        this.setLayout(null);
        
        this.prepareStoryPanel();
        this.prepareMainMenuPanel();
        this.prepareRulesPanel();

    }

    /**
     * Returns the main menu button
     */
    public JButton getMainMenuBtn() {
        return mainMenuBtn;
    }

    /**
     * Prepares the panel which contains the story of the game.
     * Sets up the background color, bounds, borders, text of the content, text font, and text color
     */
    private void prepareStoryPanel()
    {
        storyPanel.setBackground(GUIConfigurations.SECONDARY_COMPONENT_COLOR);
        storyPanel.setBounds(53,25,1182,161);
        storyPanel.setBorder(BorderFactory.createLineBorder(GUIConfigurations.LETTER_TEXT_COLOR,2));
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
        storyText.setForeground(GUIConfigurations.LETTER_TEXT_COLOR);
        storyPanel.add(storyText);
    }

    /**
     * Prepares the panel which contains the rules of the game.
     * Sets up the background color, bounds, borders, text of the content, text font, and text color
     */
    private void prepareRulesPanel()
    {
        rulesPanel.setBackground(GUIConfigurations.SECONDARY_COMPONENT_COLOR);
        rulesPanel.setBounds(53,205,1182,400);
        rulesPanel.setBorder(BorderFactory.createLineBorder(GUIConfigurations.LETTER_TEXT_COLOR,2));
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
                +"- The A grades randomly appear for a short period of time and are worth 5 points each."
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
                +"- You can navigate the player by WASD or arrow keys. Press q or esc to quit the game and go back to the main menu"
        );
        rulesText.setForeground(GUIConfigurations.LETTER_TEXT_COLOR);
        rulesText.setFont(GUIConfigurations.RULES_FONT);

        rulesPanel.add(rulesText,BorderLayout.SOUTH);
    }

    /**
     * Prepares the panel which contains the back to main menu button
     * Sets up the bounds, background color of the panel, and sets up the button GUI
     * @see GUIConfigurations#setBtnUI(JButton) 
     */
    private void prepareMainMenuPanel()
    {
        mainMenuPanel.setBounds(370,630,539,87);
        mainMenuPanel.setBackground(this.getBackground());
        this.add(mainMenuPanel);
        GUIConfigurations.setBtnUI(mainMenuBtn);
        mainMenuPanel.add(mainMenuBtn);
    }
}
