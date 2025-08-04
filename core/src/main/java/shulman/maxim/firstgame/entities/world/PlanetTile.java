package shulman.maxim.firstgame.entities.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.viewport.Viewport;
import shulman.maxim.firstgame.Main;
import shulman.maxim.firstgame.tools.TileUtils;

public class PlanetTile extends Tile {

    private int x, y, z;
    private static Texture planetTileTextureUnselected;
    private static Texture planetTileTextureSelected;
    private Viewport viewport;
    private Polygon boundsHexagon;
    private Planet planet;


    @Override
    public int getX() {
        return x;
    }

    public Planet getPlanet() {
        return planet;
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
        return planetTileTextureUnselected;
    }

    @Override
    public Texture getTileTextureSelected() {
        return planetTileTextureSelected;
    }


    public PlanetTile(int x, int y, int z, Viewport viewport, Planet planet) {
        planetTileTextureUnselected = new Texture("planet_hexagon_unselected.png");
        planetTileTextureSelected = new Texture("planet_hexagon_selected.png");
        this.x = x;
        this.y = y;
        this.z = z;
        this.viewport = viewport;
        this.planet = planet;
        boundsHexagon = new Polygon(TileUtils.getVertices(this, viewport));
    }


    public void dispose() {
        planetTileTextureUnselected.dispose();
    }

}
