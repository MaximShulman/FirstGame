package shulman.maxim.firstgame.logic;

import com.badlogic.gdx.math.Vector2;
import shulman.maxim.firstgame.entities.routes.Route;
import shulman.maxim.firstgame.entities.world.PlanetTile;
import shulman.maxim.firstgame.entities.world.Tile;
import shulman.maxim.firstgame.state.GameStateHandler;
import shulman.maxim.firstgame.tools.TileUtils;

import java.util.HashSet;
import java.util.NoSuchElementException;

public class GameLogicHandler {

    private GameStateHandler gameStateHandler;

    public GameLogicHandler(GameStateHandler gameStateHandler) {
        this.gameStateHandler = gameStateHandler;
    }

    public void createNewRoute(PlanetTile tile){
        Route route = new Route(tile);
        gameStateHandler.getRoutes().put(tile, route);
        gameStateHandler.getRoutes().get(tile).get(gameStateHandler.getRoutes().get(tile).size() - 1).addTile(tile);
    }
    public void continueRoute(Tile tile, PlanetTile origin){
        gameStateHandler.getRoutes().get(origin).get(gameStateHandler.getRoutes().get(origin).size() - 1).addTile(tile);
    }

    public void cancelRoute(PlanetTile origin){
        gameStateHandler.getRoutes().get(origin).remove(gameStateHandler.getRoutes().get(origin).size() - 1);
    }
    public void updateSelectedTiles(Vector2 touchPos) {
        gameStateHandler.setSelectedTiles(new HashSet<>(gameStateHandler.getTiles().stream().filter(tile -> tile.getBoundsHexagon().contains(touchPos)).toList()));
    }

    public Tile updateRouteList(Vector2 touchpos){
        return TileUtils.tileFromCoordinates(touchpos, gameStateHandler.getTiles());
    }

}
