package Game;

import java.awt.*;
import java.util.*;
import java.util.List;

import Graphics.Canvas;

import Graphics.RenderEngine;

public class Entity {

    public Image img;

    public boolean isSelected = false;

    public String profession;

    public ArrayList<Tool> tools = new ArrayList<>();

    public static float x;
    public static float y;

    public static int addX;
    public static int addY;

    public static float speed = 0.01f;

    public static boolean up = false;
    public static boolean down = false;
    public static boolean left = false;
    public static boolean right = false;

    public Vector2D destination = null;
    public static boolean walkToDestination = false;

    public static List path = new ArrayList<>();

    public static ArrayList<Item> inventory = new ArrayList<>();

    public Entity(Image _img, String _profession) {
        this.img = _img;
        this.profession = _profession;

        addX = (RenderEngine.tileSize - _img.getWidth(null)) / 2;
        addY = (RenderEngine.tileSize - _img.getHeight(null)) / 2;

        GameHandler.entityList.add(this);
    }

    public int getX() {
        return (int) (x + addX);
    }

    public int getY() {
        return (int) (y + addY);
    }

    public void update() {
        if(walkToDestination) {
            move();
        }

        if(destination != null) {
            path.clear();
            Vector2D s = new Vector2D((float) Math.floor(x / RenderEngine.tileSize), (float) Math.floor(y / RenderEngine.tileSize));
            System.out.println(GameHandler.map[(int) s.x][(int) s.y]);
            System.out.println(GameHandler.map[(int) destination.x][(int) destination.y]);

            System.out.println(path);
            destination = null;
        }
    }

    public static void move() {
        if(up) y -= speed;
        if(down) y += speed;
        if(left) x -= speed;
        if(right) x += speed;
    }


}
