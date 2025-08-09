package shulman.maxim.firstgame.entities.routes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;
import shulman.maxim.firstgame.entities.world.PlanetTile;
import shulman.maxim.firstgame.entities.world.Tile;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.ArrayList;

import static shulman.maxim.firstgame.entities.world.Tile.TILE_HEIGHT;
import static shulman.maxim.firstgame.entities.world.Tile.TILE_WIDTH;

public class Route {
    private PlanetTile origin;
    private ArrayList<Tile> list;
    private TextureRegion region;

    public PlanetTile getOrigin() {
        return origin;
    }

    public ArrayList<Tile> getList() {
        return list;
    }

    public Route(PlanetTile origin) {
        this.origin = origin;
        this.list = new ArrayList<>();
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        this.region = new TextureRegion(texture);
        pixmap.dispose();
    }

    public void addTile(Tile tile){
        list.add(tile);
    }

    public void drawRoute(Viewport viewport, SpriteBatch batch){
        for (int i = 0; i < list.size() - 1; i++) {
            ShapeDrawer drawer = new ShapeDrawer(batch, region);
            drawer.line(list.get(i).getViewportCoordinates(viewport).x() + TILE_WIDTH / 2, list.get(i).getViewportCoordinates(viewport).y() + TILE_HEIGHT / 2,
                list.get(i + 1).getViewportCoordinates(viewport).x() + TILE_WIDTH / 2, list.get(i + 1).getViewportCoordinates(viewport).y() + TILE_HEIGHT / 2, 0.2f);
        }

    }

    public String toString(){
        return list.toString();
    }
}
