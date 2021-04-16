package ca.sfu.cmpt276.spring2021.group8.project.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BackgroundMusicTest {
    BackgroundMusic backgroundMusic=new BackgroundMusic();

    @Test
    public void testClipIsOpen()
    {
        assert (backgroundMusic.bgm.isOpen());
    }

    @Test
    public void testStartMusic()
    {
        try {
            backgroundMusic.startMusic();
            Thread.sleep(100);
            assert (backgroundMusic.bgm.isRunning());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testStopMusic()
    {
        try {
            backgroundMusic.startMusic();
            Thread.sleep(100);
            assert (backgroundMusic.bgm.isRunning());

            backgroundMusic.stopMusic();
            assert (!backgroundMusic.bgm.isRunning());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}