package ca.sfu.cmpt276.spring2021.group8.project.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeFormatConverterTest {

    @Test
    void convertTime() {

        long Test_MS = 61200;

        //Mathematically 61200 milliseconds equals to 01 minute 018333333 seconds
        String TestMS_to_TimeFormat = "01:01";
        assertEquals(TestMS_to_TimeFormat,TimeFormatConverter.convertTime(Test_MS));


        Test_MS = 120000;

        //Mathematically 120000 milliseconds equals to 02 minute 00 seconds
        TestMS_to_TimeFormat = "02:00";
        assertEquals(TestMS_to_TimeFormat,TimeFormatConverter.convertTime(Test_MS));
    }
}