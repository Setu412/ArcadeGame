package ca.sfu.cmpt276.spring2021.group8.project.gui;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HowToPlayMenuTest {

    HowToPlayMenu howToPlayMenu=new HowToPlayMenu();

    @Test
    public void testGetters()
    {
        assert (howToPlayMenu.getMainMenuBtn()==howToPlayMenu.mainMenuBtn);
    }

    @Test
    public void testHowToPlayMenuComponents()
    {
        List howToPlayMenuComponents= Arrays.asList(howToPlayMenu.getComponents());

        assert (howToPlayMenuComponents.contains(howToPlayMenu.rulesPanel));
        assert (howToPlayMenuComponents.contains(howToPlayMenu.storyPanel));
        assert (howToPlayMenuComponents.contains(howToPlayMenu.mainMenuPanel));
    }

    @Test
    public void testStoryPanelComponents()
    {
        List storyPanelComponents = Arrays.asList(howToPlayMenu.storyPanel.getComponents());

        assert (storyPanelComponents.contains(howToPlayMenu.storyText));
    }

    @Test
    public void testRulesPanelComponents()
    {
        List rulesPanelComponents = Arrays.asList(howToPlayMenu.rulesPanel.getComponents());

        assert (rulesPanelComponents.contains(howToPlayMenu.rulesText));
    }

    @Test
    public void testMainMenuPanelComponents()
    {
        List mainMenuPanelComponents = Arrays.asList(howToPlayMenu.mainMenuPanel.getComponents());

        assert (mainMenuPanelComponents.contains(howToPlayMenu.mainMenuBtn));
    }

}