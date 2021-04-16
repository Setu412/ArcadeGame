package ca.sfu.cmpt276.spring2021.group8.project;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainFrameTest {

    MainFrame mainFrame=new MainFrame();

    @Test
    public void testMainFrameComponents()
    {
        List mainFrameComponents = Arrays.asList(mainFrame.getFrame().getRootPane().getContentPane().getComponents());

        assert (mainFrameComponents.contains(mainFrame.getPanel()));
    }

    @Test
    public void testPanelComponents()
    {
        List panelComponents = Arrays.asList(mainFrame.getPanel().getComponents());

        assert (panelComponents.contains(mainFrame.getHowToPlayMenu()));
        assert (panelComponents.contains(mainFrame.getMainMenu()));
        assert (panelComponents.contains(mainFrame.getWinningScreen()));
        assert (panelComponents.contains(mainFrame.getLosingScreen()));
    }

    @Test
    public void testDefaultCloseOperation()
    {
        assert (mainFrame.getFrame().getDefaultCloseOperation()== JFrame.EXIT_ON_CLOSE);
    }

    @Test
    public void testFrameVisible()
    {
        assert (mainFrame.getFrame().isVisible());
    }

    @Test
    public void testFrameResizable()
    {
        assert (!mainFrame.getFrame().isResizable());
    }

}