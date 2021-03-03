package ca.sfu.cmpt276.spring2021.group8.project;

import java.awt.Graphics;

public class Draw {
    public static void dot(Graphics g, int x, int y, int r) {
        g.fillOval(x - r, y - r, r*2, r*2);
    }
}
