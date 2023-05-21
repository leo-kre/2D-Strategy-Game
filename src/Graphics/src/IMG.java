package Graphics.src;

import java.awt.*;
import Graphics.RenderEngine;

public class IMG {

    public static final Image mouseNormal = RenderEngine.createTile("src/Graphics/src/tilemap-transparent.png", 36, 10);
    public static final Image mousePoint = RenderEngine.createImage("src/Graphics/src/UI/Mouse/point.png");

    public static final Image selectedTile = RenderEngine.createTile("src/Graphics/src/tilemap-transparent.png", 38, 12);

    public static final Image openUiButton = RenderEngine.createTile("src/Graphics/src/tilemap-transparent.png", 38, 13);

    //UI
    public static final Image closeUi = RenderEngine.createTile("src/Graphics/src/tilemap-transparent.png", 40, 13);
    public static final Image dragIcon = RenderEngine.createTile("src/Graphics/src/tilemap-transparent.png", 46, 16);
    public static final Image fpsBackground = RenderEngine.createCustomTile("src/Graphics/src/UI/fps.png", 0, 0, 112, 34, new Dimension(1, 1));
    public static final Image itemBackground = RenderEngine.createImage("src/Graphics/src/UI/item.png");

    //Game.Tools
    public static final Image shovel = RenderEngine.createTile("src/Graphics/src/tilemap-transparent.png", 42, 5);

    //Background
    public static final Image iconBackground = RenderEngine.createCustomTile("src/Graphics/src/tilemap.png", 625, 225, 14, 14, new Dimension(4, 4));

    //Text
    public static final Image F = RenderEngine.createCustomTile("src/Graphics/src/tilemap-transparent.png", 644, 291, 8, 10, new Dimension(2, 2));
    public static final Image P = RenderEngine.createCustomTile("src/Graphics/src/tilemap-transparent.png", 596, 307, 8, 10, new Dimension(2, 2));
    public static final Image S = RenderEngine.createCustomTile("src/Graphics/src/tilemap-transparent.png", 644, 307, 8, 10, new Dimension(2, 2));
    public static final Image X = RenderEngine.createCustomTile("src/Graphics/src/tilemap-transparent.png", 724, 307, 8, 10, new Dimension(2, 2));
    public static final Image Y = RenderEngine.createCustomTile("src/Graphics/src/tilemap-transparent.png", 740, 307, 8, 10, new Dimension(2, 2));

    public static final Image Colons = RenderEngine.createCustomTile("src/Graphics/src/tilemap-transparent.png", 727, 275, 2, 10, new Dimension(2, 2));
    public static final Image Minus = RenderEngine.createCustomTile("src/Graphics/src/tilemap-transparent.png", 595, 327, 10, 2, new Dimension(2, 2));

    public static final Image Zero = RenderEngine.createCustomTile("src/Graphics/src/tilemap-transparent.png", 564, 275, 8, 10, new Dimension(2, 2));
    public static final Image One = RenderEngine.createCustomTile("src/Graphics/src/tilemap-transparent.png", 581, 275, 4, 10, new Dimension(2, 2));
    public static final Image Two = RenderEngine.createCustomTile("src/Graphics/src/tilemap-transparent.png", 596, 275, 8, 10, new Dimension(2, 2));
    public static final Image Three = RenderEngine.createCustomTile("src/Graphics/src/tilemap-transparent.png", 612, 275, 8, 10, new Dimension(2, 2));
    public static final Image Four = RenderEngine.createCustomTile("src/Graphics/src/tilemap-transparent.png", 628, 275, 8, 10, new Dimension(2, 2));
    public static final Image Five = RenderEngine.createCustomTile("src/Graphics/src/tilemap-transparent.png", 644, 275, 8, 10, new Dimension(2, 2));
    public static final Image Six = RenderEngine.createCustomTile("src/Graphics/src/tilemap-transparent.png", 660, 275, 8, 10, new Dimension(2, 2));
    public static final Image Seven = RenderEngine.createCustomTile("src/Graphics/src/tilemap-transparent.png", 676, 275, 8, 10, new Dimension(2, 2));
    public static final Image Eight = RenderEngine.createCustomTile("src/Graphics/src/tilemap-transparent.png", 692, 275, 8, 10, new Dimension(2, 2));
    public static final Image Nine = RenderEngine.createCustomTile("src/Graphics/src/tilemap-transparent.png", 708, 275, 8, 10, new Dimension(2, 2));

    //Entity
    public static final Image lumberjack = RenderEngine.createCustomTile("src/Graphics/src/tilemap-transparent.png", 482, 17, 12, 14, new Dimension(2, 2));


    //Game.Tools
    public static final Image axe = RenderEngine.createCustomTile("src/Graphics/src/tilemap-transparent.png", 641, 144, 14, 13, new Dimension(2, 2));

    //Vegetation
    public static final Image treeNormal = RenderEngine.createTile("src/Graphics/src/tilemap.png", 0, 1);


    //Ground
    public static final Image dirt = RenderEngine.createTile("src/Graphics/src/tilemap.png", 0, 0);

    public static final Image mud = RenderEngine.createTile("src/Graphics/src/tilemap.png", 2, 0);

    public static final Image grass1 = RenderEngine.createTile("src/Graphics/src/tilemap.png", 5, 0);
    public static final Image grass2 = RenderEngine.createTile("src/Graphics/src/tilemap.png", 6, 0);
    public static final Image grass3 = RenderEngine.createTile("src/Graphics/src/tilemap.png", 7, 0);

    public static final Image water = RenderEngine.createTile("src/Graphics/src/tilemap.png", 8, 5);

    public static final Image stone = RenderEngine.createTile("src/Graphics/src/tilemap.png", 5, 2);

}
