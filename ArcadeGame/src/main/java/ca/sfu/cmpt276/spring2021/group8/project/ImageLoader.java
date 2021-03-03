package ca.sfu.cmpt276.spring2021.group8.project;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {
    public static BufferedImage loadImage(String ImagePath) {
        try {
            return ImageIO.read(ImageLoader.class.getResource(ImagePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;


    }

}