package ca.sfu.cmpt276.spring2021.group8.project.gui;

import javax.swing.*;

/**
 * The GUI menu of the menu when the player starts the game, the default starting menu
 * Contains:
 * The title of the game
 * A start game button
 * A how-to-play button
 */
public class MainMenu extends JPanel{
    JPanel titlePanel=new JPanel();
    JPanel howToPlayPanel=new JPanel();
    JPanel startGamePanel=new JPanel();
    JLabel titleText =new JLabel(); //Can change to picture of the designed title later on
    JButton startGameBtn=new JButton("Start Game");
    JButton howToPlayBtn=new JButton("How to Play");


    /**
     * Creates a new main menu
     * @see #prepareHowToPlayPanel()
     * @see #prepareStartGamePanel()
     * @see #prepareTitlePanel()
     */
    public MainMenu()
    {
        this.setBackground(GUIConfigurations.BACKGROUND_COLOR);
        this.setLayout(null);

        this.prepareTitlePanel();
        this.prepareStartGamePanel();
        this.prepareHowToPlayPanel();
    }

    /**
     * returns the start game button
     */
    public JButton getStartGameBtn() {
        return startGameBtn;
    }

    /**
     * returns the how to play button
     */
    public JButton getHowToPlayBtn() {
        return howToPlayBtn;
    }

    /**
     * Prepares the panel which contains the title of the game
     * Sets up bounds, background color, text color, text font, text content and alignment
     */
    private void prepareTitlePanel()
    {
        titlePanel.setBounds(165,110,950,290);
        titlePanel.setBackground(GUIConfigurations.PRIMARY_COMPONENT_COLOR);
        this.add(titlePanel);

        titleText.setForeground(GUIConfigurations.TEXT_COLOR);
        titleText.setFont(GUIConfigurations.TITLE_FONT);
        titleText.setBackground(titlePanel.getBackground());
        titleText.setText("<html><body style='width:800 px; text-align:center'> <em>How to Not Fail University</em> </body><html>");
        titleText.setHorizontalAlignment(JLabel.CENTER);
        titleText.setVerticalAlignment(JLabel.CENTER);
        titleText.setBounds(titlePanel.getBounds());
        //titleText.setBorder(BorderFactory.createLineBorder(Color.black));
        titleText.setAlignmentX(SwingConstants.CENTER);
        titleText.setAlignmentY(SwingConstants.CENTER);

        titlePanel.add(titleText);
    }

    /**
     * Prepares the panel which contains the start game button
     * Sets up bounds, background color, and sets up the button
     * @see GUIConfigurations#setBtnUI(JButton)
     */
    private void prepareStartGamePanel()
    {
        startGamePanel.setBounds(464,510,350,87);
        startGamePanel.setBackground(this.getBackground());
        this.add(startGamePanel);
        GUIConfigurations.setBtnUI(startGameBtn);
        startGamePanel.add(startGameBtn);
    }

    /**
     * Prepares the panel which contains the how to play button
     * Sets up bounds, background color, and sets up the button
     * @see GUIConfigurations#setBtnUI(JButton)
     */
    private void prepareHowToPlayPanel()
    {
        howToPlayPanel.setBounds(464,630,350,87);
        howToPlayPanel.setBackground(this.getBackground());
        this.add(howToPlayPanel);
        GUIConfigurations.setBtnUI(howToPlayBtn);
        howToPlayPanel.add(howToPlayBtn);
    }

}
