package ca.sfu.cmpt276.spring2021.group8.project;


import ca.sfu.cmpt276.spring2021.group8.project.game.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import GUI.GUIFrame;

import javax.swing.*;

public class Main {
    final static int width = 1280;
    final static int height = 720;

//    public static void main(String[] args)
//    {
//        GUIFrame frame= new GUIFrame();
//        frame.getUI();
//        frame.getPanelContainer().getMainMenuPanel().getStartGameButton().addActionListener(e->
//        {
//            frame.dispose();
//            launchGame();
//        });
//    }

    public static void main(String[] args) {
        Game game = new Game();
        Frame f = new Frame();
        final Canvas canvas = new Canvas();
        canvas.setSize(width, height);
        f.add(canvas);
        f.pack();
        f.setSize(width, height);
        f.setVisible(true);
        //f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        canvas.createBufferStrategy(2);
        canvas.addKeyListener(game);
        canvas.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component component = e.getComponent();
                if (component != null) {
                    Rectangle bounds = component.getBounds();
                    canvas.setSize(bounds.width, bounds.height);
                }
            }
        });

        loop(canvas, game);
    }

    private Main() {
        // prof = ImageLoader.loadImage("/Images/Professor.jpg");
        // student = ImageLoader.loadImage("/Images/Student.jpg");
    }

    private static void loop(Canvas canvas, Game game) {
        BufferStrategy buffer = canvas.getBufferStrategy();
        long lastFrameTime = System.currentTimeMillis();
        while (true) {
            Rectangle rect = canvas.getBounds();
            // subtract the offset to get the actual canvas size
            Point size = new Point(rect.width, rect.height);

            Graphics g = buffer.getDrawGraphics();
            try {
                g.clearRect(0, 0, rect.width, rect.height);
                game.render(g, size);
                buffer.show();
                final long currentTime = System.currentTimeMillis();
                final long deltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;
                game.update(deltaTime);
            } finally {
                if (g != null) {
                    g.dispose();
                }
            }
        }
    }

    // @Override
    // public void paint(Graphics g) {
    //     super.paint(g);

    //     Rectangle rect = this.getBounds();
    //     // subtract the offset to get the actual canvas size
    //     Point size = new Point(rect.width, rect.height);

    //     game.paint(g, size);
    // }
}
