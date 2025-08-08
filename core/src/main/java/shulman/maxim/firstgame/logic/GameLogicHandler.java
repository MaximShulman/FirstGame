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
    }
    public void updateSelectedTiles(Vector2 touchPos) {
        gameStateHandler.setSelectedTiles(new HashSet<>(gameStateHandler.getTiles().stream().filter(tile -> tile.getBoundsHexagon().contains(touchPos)).toList()));
    }

    public Tile updateRouteList(Vector2 touchpos){
        return TileUtils.tileFromCoordinates(touchpos, gameStateHandler.getTiles());
    }

}
