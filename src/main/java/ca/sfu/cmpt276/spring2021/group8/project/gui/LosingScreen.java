package ca.sfu.cmpt276.spring2021.group8.project.gui;

/**
 * The screen that shows up when the player loses the game
 * @see EndingScreen
 */
public class LosingScreen extends EndingScreen{
    /**
     * Prepares the content of the letter of expulsion
     * Sets the font, text color, and text content of the letter
     * @see EndingScreen#prepareLetterContent()
     */
    @Override
    void prepareLetterContent() {
        letterTitleText.setText("<html>"+"GAME OVER"+"<hr style='width:100%'></html>");
        letterTitleText.setFont(GUIConfigurations.EXPULSION_TITLE_FONT);
        letterTitleText.setForeground(GUIConfigurations.PRIMARY_COMPONENT_COLOR);

        letterBodyText.setText(
                "<html><body style='width: 600 px; text-align:center'>"
                +"<b><u>LETTER OF EXPULSION</u></b>"
                +"<br><br>"
                +"By the suggestion of Professor Name mcFake, "
                +"<br>"
                +"<b>YOU</b>"
                +"<br>"
                +"are hereby expelled from Fake University due to mediocre gameplay skills."
        );
        letterBodyText.setFont(GUIConfigurations.LETTER_TEXT_FONT);
    }
}
