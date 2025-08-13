package shulman.maxim.firstgame.logic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import shulman.maxim.firstgame.entities.world.Tile;
import shulman.maxim.firstgame.state.GameStateHandler;

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

    public Tile tileFromCoordinatesDefault(Vector2 touchPos) {
        HashSet<Tile> set = gameStateHandler.getTiles();
        return set.stream().filter(tile -> tile.getBoundsHexagon().contains(touchPos)).findFirst().orElseThrow(NoSuchElementException::new);
    }
    public void updateSelectedTiles(Vector2 touchPos) {
        gameStateHandler.setSelectedTiles(new HashSet<>(gameStateHandler.getTiles().stream().filter(tile -> tile.getBoundsHexagon().contains(touchPos)).limit(1).toList()));
    }



}
