package ca.sfu.cmpt276.spring2021.group8.project.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * A class to store all GUI configuration constants, such as colors, fonts, and Frame size
 */
public class GUIConfigurations {

    //Font Loader
    static Font timesNewRoman;

    static {
        try {
            timesNewRoman = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/font/times-new-roman.ttf"));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Colors
    public static final Color PRIMARY_COMPONENT_COLOR =Color.decode("0xA6192E"); //Dark Red, alternative: b10707
    public static final Color SECONDARY_COMPONENT_COLOR=Color.decode("0xffffe6");
    public static final Color BACKGROUND_COLOR=Color.gray;
    public static final Color TEXT_COLOR=Color.white;
    public static final Color LETTER_BACKGROUND_COLOR=Color.white;
    public static final Color LETTER_TEXT_COLOR=Color.black;

    //Fonts (we could use custom fonts later on)
    public static final Font TITLE_FONT= timesNewRoman.deriveFont(Font.BOLD,115f); //new Font("Times New Roman", Font.BOLD, 115);
    public static final Font BUTTON_FONT=timesNewRoman.deriveFont(60f); //new Font("Times New Roman",Font.PLAIN,60);
    public static final Font TEXT_FONT=timesNewRoman.deriveFont(25f); //new Font("Times New Roman",Font.PLAIN,25);
    public static final Font RULES_FONT=timesNewRoman.deriveFont(21f); //new Font("Times New Roman",Font.PLAIN,21);
    public static final Font DIPLOMA_TITLE_FONT=timesNewRoman.deriveFont(Font.ITALIC,70f); //new Font("Times New Roman",Font.ITALIC,70);
    public static final Font EXPULSION_TITLE_FONT= timesNewRoman.deriveFont(Font.BOLD,70f);//new Font("Times New Roman", Font.BOLD,70);
    public static final Font LETTER_TEXT_FONT =timesNewRoman.deriveFont(33f); //new Font("Times New Roman", Font.PLAIN,33);
    public static final Font RESULT_FONT=timesNewRoman.deriveFont(40f); //new Font("Times New Roman",Font.PLAIN,40);

    //Frame size
    public static final int WIDTH=1280;
    public static final int HEIGHT=820;

    /**
     * A shortcut for printing tabs
     */
    //Tabs
    public static final String tab="&nbsp;&nbsp;&nbsp;&nbsp;";

    public GUIConfigurations() throws IOException, FontFormatException {
    }

    /**
     * Prepares the button in the parameter, setting its font, background color, text color, removing borders, removing focus indications, and adding an on click color change
     * @param btn Jbutton object that the user can click on
     */
    //Setting up buttons
    public static void setBtnUI(JButton btn)
    {
        btn.setFont(GUIConfigurations.BUTTON_FONT);
        btn.setBackground(GUIConfigurations.PRIMARY_COMPONENT_COLOR);
        btn.setForeground(GUIConfigurations.TEXT_COLOR);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        UIManager.put("Button.select", Color.decode("0x851425")); //turns buttons a darker shade of red when clicked
    }
}
