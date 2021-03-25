package ca.sfu.cmpt276.spring2021.group8.project;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageLoader {
    // Player Images
    public static BufferedImage playerup = ImageLoader.loadImage("src/resources/Images/playerup.png");
    public static BufferedImage playerdown = ImageLoader.loadImage("src/resources/Images/playerdown.png");
    public static BufferedImage playerleft = ImageLoader.loadImage("src/resources/Images/playerright.png");
    public static BufferedImage playerright = ImageLoader.loadImage("src/resources/Images/playerleft.png");

    public static Image p1 = playerup.getScaledInstance(49, 49, Image.SCALE_DEFAULT);
    public static Image p2 = playerdown.getScaledInstance(49, 49, Image.SCALE_DEFAULT);
    public static Image p3 = playerleft.getScaledInstance(49, 49, Image.SCALE_DEFAULT);
    public static Image p4 = playerright.getScaledInstance(49, 49, Image.SCALE_DEFAULT);

    // Enemy Images
    public static BufferedImage enemyup = ImageLoader.loadImage("src/resources/Images/enemyup.png");
    public static BufferedImage enemydown = ImageLoader.loadImage("src/resources/Images/enemydown.png");
    public static BufferedImage enemyleft = ImageLoader.loadImage("src/resources/Images/enemyleft.png");
    public static BufferedImage enemyright = ImageLoader.loadImage("src/resources/Images/enemyright.png");

    public static Image e1 = enemyup.getScaledInstance(49, 49, Image.SCALE_DEFAULT);
    public static Image e2 = enemydown.getScaledInstance(49, 49, Image.SCALE_DEFAULT);
    public static Image e3 = enemyright.getScaledInstance(49, 49, Image.SCALE_DEFAULT);
    public static Image e4 = enemyleft.getScaledInstance(49, 49, Image.SCALE_DEFAULT);

    // Regular Reward Images
    //public static BufferedImage rewardImg = ImageLoader.loadImage("src/resources/Images/p.png");
    public static BufferedImage rewardImg = ImageLoader.loadImage("src/resources/Images/Pnew.png");
    public static Image rr = rewardImg.getScaledInstance(47, 47, Image.SCALE_DEFAULT);

    // Bonus Images
    //public static BufferedImage bonusImg = ImageLoader.loadImage("src/resources/Images/bonus.png");
    public static BufferedImage bonusImg = ImageLoader.loadImage("src/resources/Images/Anew.png");
    public static Image bon = bonusImg.getScaledInstance(47, 47, Image.SCALE_DEFAULT);

    // Punishment Images
    //public static BufferedImage punishmentImg = ImageLoader.loadImage("src/resources/Images/punishment.jpg");
    public static BufferedImage punishmentImg = ImageLoader.loadImage("src/resources/Images/Fnew.jpg");
    public static Image pun = punishmentImg.getScaledInstance(47, 47, Image.SCALE_DEFAULT);

    // Barrier Images
    public static BufferedImage barrierImg = ImageLoader.loadImage("src/resources/Images/barrier2.jpg");
    public static Image bar = barrierImg.getScaledInstance(49, 49, Image.SCALE_DEFAULT);

    // Maze Images
    public static BufferedImage wallImg = ImageLoader.loadImage("src/resources/Images/wall.jpg");
    public static BufferedImage entranceImg = ImageLoader.loadImage("src/resources/Images/entrance.jpg");
    public static BufferedImage exitClosedImg = ImageLoader.loadImage("src/resources/Images/exitclosed.png");
    public static BufferedImage exitOpenImg = ImageLoader.loadImage("src/resources/Images/exitopen.png");

    public static Image w = wallImg.getScaledInstance(49, 49, Image.SCALE_DEFAULT);
    public static Image entr = entranceImg.getScaledInstance(49, 49, Image.SCALE_DEFAULT);
    public static Image exitC = exitClosedImg.getScaledInstance(49, 49, Image.SCALE_DEFAULT);
    public static Image exitO = exitOpenImg.getScaledInstance(49, 49, Image.SCALE_DEFAULT);

    public static BufferedImage loadImage(String ImagePath) {
        try {
            return ImageIO.read(new FileInputStream(ImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}