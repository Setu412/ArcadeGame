package ca.sfu.cmpt276.spring2021.group8.project.gui;

/**
 * The screen that shows up when the player wins the game
 * @see EndingScreen
 */
public class WinningScreen extends EndingScreen{

    /**
     * Prepares the content of the diploma
     * Sets the font, text color, and text content of the letter
     * @see EndingScreen#prepareLetterContent()
     */
    @Override
    void prepareLetterContent() {
        letterTitlePanel.setBackground(GUIConfigurations.PRIMARY_COMPONENT_COLOR);

        letterTitleText.setText("Fake University");
        letterTitleText.setForeground(GUIConfigurations.TEXT_COLOR);
        letterTitleText.setFont(GUIConfigurations.DIPLOMA_TITLE_FONT);

        letterBodyText.setText(
                "<html><body style='width: 800 px; text-align:center'>"
                +"Congratulations!"
                +"<br>"
                +"The faculty of games hereby confer"
                +"<br><br>"
                +"<b> YOU! </b>"
                +"<br><br>"
                +"The degree of "
                +"<br>"
                +"<b> Bachelor of Expert Game Skills </b>"
                +"</body></html>"
        );
        letterBodyText.setForeground(GUIConfigurations.LETTER_TEXT_COLOR);
        letterBodyText.setFont(GUIConfigurations.LETTER_TEXT_FONT);
    }
}
