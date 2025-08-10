package shulman.maxim.firstgame.entities.world;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.viewport.Viewport;
import shulman.maxim.firstgame.tools.Hexagon;

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


    public PlanetTile(int x, int y, int z, Viewport viewport, Planet planet, Texture textureSelected, Texture textureUnselected) {
        planetTileTextureUnselected = textureUnselected;
        planetTileTextureSelected = textureSelected;
        this.x = x;
        this.y = y;
        this.z = z;
        this.viewport = viewport;
        this.planet = planet;
        boundsHexagon = new Polygon(this.getVertices(viewport));
    }

    public PlanetTile(int x, int y, int z,  Viewport viewport, Planet planet, AssetManager assetManager) {
        this(x, y, z, viewport, planet, assetManager.get("planet_hexagon_selected.png"), assetManager.get("planet_hexagon_unselected.png"));
    }


    public void dispose() {
        planetTileTextureUnselected.dispose();
    }

}
