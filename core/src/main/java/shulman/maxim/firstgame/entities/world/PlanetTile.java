package shulman.maxim.firstgame.entities.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import shulman.maxim.firstgame.Main;
import shulman.maxim.firstgame.entities.tools.TileUtils;

public class PlanetTile extends Tile{

    private int x, y, z;
    private static Texture planetTileTextureUnselected;
    private static Texture planetTileTextureSelected;
    private Main game;
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



    public PlanetTile(int x, int y, int z, Main game, Planet planet) {
        planetTileTextureUnselected = new Texture("planet_hexagon_unselected.png");
        planetTileTextureSelected = new Texture("planet_hexagon_selected.png");
        this.x = x;
        this.y = y;
        this.z = z;
        this.game = game;
        this.planet = planet;
        boundsHexagon = new Polygon(TileUtils.getVertices(this, game));
    }


    public void dispose() {
        planetTileTextureUnselected.dispose();
    }

}
