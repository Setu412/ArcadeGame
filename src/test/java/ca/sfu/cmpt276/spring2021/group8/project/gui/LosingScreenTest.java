package ca.sfu.cmpt276.spring2021.group8.project.gui;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class LosingScreenTest {

    LosingScreen losingScreen=new LosingScreen();

    @Test
    public void testNoEmptyLetterText()
    {
        assert (!losingScreen.letterTitleText.getText().isEmpty());
        assert (!losingScreen.letterBodyText.getText().isEmpty());
    }
}