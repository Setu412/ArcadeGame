package ca.sfu.cmpt276.spring2021.group8.project.game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class GameEffect {
    final public int score;
    final public boolean lose;

    public static GameEffect createScoreEffect(int deltaScore) {
        return new GameEffect(deltaScore, false);
    }

    public static GameEffect createLoseEffect() {
        return new GameEffect(0, true);
    }

    private GameEffect(int score, boolean lose) {
        this.score = score;
        this.lose = lose;
    }

    public static void playMusic(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        Clip clip;
        // create AudioInputStream object
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    public static void BRplayMusic(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        Clip clip;
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        FloatControl control = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(-40.0f);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }
}
