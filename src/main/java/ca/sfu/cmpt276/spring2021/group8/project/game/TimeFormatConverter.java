package ca.sfu.cmpt276.spring2021.group8.project.game;

public interface TimeFormatConverter {
    static String convertTime(long ms)
    {
        long seconds = ms;
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
