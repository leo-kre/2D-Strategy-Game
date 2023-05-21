package Game;

import Graphics.src.IMG;
import Graphics.*;
import Graphics.Canvas;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UIHandler {

    public static Image currentMouseImg = IMG.mouseNormal;

    public static boolean isMousePressed = false;
    public static boolean isMouseDragged = false;

    public static boolean isUsingTool = false;

    public static boolean isMenuOpen = false;

    public static Vector2D lastMousePos = new Vector2D(0, 0);

    public static int renderOffsetX;
    public static int renderOffsetY;

    public static int lastRenderOffsetX;
    public static int lastRenderOffsetY;

    public static Rectangle uiButtonRect = null;
    public static Rectangle uiCloseRect = null;
    public static Rectangle uiRect = null;

    public static List<Tool> toolList = new ArrayList<>();

    public static void init() {
        Tool shovel = new Tool(IMG.shovel, "shovel");
        toolList.add(shovel);

        Tool water = new Tool(IMG.water, "water");
        toolList.add(water);

        Tool select = new Tool(IMG.dragIcon, "select");
        toolList.add(select);
    }

    public static void update() {

        if(isMouseDragged){

            Vector2D v = new Vector2D(lastMousePos.x - GameHandler.mousePosition.x, lastMousePos.y - GameHandler.mousePosition.y);

            int oX = (int) (Math.floor((lastRenderOffsetX + lastMousePos.x - GameHandler.mousePosition.x) / RenderEngine.tileSize) * RenderEngine.tileSize);
            int oY = (int) (Math.floor((lastRenderOffsetY + lastMousePos.y - GameHandler.mousePosition.y) / RenderEngine.tileSize) * RenderEngine.tileSize);

            renderOffsetX = oX;
            renderOffsetY = oY;
        } else {
            lastRenderOffsetX = renderOffsetX;
            lastRenderOffsetY = renderOffsetY;
        }

        if(isUsingTool) useTool();
    }

    public static void useTool() {
        if (currentMouseImg.equals(IMG.shovel)) {
            Vector2D pos = Canvas.mousePositionToTilePosition(new Vector2D(GameHandler.mousePosition.x, GameHandler.mousePosition.y));

            assert pos != null;
            int x = (int) ((pos.x + renderOffsetX) / RenderEngine.tileSize);
            int y = (int) ((pos.y + renderOffsetY) / RenderEngine.tileSize);
            if(x > GameHandler.mapWidth || y > GameHandler.mapHeight || x < 0 || y < 0) return;
            Tile t = new Tile(IMG.dirt, "dirt",false);
            GameHandler.map[x][y] = t;
        }
    }

    public static void calculateClick(MouseEvent e) {
        Rectangle eRect = new Rectangle(e.getX(), e.getY(),1, 1);

        if(uiButtonRect != null && eRect.intersects(uiButtonRect)) isMenuOpen = true;
        if(uiCloseRect != null  && eRect.intersects(uiCloseRect)) isMenuOpen = false;

        for(Tool tool : toolList) {
            if(tool.r == null) return;
            if(eRect.intersects(tool.r)) {
                switch (tool.type) {
                    case "shovel" -> {
                        currentMouseImg = IMG.shovel;
                        useTool();
                    }
                    case "water" -> {
                        currentMouseImg = IMG.water;
                    }
                }
            }
        }

        for(Entity entity : GameHandler.entityList) {
            entity.isSelected = false;
        }
        for(Entity entity : GameHandler.entityList) {
            Rectangle entityRect = new Rectangle((int) ((entity.getX() - renderOffsetX) * RenderEngine.zoom), (int) ((entity.getY() - renderOffsetY) * RenderEngine.zoom), (int) (entity.img.getHeight(null) * RenderEngine.zoom), (int) (entity.img.getWidth(null) * RenderEngine.zoom));

            if(eRect.intersects(entityRect)) {
                entity.isSelected = true;
            }
        }
    }
}
