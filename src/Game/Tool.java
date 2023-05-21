package Game;

import java.awt.*;
import java.util.ArrayList;

import Graphics.RenderEngine;

public class Tool {
    public Image icon;

    public String type = "";

    public Rectangle r = new Rectangle(-1, -1, RenderEngine.tileSize, RenderEngine.tileSize);

    public Tool(Image _img, String _id) {
        this.icon = _img;
        this.type = _id;
    }
}
