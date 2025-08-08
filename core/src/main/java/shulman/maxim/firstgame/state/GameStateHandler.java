package shulman.maxim.firstgame.state;

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

    public GameStateHandler(Main game){
        tiles = TileUtils.populateWorld(15, game.getGameViewport(), game.getAssetManager());
        routes = new RouteMap();
    }
}
