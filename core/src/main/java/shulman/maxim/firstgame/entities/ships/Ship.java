package shulman.maxim.firstgame.entities.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;
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
    private float speed = 0.15f;
    private float current = 0;
    private Vector2 out = new Vector2();

    public Ship(Route route, Texture texture){
        this.route = route;
        this.texture = texture;
        fuel = 5;
    }

    public Ship(Route route, AssetManager assetManager){
        this(route, (Texture) assetManager.get("arrow.png"));
    }

    public void drawShip(CatmullRomSpline<Vector2> spline, SpriteBatch batch, Viewport viewport){
        current += Gdx.graphics.getDeltaTime() * speed;
        if(current >= 1)
            current -= 1;
        spline.valueAt(out, current);
        batch.draw(texture, out.x, out.y);
    }

}
