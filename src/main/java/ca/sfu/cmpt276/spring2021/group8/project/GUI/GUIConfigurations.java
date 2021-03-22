package ca.sfu.cmpt276.spring2021.group8.project.GUI;

import javax.swing.*;
import java.awt.*;

public class GUIConfigurations {

    //Colors
    public static final Color COMPONENT_COLOR=Color.decode("0xA6192E"); //Dark Red, alternative: b10707
    public static final Color BACKGROUND_COLOR=Color.gray;
    public static final Color TEXT_COLOR=Color.white;
    public static final Color LETTER_BACKGROUND_COLOR=Color.white;
    public static final Color LETTER_TEXT_COLOR=Color.black;

    //Fonts
    public static final Font TITLE_FONT= new Font("Times New Roman", Font.BOLD, 103);
    public static final Font BUTTON_FONT=new Font("Times New Roman",Font.PLAIN,60);
    public static final Font TEXT_FONT=new Font("Times New Roman",Font.PLAIN,25);
    public static final Font RULES_FONT=new Font("Times New Roman",Font.PLAIN,18);

    //Sizes
    public static final int WIDTH=1280;
    public static final int HEIGHT=720;

    //Tabs
    public static final String tab="&nbsp;&nbsp;&nbsp;&nbsp;";

    //Setting up buttons
    public static void setBtnUI(JButton btn)
    {
        btn.setFont(GUIConfigurations.BUTTON_FONT);
        btn.setBackground(GUIConfigurations.COMPONENT_COLOR);
        btn.setForeground(GUIConfigurations.TEXT_COLOR);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
    }

}
