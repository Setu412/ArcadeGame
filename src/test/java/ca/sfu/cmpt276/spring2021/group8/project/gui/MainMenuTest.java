package ca.sfu.cmpt276.spring2021.group8.project.gui;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuTest {

    MainMenu mainMenu=new MainMenu();

    @Test
    public void testGetters()
    {
        assert (mainMenu.getStartGameBtn()==mainMenu.startGameBtn);
        assert (mainMenu.getHowToPlayBtn()==mainMenu.howToPlayBtn);
    }

    @Test
    public void testMainMenuComponents()
    {
        List mainMenuComponents= Arrays.asList(mainMenu.getComponents());

        assert (mainMenuComponents.contains(mainMenu.titlePanel));
        assert (mainMenuComponents.contains(mainMenu.howToPlayPanel));
        assert (mainMenuComponents.contains(mainMenu.startGamePanel));
    }

    @Test
    public void testTitlePanelComponents()
    {
        List titlePanelComponents= Arrays.asList(mainMenu.titlePanel.getComponents());

        assert (titlePanelComponents.contains(mainMenu.titleText));
    }

    @Test
    public void testHowToPlayPanelComponents()
    {
        List howToPlayPanelComponents = Arrays.asList(mainMenu.howToPlayPanel.getComponents());

        assert (howToPlayPanelComponents.contains(mainMenu.howToPlayBtn));
    }

    @Test
    public void testStartGamePanel()
    {
        List startGameComponents= Arrays.asList(mainMenu.startGamePanel.getComponents());

        assert (startGameComponents.contains(mainMenu.startGameBtn));
    }

}