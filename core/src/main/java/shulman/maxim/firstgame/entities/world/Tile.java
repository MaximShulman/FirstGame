package shulman.maxim.firstgame.entities.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.viewport.Viewport;
import shulman.maxim.firstgame.Main;
import shulman.maxim.firstgame.tools.TileUtils;
import shulman.maxim.firstgame.tools.ViewportCoordinates;

public abstract class Tile {
    // A tile is 200 x 170 px large, with a 3px border


    public static float TILE_WIDTH = 20;
    public static float TILE_HEIGHT = TILE_WIDTH * 0.85f;
    public static float LINE_SIZE = TILE_WIDTH * 0.5f;
    public static float BORDER_SIZE_COEFFICIENT = TILE_WIDTH * 0.015f * 0.5f;

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
    public <T extends Tile> void drawTile(Viewport viewport, SpriteBatch batch, boolean isSelected) {

        ViewportCoordinates coordinates = this.getViewportCoordinates(viewport);

        if (isSelected) {
            batch.draw(this.getTileTextureSelected(), coordinates.x(), coordinates.y(), TILE_WIDTH, TILE_HEIGHT);
        } else {
            batch.draw(this.getTileTextureUnselected(), coordinates.x(), coordinates.y(), TILE_WIDTH, TILE_HEIGHT);
        }
    }

    /*
    INSTRUCTIONS FOR COORDINATE CONVERSION

    A hexagonal pattern is created along two axis:
    - q
    - r (radius)

    A tile in the center has the coordinates 0,0
    The tile to the upper right is 0,1,-1
    To the bottom right is -1,1,0
    To the bottom is -1,0,1
    To the bottom left is 0,-1,1
    To the top left 1,-1,0
    To the top 1,0,-1

    The tiles are drawn starting from the center
 */
    public <T extends Tile> ViewportCoordinates getViewportCoordinates(Viewport viewport) {

        if (this.getX() + this.getZ() + this.getY() != 0) {
            return new ViewportCoordinates(Float.MAX_VALUE, Float.MAX_VALUE);
        }
        int q = this.getX();
        int r = this.getY();

        float x = q * (TILE_WIDTH / 4 + TILE_WIDTH / 2 - BORDER_SIZE_COEFFICIENT) + viewport.getWorldWidth() / 2f - TILE_WIDTH / 2;
        float y = (TILE_HEIGHT / (float) Math.sqrt(3) - BORDER_SIZE_COEFFICIENT) * ((float) Math.sqrt(3) * (r + q / 2f)) + viewport.getWorldHeight() / 2f - TILE_HEIGHT / 2;
        return new ViewportCoordinates(x, y);
    }


    public <T extends Tile> float[] getVertices(Viewport viewport) {

        ViewportCoordinates coordinates = this.getViewportCoordinates(viewport);
        float[] verticeArray = new float[]{coordinates.x() + BORDER_SIZE_COEFFICIENT * 2, coordinates.y() + TILE_HEIGHT / 2, coordinates.x() + TILE_WIDTH / 4 + BORDER_SIZE_COEFFICIENT, coordinates.y() + TILE_HEIGHT - BORDER_SIZE_COEFFICIENT, coordinates.x() + TILE_WIDTH / 4 + TILE_WIDTH / 2 - BORDER_SIZE_COEFFICIENT, coordinates.y() + TILE_HEIGHT - BORDER_SIZE_COEFFICIENT, coordinates.x() + TILE_WIDTH - BORDER_SIZE_COEFFICIENT * 2, coordinates.y() + TILE_HEIGHT / 2, coordinates.x() + TILE_WIDTH / 4 + TILE_WIDTH / 2 - BORDER_SIZE_COEFFICIENT, coordinates.y() + BORDER_SIZE_COEFFICIENT, coordinates.x() + TILE_WIDTH / 4 + BORDER_SIZE_COEFFICIENT, coordinates.y() + BORDER_SIZE_COEFFICIENT, coordinates.x() + BORDER_SIZE_COEFFICIENT * 2, coordinates.y() + TILE_HEIGHT / 2};
        return verticeArray;
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

    @Override public String toString(){
        return "[" + getX() + ", " + getY() + ", " + getZ() + "]";
    }
}
