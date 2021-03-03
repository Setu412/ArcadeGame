package ca.sfu.cmpt276.spring2021.group8.project;

import Display.Display;
import Display.ImageLoader;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {

    private Display display;
    private boolean isRunning = false;
    private Thread thread;
    private BufferStrategy buffer;
    private Graphics graphics;

    private BufferedImage Prof, Student;
    public String title;
    public int height, width;

    public Game(String title, int height, int width) {
        this.title = title;
        this.height = height;
        this.width = width;
    }

    private void initialize(){
        display = new Display(title ,height ,width);
        Prof = ImageLoader.loadImage("/Images/Professor.jpg");
        Student = ImageLoader.loadImage("/Images/Student.jpg");
    }

    private void tick() {
        /**
         * all game logic calling here and returning here
         * change isRunning to false when player wants to stop
         */


    }
    private void render() {
        /**
         * rendering all the changes here
         */

        //using buffer, bufferStrategy ang graphics

        buffer = display.getCanvas().getBufferStrategy();
        if(buffer == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        graphics = buffer.getDrawGraphics();

        graphics.clearRect(0 ,0 ,height, width);

        graphics.drawImage(Student, 0 , 0 , null);
        graphics.drawImage(Prof, 500 , 0 , null);
        //show graphics
        buffer.show();
        graphics.dispose();
    }


    public void run() {

        initialize();   //initialize display

        while(isRunning){
            tick();
            render();
        }
        stop();
    }

    public synchronized void start(){
        if(isRunning)
            return;
        isRunning = true;
        thread = new Thread(this);
        thread.start();

    }

    public synchronized void stop(){
        if(!isRunning)
            return;
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}