package ca.sfu.cmpt276.spring2021.group8.project.gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * The screen that shows up when the player wins the game
 * @see EndingScreen
 */
public class WinningScreen extends EndingScreen{

    private Clip winningSong;

    /**
     * Creates a new WinningScreen and plays winning song
     * @see EndingScreen#EndingScreen()
     */
    public WinningScreen()
    {
        super();
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/Audio/PompAndCircumstance.wav").getAbsoluteFile());
            winningSong = AudioSystem.getClip();
            winningSong.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts playing the winning song from the beginning
     */
    public void startMusic()
    {
        winningSong.setFramePosition(0);
        winningSong.start();
    }

    /**
     * Stop playing the winning song
     */
    public void stopMusic()
    {
        winningSong.stop();
    }

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
