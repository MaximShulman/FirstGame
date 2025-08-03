package shulman.maxim.firstgame.entities.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import shulman.maxim.firstgame.Main;

public abstract class Tile {

    private int x, y, z;
    private Texture tileTextureUnselected;
    private Texture tileTextureSelected;
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

    public Texture getTileTextureUnselected() {
        return tileTextureUnselected;
    }
    public Texture getTileTextureSelected() {
        return tileTextureSelected;
    }

    public Polygon getBoundsHexagon() {
        return boundsHexagon;
    }



}
