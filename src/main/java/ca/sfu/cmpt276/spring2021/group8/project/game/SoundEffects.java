package ca.sfu.cmpt276.spring2021.group8.project.game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Class to give the sound effects to player's action
 */
public class SoundEffects {

    /**
     * Plays a sound clip for different game entities
     *
     * @param path Location of required audio file
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public static void playMusic(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        Clip clip;
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

//    /**
//     * Plays a background sound clip
//     *
//     * @param path Location for required audio file
//     * @throws UnsupportedAudioFileException
//     * @throws IOException
//     * @throws LineUnavailableException
//     */
//    public static void BRplayMusic(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException
//    {
//        Clip clip;
//        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
//        clip = AudioSystem.getClip();
//        clip.open(audioInputStream);
//        FloatControl control = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
//        control.setValue(-30.0f);
//        clip.loop(Clip.LOOP_CONTINUOUSLY);
//        clip.start();
//    }
}
