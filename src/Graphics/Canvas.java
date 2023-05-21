package Graphics;

import Game.*;
import Graphics.src.IMG;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Canvas extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int offsetX = UIHandler.renderOffsetX;
        int offsetY = UIHandler.renderOffsetY;

        Vector2D mV = mousePositionToTilePosition(GameHandler.mousePosition);

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        g2d.scale(RenderEngine.zoom, RenderEngine.zoom);

        for (int x = 0; x < GameHandler.mapWidth; x++) {
            for (int y = 0; y < GameHandler.mapHeight; y++) {
                if (GameHandler.map[x][y] != null) {
                    g2d.drawImage(GameHandler.map[x][y].img, x * RenderEngine.tileSize - offsetX, y * RenderEngine.tileSize - offsetY, null);
                }
            }
        }

        for(Entity e : GameHandler.entityList) {

            if(e.isSelected) {
                g2d.setColor(Color.CYAN);
                g2d.fillRect(e.getX() - offsetX, e.getY() - offsetY, e.img.getWidth(null), e.img.getHeight(null));
            }

            g2d.drawImage(e.img, e.getX() - offsetX, e.getY() - offsetY, null);
        }

        if (mV != null) g2d.drawImage(IMG.selectedTile, (int) mV.x, (int) mV.y, null);

        paintUI(g2d);
    }

    private void paintUI(Graphics2D g2d) {
        float newZoom = (float) (1.0 / RenderEngine.zoom);
        g2d.scale(newZoom, newZoom);

        if (!UIHandler.isMenuOpen) {
            g2d.drawImage(IMG.openUiButton, 0, this.getHeight() - RenderEngine.tileSize, null);
            UIHandler.uiButtonRect = new Rectangle(0, this.getHeight() - RenderEngine.tileSize, RenderEngine.tileSize, RenderEngine.tileSize);
        } else {
            generateUI(g2d);
        }

        g2d.drawImage(UIHandler.currentMouseImg, (int) ((int) GameHandler.mousePosition.x * RenderEngine.zoom - 12), (int) ((int) GameHandler.mousePosition.y * RenderEngine.zoom - 5), null);

        drawFPS(g2d);
        drawCoordinates(g2d);
    }

    private void drawFPS(Graphics2D g2d) {
        g2d.drawImage(IMG.fpsBackground, 0, 0, null);

        String fps = "FPS: " + RenderEngine.currentFps;
        char[] fpsChar = fps.toCharArray();

        int offsetX = 6;
        int offsetY = 6;

        for (int i = 0; i < fpsChar.length; i++) {
            switch (fpsChar[i]) {
                case 'F' -> {
                    g2d.drawImage(IMG.F, offsetX, offsetY, null);
                    offsetX += IMG.F.getWidth(null) + 1;
                }
                case 'P' -> {
                    g2d.drawImage(IMG.P, offsetX, offsetY, null);
                    offsetX += IMG.P.getWidth(null) + 1;
                }
                case 'S' -> {
                    g2d.drawImage(IMG.S, offsetX, offsetY, null);
                    offsetX += IMG.S.getWidth(null) + 1;
                }
                case ':' -> {
                    g2d.drawImage(IMG.Colons, offsetX, offsetY, null);
                    offsetX += IMG.Colons.getWidth(null) + 1;
                }
                case '1' -> {
                    g2d.drawImage(IMG.One, offsetX, offsetY, null);
                    offsetX += IMG.One.getWidth(null) + 1;
                }
                case '2' -> {
                    g2d.drawImage(IMG.Two, offsetX, offsetY, null);
                    offsetX += IMG.Two.getWidth(null) + 1;
                }
                case '3' -> {
                    g2d.drawImage(IMG.Three, offsetX, offsetY, null);
                    offsetX += IMG.Three.getWidth(null) + 1;
                }
                case '4' -> {
                    g2d.drawImage(IMG.Four, offsetX, offsetY, null);
                    offsetX += IMG.Four.getWidth(null) + 1;
                }
                case '5' -> {
                    g2d.drawImage(IMG.Five, offsetX, offsetY, null);
                    offsetX += IMG.Five.getWidth(null) + 1;
                }
                case '6' -> {
                    g2d.drawImage(IMG.Six, offsetX, offsetY, null);
                    offsetX += IMG.Six.getWidth(null) + 1;
                }
                case '7' -> {
                    g2d.drawImage(IMG.Seven, offsetX, offsetY, null);
                    offsetX += IMG.Seven.getWidth(null) + 1;
                }
                case '8' -> {
                    g2d.drawImage(IMG.Eight, offsetX, offsetY, null);
                    offsetX += IMG.Eight.getWidth(null) + 1;
                }
                case '9' -> {
                    g2d.drawImage(IMG.Nine, offsetX, offsetY, null);
                    offsetX += IMG.Nine.getWidth(null) + 1;
                }
            }
        }
    }

    private void drawCoordinates(Graphics g2d) {

        g2d.drawImage(IMG.fpsBackground, this.getWidth() - IMG.fpsBackground.getWidth(null),0, null);
        g2d.drawImage(IMG.fpsBackground, this.getWidth() - IMG.fpsBackground.getWidth(null),IMG.fpsBackground.getHeight(null), null);

        Vector2D pos = mousePositionToTilePosition(GameHandler.mousePosition);

        if(pos == null) return;

        int x = (int) ((pos.x + UIHandler.renderOffsetX) / RenderEngine.tileSize);

        int offsetX = this.getWidth() - IMG.fpsBackground.getWidth(null) + 6;

        g2d.drawImage(IMG.X, offsetX, 6, null);
        offsetX += IMG.X.getWidth(null) + 1;
        g2d.drawImage(IMG.Colons, offsetX, 6, null);
        offsetX += IMG.Colons.getWidth(null) + 1;

        for(char c : Integer.toString(x).toCharArray()) {
            Image img = numberToImage(String.valueOf(c));

            if(img == IMG.Minus) {
                g2d.drawImage(img, offsetX, 6 + 8, null);
            } else {
                g2d.drawImage(img, offsetX, 6, null);
            }

            offsetX += img.getWidth(null) + 1;
        }

        int y = (int) ((pos.y + UIHandler.renderOffsetY) / RenderEngine.tileSize);

        offsetX = this.getWidth() - IMG.fpsBackground.getWidth(null) + 6;

        g2d.drawImage(IMG.Y, offsetX, 6 + IMG.fpsBackground.getHeight(null), null);
        offsetX += IMG.Y.getWidth(null) + 1;
        g2d.drawImage(IMG.Colons, offsetX, 6 + IMG.fpsBackground.getHeight(null), null);
        offsetX += IMG.Colons.getWidth(null) + 1;

        for(char c : Integer.toString(y).toCharArray()) {
            Image img = numberToImage(String.valueOf(c));

            if(img == IMG.Minus) {
                g2d.drawImage(img, offsetX, 6 + 8 + IMG.fpsBackground.getHeight(null), null);
            } else {
                g2d.drawImage(img, offsetX, 6 + IMG.fpsBackground.getHeight(null), null);
            }

            offsetX += img.getWidth(null) + 1;
        }

    }

    private Image numberToImage(String  _num) {
        switch (_num.toCharArray()[0]) {
            case '-' -> {
                return IMG.Minus;
            }
            case '0' -> {
                return IMG.Zero;
            }
            case '1' -> {
                return IMG.One;
            }
            case '2' -> {
                return IMG.Two;
            }
            case '3' -> {
                return IMG.Three;
            }
            case '4' -> {
                return IMG.Four;
            }
            case '5' -> {
                return IMG.Five;
            }
            case '6' -> {
                return IMG.Six;
            }
            case '7' -> {
                return IMG.Seven;
            }
            case '8' -> {
                return IMG.Eight;
            }
            case '9' -> {
                return IMG.Nine;
            }
        }
        return null;
    }

    private void generateUI(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect(0, this.getHeight() - 150, this.getWidth(), 150);

        UIHandler.uiRect = new Rectangle(0, this.getHeight() - 150, this.getWidth(), 150);

        int offsetX = 0;
        int offsetY = 0;

        offsetX = (this.getWidth() - UIHandler.toolList.size() * RenderEngine.tileSize) / 2;

        for(Tool tool : UIHandler.toolList) {

            int x = UIHandler.toolList.indexOf(tool) * tool.r.width + offsetX;
            int y = UIHandler.uiRect.y;

            g2d.drawImage(IMG.itemBackground, x, y, null);
            g2d.drawImage(tool.icon, x, y, null);

            tool.r.x = x;
            tool.r.y = y;
        }

        g2d.drawImage(IMG.closeUi, UIHandler.uiRect.width - RenderEngine.tileSize, UIHandler.uiRect.y, null);
        UIHandler.uiCloseRect = new Rectangle(UIHandler.uiRect.width - RenderEngine.tileSize, UIHandler.uiRect.y, RenderEngine.tileSize, RenderEngine.tileSize);
    }

    public static Vector2D mousePositionToTilePosition(Vector2D mV) {

        for(int x = 0; x < GameHandler.mapWidth; x++) {
            for(int y = 0; y < GameHandler.mapHeight; y++) {
                Rectangle r1 = new Rectangle(x * RenderEngine.tileSize, y * RenderEngine.tileSize, RenderEngine.tileSize, RenderEngine.tileSize);
                Rectangle r2 = new Rectangle((int) mV.x, (int) mV.y, 1, 1);
                if(r2.intersects(r1)) {
                    return new Vector2D(x * RenderEngine.tileSize, y * RenderEngine.tileSize);
                }
            }
        }
        return null;
    }
}
