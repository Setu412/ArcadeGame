package ca.sfu.cmpt276.spring2021.group8.project.gui;

import javax.swing.*;
import java.awt.*;

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

    public abstract void prepareLetterContent();

    public void prepareLetterPanel()
    {
        this.add(letterPanel);
        letterPanel.setBounds(142,42,995,436);
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

    public JButton getMainMenuBtn() {
        return mainMenuBtn;
    }

    public JButton getPlayAgainBtn() {
        return playAgainBtn;
    }

    public void prepareScorePanel()
    {
        this.add(scorePanel);
        scorePanel.setBounds(237,506,301,51);
        scorePanel.setBackground(Color.blue);

        scorePanel.add(scoreText);

    }

    public void prepareTimePanel()
    {
        this.add(timePanel);
        timePanel.setBounds(742,506,301,51);
        timePanel.setBackground(Color.blue);

        timePanel.add(timeText);
    }

    public void preparePlayAgainPanel()
    {
        this.add(playAgainPanel);
        playAgainPanel.setBounds(142,579,490,87);
        playAgainPanel.setBackground(this.getBackground());

        playAgainPanel.add(playAgainBtn);
        GUIConfigurations.setBtnUI(playAgainBtn);
    }

    public void prepareMainMenuPanel()
    {
        this.add(mainMenuPanel);
        mainMenuPanel.setBounds(648,579,490,87);
        mainMenuPanel.setBackground(this.getBackground());

        mainMenuPanel.add(mainMenuBtn);
        GUIConfigurations.setBtnUI(mainMenuBtn);
    }
}