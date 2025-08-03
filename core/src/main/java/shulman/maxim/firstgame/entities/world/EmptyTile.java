package shulman.maxim.firstgame.entities.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import shulman.maxim.firstgame.Main;
import shulman.maxim.firstgame.entities.tools.TileUtils;

public class EmptyTile extends Tile {

    private int x, y, z;
    private static Texture emptyTileTextureUnselected;
    private static Texture emptyTileTextureSelected;
    private Main game;
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



    public EmptyTile(int x, int y, int z, Main game) {
        emptyTileTextureUnselected = new Texture("empty_hexagon_unselected.png");
        emptyTileTextureSelected = new Texture("empty_hexagon_selected.png");
        this.x = x;
        this.y = y;
        this.z = z;
        this.game = game;
        boundsHexagon = new Polygon(TileUtils.getVertices(this, game));
    }


    public void dispose() {
        emptyTileTextureUnselected.dispose();
        emptyTileTextureSelected.dispose();
    }
}
