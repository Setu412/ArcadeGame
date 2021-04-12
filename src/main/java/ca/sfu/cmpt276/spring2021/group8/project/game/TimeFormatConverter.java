package ca.sfu.cmpt276.spring2021.group8.project.game;

/**
 * This interface having member function to returns time in its standard representation
 */
public class TimeFormatConverter {

    /**
     * Takes in the total time passed during the game in milliseconds and
     * converts into standard format to time representation mm:ss in form of string
     *
     * @param ms Time passed in milliseconds
     * @return Standard representation of time in form of string
     */
    public static String convertTime(long ms)
    {
        long seconds = ms/1000;
        long minutes = (seconds / 60);
        seconds = seconds % 60;
        String strSecond = String.valueOf(seconds);
        String strMinute = String.valueOf(minutes);

        if (strMinute.length() < 2)
            strMinute = "0" + strMinute;
        if (strSecond.length() < 2)
            strSecond = "0" + strSecond;

        String formatTime =  strMinute + ":" + strSecond;
        return formatTime;
    }
}
