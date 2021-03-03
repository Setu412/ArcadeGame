package ca.sfu.cmpt276.spring2021.group8.project;

import javax.swing.JFrame;
import java.awt.*;


public class Display {

    private JFrame Frame;
    private Canvas canvas;
    private String title;
    private int height, width;

    public Display(String title, int height, int width) {
        this.title = title;
        this.height = height;
        this.width = width;

        createFrame();
    }

    private void createFrame() {

        Frame = new JFrame(title);
        Frame.setSize(width,height);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setLocationRelativeTo(null);
        Frame.setVisible(true);

        /**
         * this will be having all board, character images and stuff displayed onto the Frame
         */
        canvas = new Canvas();
        canvas.setSize(new Dimension(height, width));
        Frame.add(canvas);
        Frame.pack();
    }

    public Canvas getCanvas(){
        return canvas;
    }
}
