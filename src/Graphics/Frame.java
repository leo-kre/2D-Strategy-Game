package Graphics;

import Game.GameHandler;
import Game.UIHandler;
import Game.Vector2D;
import Graphics.src.IMG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Frame extends JFrame {

    public Frame(String _title, int _with, int _height, boolean isFullscreen, JPanel _panel) {

        this.setSize(_with, _height);

        if(isFullscreen) this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(_title);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.add(_panel);

        this.setUndecorated(true);

        addListeners();

        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        this.getContentPane().setCursor(blankCursor);

        this.setVisible(true);
    }

    private void addListeners() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_R -> GameHandler.generateWorld();
                    case KeyEvent.VK_ESCAPE -> UIHandler.currentMouseImg = IMG.mouseNormal;
                    case KeyEvent.VK_M -> UIHandler.isMenuOpen = !UIHandler.isMenuOpen;
                    case KeyEvent.VK_P -> GameHandler.entityList.get(0).destination = new Vector2D(15, 15);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UIHandler.calculateClick(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                UIHandler.isMousePressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                UIHandler.isMousePressed = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                GameHandler.mousePosition.x = e.getX() / RenderEngine.zoom;
                GameHandler.mousePosition.y = e.getY() / RenderEngine.zoom;

                if(e.getModifiersEx() == 4096) {
                    UIHandler.isMouseDragged = true;
                } else {
                    UIHandler.isMouseDragged = false;
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                GameHandler.mousePosition.x = e.getX() / RenderEngine.zoom;
                GameHandler.mousePosition.y = e.getY() / RenderEngine.zoom;

                UIHandler.lastMousePos.x = e.getX() / RenderEngine.zoom;
                UIHandler.lastMousePos.y = e.getY() / RenderEngine.zoom;

                UIHandler.isMouseDragged = false;
                UIHandler.isUsingTool = false;
            }
        });

        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                RenderEngine.zoom -= (float) e.getWheelRotation() / 4;
                if(RenderEngine.zoom < 0.25) RenderEngine.zoom = 0.25f;
                if(RenderEngine.zoom > 5) RenderEngine.zoom = 5;

                GameHandler.mousePosition.x = e.getX() / RenderEngine.zoom;
                GameHandler.mousePosition.y = e.getY() / RenderEngine.zoom;
            }
        });
    }
}
