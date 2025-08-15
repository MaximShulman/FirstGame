package shulman.maxim.firstgame.entities.ships;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.utils.viewport.Viewport;
import shulman.maxim.firstgame.entities.routes.Route;
import shulman.maxim.firstgame.entities.world.Planet;
import shulman.maxim.firstgame.entities.world.Tile;

public class Ship {
    private Route route;
    private int fuel;
    private Tile origin;
    private static Texture texture;
    private Tile destination;

    public Ship(Route route, Texture texture){
        this.route = route;
        this.texture = texture;
        fuel = 5;
    }

    public Ship(Route route, AssetManager assetManager){
        this(route, (Texture) assetManager.get("arrow.png"));
    }

    public void drawShip(CatmullRomSpline spline, SpriteBatch batch, Viewport viewport){

    }

}
