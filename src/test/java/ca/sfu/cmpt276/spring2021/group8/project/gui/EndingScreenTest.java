package ca.sfu.cmpt276.spring2021.group8.project.gui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EndingScreenTest {
    class MockEndingScreen extends EndingScreen
    {

        @Override
        void prepareLetterContent() {
            //Empty, the letter content will be tested in WinningScreen and LosingScreen
        }
    }

    MockEndingScreen mockEndingScreen=new MockEndingScreen();

    @Test
    public void testBackgroundColor()
    {
        assert (mockEndingScreen.getBackground()==GUIConfigurations.BACKGROUND_COLOR);
    }

    @Test
    public void testLayout()
    {
        assert (mockEndingScreen.getLayout()==null);
    }

    @Nested
    class TestPrepareLetterPanel
    {
        @Test
        public void testEndingScreenContainsLetterPanel()
        {
            assert (Arrays.asList(mockEndingScreen.getComponents()).contains(mockEndingScreen.letterPanel));
        }

        @Test
        public void testLetterPanelConfigurations() {
            JPanel mockLetterPanel = new JPanel();
            mockLetterPanel.setBounds(142, 50, 995, 450);
            mockLetterPanel.setBackground(GUIConfigurations.LETTER_BACKGROUND_COLOR);
            mockLetterPanel.setBorder(BorderFactory.createLineBorder(Color.black, 4));

            assert (mockEndingScreen.letterPanel.getBounds().equals(mockLetterPanel.getBounds()));
            assert (mockEndingScreen.letterPanel.getBackground().equals(mockLetterPanel.getBackground()));
            assert (mockEndingScreen.letterPanel.getBorder().getBorderInsets(mockEndingScreen.letterPanel).equals(mockLetterPanel.getBorder().getBorderInsets(mockLetterPanel)));
            assert (mockEndingScreen.letterPanel.getLayout()==null);
        }

        @Test
        public void testLetterPanelContainsLetterTitlePanel()
        {
            List letterPanelComponents=Arrays.asList(mockEndingScreen.letterPanel.getComponents());
            assert (letterPanelComponents.contains(mockEndingScreen.letterTitlePanel));
        }

        @Test
        public void testLetterTitlePanelConfigurations()
        {
            JPanel mockLetterTitlePanel=new JPanel();
            mockLetterTitlePanel.setBounds(240,23,516,94);
            mockLetterTitlePanel.setBackground(mockEndingScreen.letterPanel.getBackground());

            assert (mockEndingScreen.letterTitlePanel.getBounds().equals(mockLetterTitlePanel.getBounds()));
            assert (mockEndingScreen.letterTitlePanel.getBackground().equals(mockLetterTitlePanel.getBackground()));
        }

        @Test
        public void testLetterTitlePanelContainsLetterTitleText()
        {
            assert (mockEndingScreen.letterTitleText.getParent()==mockEndingScreen.letterTitlePanel);
        }

        @Test
        public void testLetterPanelContainsLetterBodyPanel()
        {
            List letterPanelComponents=Arrays.asList(mockEndingScreen.letterPanel.getComponents());
            assert (letterPanelComponents.contains(mockEndingScreen.letterBodyPanel));
        }

        @Test
        public void testLetterBodyPanelConfigurations()
        {
            JPanel mockLetterBodyPanel=new JPanel();
            mockLetterBodyPanel.setBounds(73,139,850,279);
            mockLetterBodyPanel.setBackground(mockEndingScreen.letterPanel.getBackground());

            assert (mockEndingScreen.letterBodyPanel.getBounds().equals(mockLetterBodyPanel.getBounds()));
            assert (mockEndingScreen.letterBodyPanel.getBackground().equals(mockLetterBodyPanel.getBackground()));
        }

        @Test
        public void testLetterBodyPanelContainsLetterBodyText()
        {
            assert (mockEndingScreen.letterBodyText.getParent()==mockEndingScreen.letterBodyPanel);
        }
    }


    @Test
    public void testGetters()
    {
        assert (mockEndingScreen.getMainMenuBtn()==mockEndingScreen.mainMenuBtn);
        assert (mockEndingScreen.getPlayAgainBtn()==mockEndingScreen.playAgainBtn);
        assert (mockEndingScreen.getScoreText()==mockEndingScreen.scoreText);
        assert (mockEndingScreen.getTimeText()==mockEndingScreen.timeText);
    }

    @Nested
    class TestPrepareScorePanel
    {
        @Test
        public void testEndingScreenContainsScorePanel()
        {
            assert (mockEndingScreen.scorePanel.getParent()==mockEndingScreen);
        }
        
        @Test
        public void testScorePanelConfigurations()
        {
            JPanel mockScorePanel=new JPanel();
            mockScorePanel.setBounds(422,540,210,60);
            mockScorePanel.setBackground(GUIConfigurations.SECONDARY_COMPONENT_COLOR);
            mockScorePanel.setBorder(BorderFactory.createLineBorder(GUIConfigurations.LETTER_TEXT_COLOR,2));

            assert (mockEndingScreen.scorePanel.getBackground().equals(mockScorePanel.getBackground()));
            assert (mockEndingScreen.scorePanel.getBounds().equals(mockScorePanel.getBounds()));
            assert (mockEndingScreen.scorePanel.getBorder().getBorderInsets(mockEndingScreen.scorePanel)
                    .equals(mockScorePanel.getBorder().getBorderInsets(mockScorePanel)));
        }

        @Test
        public void testScoreTextConfigurations()
        {
            JLabel mockScoreText=new JLabel();
            mockScoreText.setForeground(GUIConfigurations.LETTER_TEXT_COLOR);
            mockScoreText.setFont(GUIConfigurations.RESULT_FONT);

            assert (mockEndingScreen.scoreText.getForeground().equals(mockScoreText.getForeground()));
            assert (mockEndingScreen.scoreText.getFont().equals(mockScoreText.getFont()));
        }

        @Test
        public void testScorePanelContainsScoreText()
        {
            assert (mockEndingScreen.scoreText.getParent()==mockEndingScreen.scorePanel);
        }
    }

    @Nested
    class testPrepareTimePanel
    {
        @Test
        public void testEndingScreenContainsTimePanel()
        {
            assert (mockEndingScreen.timePanel.getParent()==mockEndingScreen);
        }

        @Test
        public void testTimePanelConfigurations()
        {
            JPanel mockTimePanel=new JPanel();
            mockTimePanel.setBounds(647,540,210,60);
            mockTimePanel.setBackground(GUIConfigurations.SECONDARY_COMPONENT_COLOR);
            mockTimePanel.setBorder(BorderFactory.createLineBorder(GUIConfigurations.LETTER_TEXT_COLOR,2));

            assert (mockEndingScreen.timePanel.getBounds().equals(mockTimePanel.getBounds()));
            assert (mockEndingScreen.timePanel.getBackground().equals(mockTimePanel.getBackground()));
            assert (mockEndingScreen.timePanel.getBorder().getBorderInsets(mockEndingScreen.timePanel)
                .equals(mockTimePanel.getBorder().getBorderInsets(mockTimePanel)));
        }

        @Test
        public void testTimeTextConfigurations()
        {
            JLabel mockTimeLabel=new JLabel();
            mockTimeLabel.setForeground(GUIConfigurations.LETTER_TEXT_COLOR);
            mockTimeLabel.setFont(GUIConfigurations.RESULT_FONT);

            assert (mockEndingScreen.timeText.getForeground().equals(mockTimeLabel.getForeground()));
            assert (mockEndingScreen.timeText.getFont().equals(mockTimeLabel.getFont()));
        }

        @Test
        public void testTimePanelContainsTimeText()
        {
            assert (mockEndingScreen.timeText.getParent()==mockEndingScreen.timePanel);
        }
    }

    @Nested
    class testPreparePlayAgainPanel
    {
        @Test
        public void testEndingScreenContainsPlayAgainPanel()
        {
            assert (mockEndingScreen.playAgainPanel.getParent()==mockEndingScreen);
        }

        @Test
        public void testPlayAgainPanelConfigurations()
        {
            JPanel mockPlayAgainPanel =new JPanel();
            mockPlayAgainPanel.setBounds(250,630,322,87);
            mockPlayAgainPanel.setBackground(mockEndingScreen.getBackground());

            assert (mockEndingScreen.playAgainPanel.getBounds().equals(mockPlayAgainPanel.getBounds()));
            assert (mockEndingScreen.playAgainPanel.getBackground().equals(mockPlayAgainPanel.getBackground()));
        }

        @Test
        public void testPlayAgainPanelContainsPlayAgainBtn()
        {
            assert (mockEndingScreen.playAgainBtn.getParent()==mockEndingScreen.playAgainPanel);
        }

        //PlayAgainBtn configurations are tested in GUIConfigurationsTest to not repeat testing
    }

    @Nested
    class testPrepareMainMenuPanel
    {
        @Test
        public void testEndingScreenContainsMainMenuPanel()
        {
            assert (mockEndingScreen.mainMenuPanel.getParent()==mockEndingScreen);
        }

        @Test
        public void testMainMenuPanelConfigurations()
        {
            JPanel mockMainMenuPanel =new JPanel();
            mockMainMenuPanel.setBounds(705,630,350,87);
            mockMainMenuPanel.setBackground(mockEndingScreen.getBackground());

            assert (mockEndingScreen.mainMenuPanel.getBounds().equals(mockMainMenuPanel.getBounds()));
            assert (mockEndingScreen.mainMenuPanel.getBackground().equals(mockMainMenuPanel.getBackground()));
        }

        @Test
        public void testMainMenuPanelContainsMainMenuBtn()
        {
            assert (mockEndingScreen.mainMenuBtn.getParent()==mockEndingScreen.mainMenuPanel);
        }

        //MainMenuBtn configurations are tested in GUIConfigurationsTest to not repeat testing
    }

}