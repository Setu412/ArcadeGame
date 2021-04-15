package ca.sfu.cmpt276.spring2021.group8.project.game;

import org.junit.jupiter.api.Test;

class SoundEffectsTest {

    SoundEffects soundEffects = new SoundEffects();

    @Test
    void testPlayMusic() {
        try {
            soundEffects.playMusic("src/main/resources/Audio/RewardCollection.wav");
            Thread.sleep(100);
            assert (soundEffects.clip.isRunning());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}