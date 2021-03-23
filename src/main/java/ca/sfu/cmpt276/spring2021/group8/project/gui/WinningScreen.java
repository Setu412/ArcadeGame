package ca.sfu.cmpt276.spring2021.group8.project.gui;

public class WinningScreen extends EndingScreen{

    @Override
    public void prepareLetterContent() {
        letterTitlePanel.setBackground(GUIConfigurations.COMPONENT_COLOR);

        letterTitleText.setText("Fake University");
        letterTitleText.setForeground(GUIConfigurations.TEXT_COLOR);
        letterTitleText.setFont(GUIConfigurations.DIPLOMA_TITLE_FONT);

        letterBodyText.setText(
                "<html><body style='width: 800 px; text-align:center'>"
                +"Congratulations!"
                +"<br>"
                +"The faculty of fake degrees hereby confer"
                +"<br><br>"
                +"<b> YOU! </b>"
                +"<br><br>"
                +"The degree of "
                +"<br>"
                +"<b> Bachelor of Fake Degrees </b>"
                +"</body></html>"
        );
        letterBodyText.setForeground(GUIConfigurations.LETTER_TEXT_COLOR);
        letterBodyText.setFont(GUIConfigurations.LETTER_TEXT_FONT);
    }
}
