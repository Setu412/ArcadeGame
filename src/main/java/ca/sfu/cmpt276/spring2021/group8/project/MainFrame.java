package ca.sfu.cmpt276.spring2021.group8.project;


import ca.sfu.cmpt276.spring2021.group8.project.GUI.GUIConfigurations;
import ca.sfu.cmpt276.spring2021.group8.project.GUI.HowToPlayMenu;
import ca.sfu.cmpt276.spring2021.group8.project.GUI.MainMenu;
import ca.sfu.cmpt276.spring2021.group8.project.game.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainFrame {
    private static final String SCREEN_GAME = "game";
    private static final String SCREEN_MAINMENU = "mainmenu";
    private static final String SCREEN_HOWTOPLAY= "howtoplay";

    private Canvas canvas;
    private CardLayout cardLayout = new CardLayout();
    private JPanel panel = new JPanel();

    private MainMenu mainMenu=new MainMenu();
    private HowToPlayMenu howToPlayMenu=new HowToPlayMenu();

    public MainFrame() {
        JFrame f = new JFrame();

        panel.setLayout(cardLayout);

        f.add(panel);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(GUIConfigurations.WIDTH, GUIConfigurations.HEIGHT);
        f.setResizable(false);

        //mainMenu.setSize(width,height);
        mainMenu.getStartGameBtn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        mainMenu.getHowToPlayBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHowToPlayMenu();
            }
        });

        //howToPlayMenu.setSize(width,height);
        howToPlayMenu.getMainMenuBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMainMenu();
            }
        });
        panel.add(mainMenu, SCREEN_MAINMENU);
        panel.add(createGameCanvas(GUIConfigurations.WIDTH,GUIConfigurations.HEIGHT), SCREEN_GAME);
        panel.add(howToPlayMenu,SCREEN_HOWTOPLAY);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        cardLayout.show(panel,SCREEN_MAINMENU);

        try {
            SoundEffects.BRplayMusic("src/resources/Audio/Background.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMainMenu() {
        cardLayout.show(panel, SCREEN_MAINMENU);
    }

    private void showHowToPlayMenu()
    {
        cardLayout.show(panel,SCREEN_HOWTOPLAY);
    }

    private Canvas createGameCanvas(int width, int height) {
        canvas = new Canvas();

        canvas.setSize(width, height);
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

        return canvas;
    }

    void startGame() {
        cardLayout.show(panel, SCREEN_GAME);
        canvas.requestFocusInWindow();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Game game = new Game();
                try {
                    canvas.addKeyListener(game);

                    GameResult result = game.loop(canvas);
                    switch (result) {
                    default:
                        System.out.println("game result: " + result.toString());
                        showMainMenu();
                        break;

                    // TODO handle other game results
                    }
                } finally {
                    canvas.removeKeyListener(game);
                }
            }
        }).start();
    }
}
