package ca.sfu.cmpt276.spring2021.group8.project.game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

/**
 * Class to play background music
 */
public class BackgroundMusic {
    Clip bgm;

    /**
     * Loads the background music
     */
    public BackgroundMusic()
    {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/Audio/Background.wav").getAbsoluteFile());
            bgm = AudioSystem.getClip();
            bgm.open(audioInputStream);
            FloatControl gainControl =
                    (FloatControl) bgm.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the background music from the beginning
     */
    public void startMusic()
    {
        bgm.setFramePosition(0);
        bgm.start();
    }

    /**
     * Stops the background music
     */
    public void stopMusic()
    {
        bgm.stop();
    }
}
