package shulman.maxim.firstgame.state;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.viewport.Viewport;
import shulman.maxim.firstgame.Main;
import shulman.maxim.firstgame.entities.routes.Route;
import shulman.maxim.firstgame.entities.world.PlanetTile;
import shulman.maxim.firstgame.entities.world.Tile;
import shulman.maxim.firstgame.tools.RouteMap;
import shulman.maxim.firstgame.tools.TileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GameStateHandler {
    private HashSet<Tile> tiles;
    private HashSet<Tile> SelectedTiles;
    private RouteMap routes;

    public void setSelectedTiles(HashSet<Tile> selectedTiles) {
        SelectedTiles = selectedTiles;
    }

    public RouteMap getRoutes() {
        return routes;
    }

    public void setRoutes(RouteMap routes) {
        this.routes = routes;
    }

    public HashSet<Tile> getSelectedTiles() {
        return SelectedTiles;
    }

    public void setTiles(HashSet<Tile> tiles) {
        this.tiles = tiles;
    }

    public HashSet<Tile> getTiles() {
        return tiles;
    }

    public GameStateHandler(Viewport viewport, AssetManager assetManager){
        tiles = TileUtils.populateWorld(4, viewport, assetManager);
        routes = new RouteMap();
    }

    public GameStateHandler(Viewport viewport, AssetManager assetManager, HashSet<Tile> tiles){
        this.tiles = tiles;
        routes = new RouteMap();
    }
}
