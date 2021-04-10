package ca.sfu.cmpt276.spring2021.group8.project.gui;

import javax.swing.*;
import java.awt.*;

/**
 * A GUI template for ending screens (winning and losing screen).
 * Contains:
 * A letter (diploma or letter of expulsion, depending on wheter or not its a winning or losing screen)
 * A text label of the final score of the player
 * A text label of the final time of the player
 * A button to go back to the main menu
 * A button to play again
 * @see LosingScreen
 * @see WinningScreen
 */
public abstract class EndingScreen extends JPanel {
    JPanel letterPanel=new JPanel();
    JPanel letterTitlePanel=new JPanel();
    JPanel letterBodyPanel=new JPanel();
    JPanel scorePanel= new JPanel();
    JPanel timePanel=new JPanel();
    JPanel mainMenuPanel=new JPanel();
    JPanel playAgainPanel=new JPanel();

    JLabel letterTitleText=new JLabel();
    JLabel letterBodyText=new JLabel();
    JLabel scoreText=new JLabel();
    JLabel timeText=new JLabel();
    JButton mainMenuBtn=new JButton("Main Menu");
    JButton playAgainBtn=new JButton("Play Again");

    /**
     * Creates a new ending screen
     * @see #prepareLetterPanel()
     * @see #prepareScorePanel()
     * @see #prepareScorePanel()
     * @see #preparePlayAgainPanel()
     * @see #prepareTimePanel()
     */
    public EndingScreen()
    {
        this.setBackground(GUIConfigurations.BACKGROUND_COLOR);
        this.setLayout(null);

        this.prepareLetterPanel();
        this.prepareMainMenuPanel();
        this.prepareScorePanel();
        this.preparePlayAgainPanel();
        this.prepareTimePanel();
    }

    /**
     * Prepares the body content of the letters for the respective ending screens
     * @see LosingScreen#prepareLetterContent()
     * @see WinningScreen#prepareLetterContent()
     */
    abstract void prepareLetterContent();

    /**
     * Prepares the panel which contains the letter
     * Sets the bounds, background, and border of the panel and underlying panels for the body and text of the letter.
     */
    private void prepareLetterPanel()
    {
        this.add(letterPanel);
        letterPanel.setBounds(142,50,995,450);
        letterPanel.setBackground(GUIConfigurations.LETTER_BACKGROUND_COLOR);
        letterPanel.setBorder(BorderFactory.createLineBorder(Color.black,4));
        letterPanel.setLayout(null);

        letterPanel.add(letterTitlePanel);
        letterTitlePanel.setBounds(240,23,516,94);
        letterTitlePanel.setBackground(letterPanel.getBackground());
        letterTitlePanel.add(letterTitleText);

        letterPanel.add(letterBodyPanel);
        letterBodyPanel.setBounds(73,139,850,279);
        letterBodyPanel.setBackground(letterPanel.getBackground());
        letterBodyPanel.add(letterBodyText);

        prepareLetterContent();
    }

    /**
     * Returns the main menu button
     */
    public JButton getMainMenuBtn() {
        return mainMenuBtn;
    }

    /**
     * Returns the play again button
     */
    public JButton getPlayAgainBtn() {
        return playAgainBtn;
    }

    /**
     * returns the text label for final score
     */
    public JLabel getScoreText() {
        return scoreText;
    }

    /**
     * returns the text label for final time
     */
    public JLabel getTimeText() {
        return timeText;
    }

    /**
     * Prepares the panel which contains the final score of the player
     * Sets the boundary, background color, borders, text color, and text font
     */
    private void prepareScorePanel()
    {
        this.add(scorePanel);
        scorePanel.setBounds(422,540,210,60);
        scorePanel.setBackground(GUIConfigurations.SECONDARY_COMPONENT_COLOR);
        scorePanel.setBorder(BorderFactory.createLineBorder(GUIConfigurations.LETTER_TEXT_COLOR,2));
        scoreText.setForeground(GUIConfigurations.LETTER_TEXT_COLOR);
        scoreText.setFont(GUIConfigurations.RESULT_FONT);

        scorePanel.add(scoreText);

    }

    /**
     * Prepares the panel which contains the final time of the player
     * Sets the boundary, background color, borders, text color, and text font
     */
    private void prepareTimePanel()
    {
        this.add(timePanel);
        timePanel.setBounds(647,540,210,60);
        timePanel.setBackground(GUIConfigurations.SECONDARY_COMPONENT_COLOR);
        timePanel.setBorder(BorderFactory.createLineBorder(GUIConfigurations.LETTER_TEXT_COLOR,2));
        timeText.setForeground(GUIConfigurations.LETTER_TEXT_COLOR);
        timeText.setFont(GUIConfigurations.RESULT_FONT);

        timePanel.add(timeText);
    }

    /**
     * Prepares the panel which contains the play again button
     * Sets the boundary of the panel and background color of the panel, sets up and add the button to the panel
     * @see GUIConfigurations#setBtnUI(JButton)
     */
    private void preparePlayAgainPanel()
    {
        this.add(playAgainPanel);
        playAgainPanel.setBounds(250,630,322,87);
        playAgainPanel.setBackground(this.getBackground());

        playAgainPanel.add(playAgainBtn);
        GUIConfigurations.setBtnUI(playAgainBtn);
    }

    /**
     * Prepares the panel which contains the main menu button
     * Sets the boundary of the panel and background color of the panel, sets up and add the button to the panel
     * @see GUIConfigurations#setBtnUI(JButton)
     */
    private void prepareMainMenuPanel()
    {
        this.add(mainMenuPanel);
        mainMenuPanel.setBounds(705,630,350,87);
        mainMenuPanel.setBackground(this.getBackground());

        mainMenuPanel.add(mainMenuBtn);
        GUIConfigurations.setBtnUI(mainMenuBtn);
    }
}
