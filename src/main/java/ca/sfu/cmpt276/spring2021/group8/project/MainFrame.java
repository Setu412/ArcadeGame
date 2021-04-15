package ca.sfu.cmpt276.spring2021.group8.project;


import ca.sfu.cmpt276.spring2021.group8.project.gui.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.*;
import ca.sfu.cmpt276.spring2021.group8.project.game.result.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * The class that sets up the GUI frame of the game
 */
public class MainFrame {
    private static final String SCREEN_GAME = "game";
    private static final String SCREEN_MAINMENU = "mainmenu";
    private static final String SCREEN_HOWTOPLAY= "howtoplay";
    private static final String SCREEN_WIN="win";
    private static final String SCREEN_LOST="lost";

    private Canvas canvas;
    private CardLayout cardLayout = new CardLayout();
    private JPanel panel = new JPanel();
    private JFrame frame=new JFrame();

    private BackgroundMusic backgroundMusic=new BackgroundMusic();

    private MainMenu mainMenu=new MainMenu();
    private HowToPlayMenu howToPlayMenu=new HowToPlayMenu();
    private WinningScreen winningScreen=new WinningScreen();
    private LosingScreen losingScreen=new LosingScreen();

    /**
     * Creates a new game frame
     */
    public MainFrame() {

        panel.setLayout(cardLayout);

        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(GUIConfigurations.WIDTH, GUIConfigurations.HEIGHT);
        frame.setResizable(false);

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

        winningScreen.getMainMenuBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                winningScreen.stopMusic();
                backgroundMusic.startMusic();
                showMainMenu();
            }
        });
        winningScreen.getPlayAgainBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                winningScreen.stopMusic();
                backgroundMusic.startMusic();
                startGame();
            }
        });

        losingScreen.getMainMenuBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMainMenu();
            }
        });
        losingScreen.getPlayAgainBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        panel.add(mainMenu, SCREEN_MAINMENU);
        panel.add(createGameCanvas(GUIConfigurations.WIDTH,GUIConfigurations.HEIGHT), SCREEN_GAME);
        panel.add(howToPlayMenu,SCREEN_HOWTOPLAY);
        panel.add(winningScreen,SCREEN_WIN);
        panel.add(losingScreen,SCREEN_LOST);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        cardLayout.show(panel,SCREEN_MAINMENU);

        backgroundMusic.startMusic();
    }

    /**
     * changes the frame to show the main menu
     */
    private void showMainMenu() {
        cardLayout.show(panel, SCREEN_MAINMENU);
    }

    /**
     * changes the frame to show the how to play menu
     */
    private void showHowToPlayMenu()
    {
        cardLayout.show(panel,SCREEN_HOWTOPLAY);
    }

    /**
     * changes the frame to show the winning screen
     */
    private void showWinningScreen()
    {
        winningScreen.startMusic();
        backgroundMusic.stopMusic();
        cardLayout.show(panel,SCREEN_WIN);
    }

    /**
     * changes the frame to show the losing screen
     */
    private void showLosingScreen()
    {
        cardLayout.show(panel,SCREEN_LOST);
    }

    /**
     * creates a game canvas with the given dimensions
     * @param width an int of the width of the canvas
     * @param height an int of the height of the canvas
     * @return the newly created Canvas object representing the game object
     */
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

    /**
     * Switch to game panel and start game loop
     */
    private void startGame() {
        cardLayout.show(panel, SCREEN_GAME);
        canvas.requestFocusInWindow();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Game game = new Game();
                try {
                    canvas.addKeyListener(game);

                    GameResult result = game.loop(canvas);
                    if (result instanceof GameQuitResult) {
                        showMainMenu();
                    } else if (result instanceof GameOverResult) {
                        GameOverResult info = ((GameOverResult) result);
                        showMainMenu();
                        if(((GameOverResult) result).win)
                        {
                            winningScreen.getScoreText().setText("Score: "+info.score);
                            winningScreen.getTimeText().setText("Time: "+TimeFormatConverter.convertTime(info.time));
                            showWinningScreen();
                        }
                        else if(!((GameOverResult) result).win)
                        {
                            losingScreen.getScoreText().setText("Score: "+((GameOverResult) result).score);
                            losingScreen.getTimeText().setText("Time: "+TimeFormatConverter.convertTime(info.time));
                            showLosingScreen();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    showMainMenu();
                } finally {
                    canvas.removeKeyListener(game);
                }
            }
        }).start();
    }

    /**
     * Returns the main panel of the frame
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Returns the main menu panel
     */
    public MainMenu getMainMenu() {
        return mainMenu;
    }

    /**
     * Returns the how to play menu
     */
    public HowToPlayMenu getHowToPlayMenu() {
        return howToPlayMenu;
    }

    /**
     * Returns the winning screen
     */
    public WinningScreen getWinningScreen() {
        return winningScreen;
    }

    /**
     * Returns the losing screen
     */
    public LosingScreen getLosingScreen() {
        return losingScreen;
    }

    /**
     * Returns the main frame
     */
    public JFrame getFrame() {
        return frame;
    }
}
