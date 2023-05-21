package Graphics;

import Game.GameHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class RenderEngine{

    static final int targetFps = 144;
    static int currentFps;

    static final String title = "test";

    public static final int tileSize = 50;

    public static final int width = GameHandler.mapWidth * tileSize;
    public static final int height = GameHandler.mapHeight * tileSize;

    public static float zoom = 5f;

    static Frame frame = null;
    static Canvas canvas = null;

    public static void init() {
        canvas = new Canvas();
        frame = new Frame(title, width, height, true, canvas);

        startRenderTimer(targetFps);
    }

    public static Image createImage(String _path) {

        Image img;

        ImageIcon icon = new ImageIcon(_path);
        img = icon.getImage();

        img = toBufferedImage(img);

        img = scaleImage(img, new Dimension(tileSize, tileSize));

        return img;
    }

    public static Image createTile(String _path, int _iX, int _iY) {

        Image img;

        ImageIcon icon = new ImageIcon(_path);
        img = icon.getImage();

        img = toBufferedImage(img);


        if(img instanceof BufferedImage) {
            img = ((BufferedImage) img).getSubimage(_iX * 16, _iY * 16, 16, 16);
        }

        img = scaleImage(img, new Dimension(tileSize, tileSize));

        return img;
    }

    public static Image createCustomTile(String _path, int _x, int _y, int _width, int _height, Dimension _scale) {

        Image img;

        ImageIcon icon = new ImageIcon(_path);
        img = icon.getImage();

        img = toBufferedImage(img);


        img = ((BufferedImage) img).getSubimage(_x, _y, _width, _height);

        img = scaleImage(img, new Dimension(_width * _scale.width, _height * _scale.height));

        return img;
    }

    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;
    }

    public static Image scaleImage(Image _img, Dimension _scale) {
        return _img.getScaledInstance(_scale.width, _scale.height, Image.SCALE_FAST);
    }

    public static Image rotateImage(Image image, double angle) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage rotatedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle), width / 2, height / 2);
        g2d.setTransform(transform);

        int numThreads = Runtime.getRuntime().availableProcessors();
        int chunkHeight = height / numThreads;

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            final int rowStart = i * chunkHeight;
            final int rowEnd = i == numThreads - 1 ? height : (i + 1) * chunkHeight;
            final BufferedImage chunk = rotatedImage.getSubimage(0, rowStart, width, rowEnd - rowStart);

            threads[i] = new Thread(new Runnable() {
                public void run() {
                    Graphics2D g2dChunk = chunk.createGraphics();
                    g2dChunk.drawImage(image, 0, -rowStart, null);
                    g2dChunk.dispose();
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        g2d.dispose();
        return rotatedImage;
    }


    private static void startRenderTimer(int _fps) {
        final long[] previousTime = {System.nanoTime()};
        long nanoPerSecond = 1000000000L;

        Timer timer = new Timer(0, e -> {
            long currentTime = System.nanoTime();
            long deltaTime = currentTime - previousTime[0];
            previousTime[0] = currentTime;

            double fpsValue = (double) nanoPerSecond / deltaTime;
            currentFps = (int) fpsValue;

            //Executed code
            canvas.repaint();
        });
        timer.setRepeats(true);
        timer.setDelay(1000 / _fps);
        timer.start();
    }
}
