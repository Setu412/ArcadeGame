package ca.sfu.cmpt276.spring2021.group8.project.gui;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WinningScreenTest {

    WinningScreen winningScreen=new WinningScreen();

    @Test
    public void testNoEmptyLetterText()
    {
        assert (!winningScreen.letterTitleText.getText().isEmpty());
        assert (!winningScreen.letterBodyText.getText().isEmpty());
    }

    @Test
    public void testClipIsOpen()
    {
        assert (winningScreen.winningSong.isOpen());
    }

    @Test
    public void testStartMusic()
    {
        try {
            winningScreen.startMusic();
            Thread.sleep(100);
            assert (winningScreen.winningSong.isRunning());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testStopMusic()
    {
        try{
            winningScreen.startMusic();
            Thread.sleep(100);
            assert (winningScreen.winningSong.isRunning());

            winningScreen.stopMusic();
            assert (!winningScreen.winningSong.isRunning());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}