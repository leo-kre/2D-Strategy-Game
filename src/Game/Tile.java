package Game;

import java.awt.*;
import Graphics.RenderEngine;
import Graphics.src.IMG;

public class Tile {
    public Image img;
    public boolean shouldCollide;
    public String type;
    public int x;
    public int y;
    public Tile parent;   // added property
    public int gScore;    // added property
    public int fScore;    // added property

    public Tile(Image _img, String _type, boolean _shouldCollide) {
        img = _img;
        if(_img == IMG.mud) img = RenderEngine.rotateImage(_img, Math.floor(Math.random() * 3) * 90);
        if(_img == IMG.grass1 || _img == IMG.grass2 || _img == IMG.grass3) img = RenderEngine.rotateImage(_img, Math.floor(Math.random() * 3) * 90);
        type = _type;
        shouldCollide = _shouldCollide;
    }
}

