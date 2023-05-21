package Game;

import Graphics.RenderEngine;
import Graphics.src.IMG;

import javax.swing.Timer;
import java.util.*;
import java.util.stream.Collectors;

public class GameHandler {

    public static final int fps = 60;

    public static int mapWidth = 150;
    public static int mapHeight = 150;
    public static Tile[][] map = new Tile[mapWidth][mapHeight];

    public static Vector2D mousePosition = new Vector2D( 1, 1);

    public static ArrayList<Entity> entityList = new ArrayList<>();

    public static void main(String[] args) {
        RenderEngine.init();
        UIHandler.init();

        generateWorld();

        StartGameTimer(fps);

        Entity e = new Entity(IMG.lumberjack, "lumberjack");
        e.tools.add(Tools.axe);
    }

    public static void generateWorld() {

        System.out.println("generate");

        PerlinNoise environementNoise = new PerlinNoise((long) (Math.random() * 9999));
        PerlinNoise vegetationNoise = new PerlinNoise((long) (Math.random() * 9999));

        //0.038
        double scale = 0.094;

        for(int x = 0; x < mapWidth; x++) {
            for(int y = 0; y < mapHeight; y++) {
                double noiseValue = environementNoise.perlinNoise(x * scale, y * scale);
                double value = noiseValue * 100;

                if(value > 40) value = 40;
                if(value < -50) value = -50;


                Tile t = new Tile(IMG.dirt, "dirt", false);

                if(Math.floor(Math.random() * 10) == 1) {
                    t.img = IMG.mud;
                    t.type = "mud";
                }

                if(Math.floor(Math.random() * 10) == 2) {
                    int i = (int) (Math.random() * 3);

                    t.type = "grass";

                    switch (i) {
                        case 1 -> {
                            t.img = IMG.grass1;
                            t.shouldCollide = false;
                        }
                        case 2 -> {
                            t.img = IMG.grass2;
                            t.shouldCollide = false;
                        }
                        case 3 -> {
                            t.img = IMG.grass3;
                            t.shouldCollide = false;
                        }
                    }
                }

                if(value > 10) {
                    double noise = vegetationNoise.perlinNoise(x * 0.014, y * 0.014);
                    double v = noise * 100;

                    if(v > 0) {
                        t.img = IMG.stone;
                        t.type = "stone";
                        t.shouldCollide = true;
                    }
                }

                map[x][y] = t;
            }
        }

        for(int x = 0; x < mapWidth; x++) {
            for(int y = 0; y < mapHeight; y++) {
                double noiseValue = environementNoise.perlinNoise(x * scale / 3, y * scale / 3);
                double value = noiseValue * 100;

                if(value > 30) {
                    Tile t = new Tile(IMG.treeNormal, "tree", true);
                    map[x][y] = t;
                }
            }
        }

        for(int x = 0; x < mapWidth; x++) {
            for(int y = 0; y < mapHeight; y++) {
                double noiseValue = environementNoise.perlinNoise(x * scale / 1.2, y * scale / 1.2);
                double value = noiseValue * 100;

                if(value > 30) {
                    Tile t = new Tile(IMG.treeNormal, "tree", true);
                    map[x][y] = t;
                }
            }
        }

        for(int x = 0; x < mapWidth; x++) {
            for(int y = 0; y < mapHeight; y++) {
                double noiseValue = environementNoise.perlinNoise(x * scale / 2, y * scale / 2);
                double value = noiseValue * 100;

                if(value > 30) {
                    Tile t = new Tile(IMG.water, "water", false);
                    map[x][y] = t;
                }
            }
        }

        cleanTerrain();
    }

    public static void cleanTerrain() {
        for(int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {

                String t = map[x][y].type;

                ArrayList<String> alternativeTerrain = new ArrayList<>();

                if(x - 1 >= 0 && !Objects.equals(map[x - 1][y].type, t)) {
                    alternativeTerrain.add(map[x - 1][y].type);
                }

                if(y - 1 >= 0 && !Objects.equals(map[x][y - 1].type, t)) {
                    alternativeTerrain.add(map[x][y - 1].type);
                }

                if(x + 1 <= mapWidth - 1 && !Objects.equals(map[x + 1][y].type, t)) {
                    alternativeTerrain.add(map[x + 1][y].type);
                }

                if(y + 1 <= mapHeight - 1 && !Objects.equals(map[x][y + 1].type, t)) {
                    alternativeTerrain.add(map[x][y + 1].type);
                }

                if(alternativeTerrain.size() == 0) return;

                Map.Entry<String, Long> mostCommon = alternativeTerrain.stream()
                        .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .orElseThrow();

                switch (mostCommon.getKey()) {
                    case "dirt" -> {
                        map[x][y].img = IMG.dirt;
                        map[x][y].type = mostCommon.getKey();
                        map[x][y].shouldCollide = false;
                    }
                    case "stone" -> {
                        map[x][y].img = IMG.stone;
                        map[x][y].type = mostCommon.getKey();
                        map[x][y].shouldCollide = true;
                    }
                    case "mud" -> {
                        map[x][y].img = IMG.mud;
                        map[x][y].type = mostCommon.getKey();
                        map[x][y].shouldCollide = true;
                    }
                }

            }
        }
    }

    private static void GameLoop() {
        UIHandler.update();

        for(Entity e : entityList) {
            e.update();
        }
    }

    private static void StartGameTimer(int _fps) {
        final long[] previousTime = {System.nanoTime()};
        long nanoPerSecond = 1000000000L;

        Timer timer = new Timer(0, e -> {
            long currentTime = System.nanoTime();
            long deltaTime = currentTime - previousTime[0];
            previousTime[0] = currentTime;

            double fpsValue = (double) nanoPerSecond / deltaTime;

            //Executed code
            GameLoop();
        });
        timer.setRepeats(true);
        timer.setDelay(1000 / _fps);
        timer.start();
    }
}
