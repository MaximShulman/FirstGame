package shulman.maxim.firstgame.logic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import shulman.maxim.firstgame.entities.routes.Route;
import shulman.maxim.firstgame.entities.world.PlanetTile;
import shulman.maxim.firstgame.entities.world.Tile;
import shulman.maxim.firstgame.state.GameStateHandler;
import shulman.maxim.firstgame.tools.TileUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class GameLogicHandler {

    private GameStateHandler gameStateHandler;
    private Viewport viewport;

    private RouteLogic routeLogic;


    public GameLogicHandler(GameStateHandler gameStateHandler, Viewport viewport) {
        this.viewport = viewport;
        this.gameStateHandler = gameStateHandler;
        this.routeLogic = new RouteLogic(gameStateHandler, viewport);
    }

    public RouteLogic getRouteLogic() {
        return routeLogic;
    }

    public void updateSelectedTiles(Vector2 touchPos) {
        gameStateHandler.setSelectedTiles(new HashSet<>(gameStateHandler.getTiles().stream().filter(tile -> tile.getBoundsHexagon().contains(touchPos)).toList()));
    }



}
