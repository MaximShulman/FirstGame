package shulman.maxim.firstgame.entities.routes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import shulman.maxim.firstgame.entities.world.PlanetTile;
import shulman.maxim.firstgame.entities.world.Tile;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Route {
    private PlanetTile origin;
    private ArrayList<Tile> list;

    public PlanetTile getOrigin() {
        return origin;
    }

    public ArrayList<Tile> getList() {
        return list;
    }

    public Route(PlanetTile origin) {
        this.origin = origin;
        this.list = new ArrayList<>();
    }

    public void addTile(Tile tile){
        list.add(tile);
    }

    public void drawTile(Viewport viewport, SpriteBatch batch, Vector2 start, Vector2 end){

    }
}
