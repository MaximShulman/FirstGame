package shulman.maxim.firstgame.entities.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.viewport.Viewport;
import shulman.maxim.firstgame.Main;

public abstract class Tile {

    private int x, y, z;
    private Texture tileTextureUnselected;
    private Texture tileTextureSelected;
    private Viewport viewport;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Tile)) return false;
        Tile tile = (Tile) obj;
        return getX() == tile.getX() && getY() == tile.getY() && getZ() == tile.getZ();
    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        result = 31 * result + getZ();
        return result;
    }
}
