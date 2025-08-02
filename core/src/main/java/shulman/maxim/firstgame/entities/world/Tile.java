package shulman.maxim.firstgame.entities.world;

import static shulman.maxim.firstgame.entities.tools.TileUtils.LINE_SIZE;
import static shulman.maxim.firstgame.entities.tools.TileUtils.TILE_HEIGHT;
import static shulman.maxim.firstgame.entities.tools.TileUtils.TILE_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import shulman.maxim.firstgame.Main;

public abstract class Tile {

    private int x, y, z;
    private Texture tileTexture;
    private Main game;
    private Polygon boundsHexagon;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Texture getTileTexture() {
        return tileTexture;
    }

    public Polygon getBoundsHexagon() {
        return boundsHexagon;
    }



}
