package ca.sfu.cmpt276.spring2021.group8.project.gui;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class GUIConfigurationsTest {

    @Test
    public void testSetBtnUI()
    {
        JButton mockBtn=new JButton();
        GUIConfigurations.setBtnUI(mockBtn);

        assert (mockBtn.getFont().equals(GUIConfigurations.BUTTON_FONT));
        assert (mockBtn.getBackground().equals(GUIConfigurations.PRIMARY_COMPONENT_COLOR));
        assert (mockBtn.getForeground().equals(GUIConfigurations.TEXT_COLOR));
        assert (mockBtn.isBorderPainted()==false);
        assert (mockBtn.isFocusPainted()==false);
    }

}