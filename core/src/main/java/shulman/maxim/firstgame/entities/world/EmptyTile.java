package shulman.maxim.firstgame.entities.world;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.viewport.Viewport;
import shulman.maxim.firstgame.Main;
import shulman.maxim.firstgame.tools.TileUtils;

public class EmptyTile extends Tile {

    private int x, y, z;
    private static Texture emptyTileTextureUnselected;
    private static Texture emptyTileTextureSelected;
    private Viewport viewport;
    private Polygon boundsHexagon;

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Polygon getBoundsHexagon() {
        return boundsHexagon;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public Texture getTileTextureUnselected() {
        return emptyTileTextureUnselected;
    }
    @Override
    public Texture getTileTextureSelected() {
        return emptyTileTextureSelected;
    }



    public EmptyTile(int x, int y, int z, Viewport viewport, Texture textureSelected, Texture textureUnselected) {
        emptyTileTextureUnselected = textureUnselected;
        emptyTileTextureSelected = textureSelected;
        this.x = x;
        this.y = y;
        this.z = z;
        this.viewport = viewport;
        boundsHexagon = new Polygon(TileUtils.getVertices(this, viewport));
    }

    public EmptyTile(int x, int y, int z, Viewport viewport, AssetManager assetManager) {
        this(x, y, z, viewport, assetManager.get("empty_hexagon_selected.png"), assetManager.get("empty_hexagon_unselected.png"));
    }


    public void dispose() {
        emptyTileTextureUnselected.dispose();
        emptyTileTextureSelected.dispose();
    }
}
