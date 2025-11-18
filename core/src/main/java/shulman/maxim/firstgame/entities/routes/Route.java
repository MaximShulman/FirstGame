package shulman.maxim.firstgame.entities.routes;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import shulman.maxim.firstgame.Main;
import shulman.maxim.firstgame.entities.ships.Ship;
import shulman.maxim.firstgame.entities.world.PlanetTile;
import shulman.maxim.firstgame.entities.world.Tile;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static shulman.maxim.firstgame.entities.world.Tile.TILE_HEIGHT;
import static shulman.maxim.firstgame.entities.world.Tile.TILE_WIDTH;

public class Route {
    private PlanetTile origin;
    private ArrayList<Tile> list;
    private TextureRegion region;
    private PlanetTile destination;
    private ArrayList<Vector2> controlPoints;
    private CatmullRomSpline<Vector2> spline;
    private Set<Ship> shipSet;


    public PlanetTile getOrigin() {
        return origin;
    }

    public ArrayList<Tile> getList() {
        return list;
    }

    public Route(PlanetTile origin) {
        this(origin, true);
    }

    public Route(PlanetTile origin, boolean loadPixmap) {
        this.origin = origin;
        this.list = new ArrayList<>();
        this.controlPoints = new ArrayList<>();
        if (loadPixmap) {
            Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            pixmap.setColor(Color.BLACK);
            pixmap.fill();
            Texture texture = new Texture(pixmap);
            this.region = new TextureRegion(texture);
            pixmap.dispose();
            texture.dispose();
        }
        this.shipSet = new HashSet<>();
    }

    public void createShip(){
        Ship ship = new Ship(this, Main.getAssetManager());
        this.shipSet.add(ship);
    }

    public Route addTile(Tile tile) {
        if (!list.isEmpty() && tile instanceof PlanetTile) {
            destination = (PlanetTile) tile;
            list.add(destination);
        }

        list.add(tile);
        return this;
    }

    public Route removeTile(Tile tile) {
        list.remove(tile);
        return this;
    }

    public void drawRoute(Viewport viewport, SpriteBatch batch, AssetManager assetManager) {
        ShapeDrawer drawer = new ShapeDrawer(batch, region);

        for (int i = 0; i < list.size() - 1; i++) {
            Vector2 controlPoint = new Vector2(list.get(i).getViewportCoordinates(viewport).x() + TILE_WIDTH / 2, list.get(i).getViewportCoordinates(viewport).y() + TILE_HEIGHT / 2);
            if (!controlPoints.contains(controlPoint)) {
                controlPoints.add(controlPoint);
                Vector2 controlPoint2 = new Vector2(list.get(i).getViewportCoordinates(viewport).x() + TILE_WIDTH / 2 + ((list.get(i + 1).getViewportCoordinates(viewport).x() - list.get(i).getViewportCoordinates(viewport).x()) / 4),
                    list.get(i).getViewportCoordinates(viewport).y() + TILE_HEIGHT / 2 + ((list.get(i + 1).getViewportCoordinates(viewport).y() - list.get(i).getViewportCoordinates(viewport).y()) / 4));
                Vector2 controlPoint3 = new Vector2(list.get(i).getViewportCoordinates(viewport).x() + TILE_WIDTH / 2 + ((list.get(i + 1).getViewportCoordinates(viewport).x() - list.get(i).getViewportCoordinates(viewport).x()) / 2),
                    list.get(i).getViewportCoordinates(viewport).y() + TILE_HEIGHT / 2 + ((list.get(i + 1).getViewportCoordinates(viewport).y() - list.get(i).getViewportCoordinates(viewport).y()) / 2));
                Vector2 controlPoint4 = new Vector2(list.get(i).getViewportCoordinates(viewport).x() + TILE_WIDTH / 2 + ((list.get(i + 1).getViewportCoordinates(viewport).x() - list.get(i).getViewportCoordinates(viewport).x()) / 4 * 3),
                    list.get(i).getViewportCoordinates(viewport).y() + TILE_HEIGHT / 2 + ((list.get(i + 1).getViewportCoordinates(viewport).y() - list.get(i).getViewportCoordinates(viewport).y()) / 4 * 3));

                controlPoints.add(controlPoint2);
                controlPoints.add(controlPoint3);
                controlPoints.add(controlPoint4);
            }
        }

        if (controlPoints.size() >= 4) {
            spline = new CatmullRomSpline<>(controlPoints.toArray(Vector2[]::new), false);

            int samples = controlPoints.size();
            Vector2[] points = new Vector2[samples];
            for (int i = 0; i < samples; i++) {
                points[i] = new Vector2();
                spline.valueAt(points[i], (float) i / (samples - 1));
            }
            for (int i = 0; i < samples - 1; i++) {
                drawer.line(points[i].x, points[i].y, points[i + 1].x, points[i + 1].y, 0.1f);
            }

        }

        for (Ship ship : shipSet){
            ship.drawShip(spline, batch, viewport);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(origin, route.origin) && Objects.equals(destination, route.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination);
    }

    @Override

    public String toString() {
        return list.toString();
    }
}
