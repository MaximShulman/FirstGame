package shulman.maxim.firstgame.logic;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shulman.maxim.firstgame.Main;
import shulman.maxim.firstgame.entities.routes.Route;
import shulman.maxim.firstgame.entities.world.EmptyTile;
import shulman.maxim.firstgame.entities.world.Planet;
import shulman.maxim.firstgame.entities.world.PlanetTile;
import shulman.maxim.firstgame.entities.world.Tile;
import shulman.maxim.firstgame.state.GameStateHandler;
import shulman.maxim.firstgame.tools.RouteMap;
import shulman.maxim.firstgame.tools.TileUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class RouteLogicTest {


    private FitViewport viewport = new FitViewport(1920, 1080);


    // lay out 7 tiles for testing, 3 will be planet tiles
    private Tile tile0 = new PlanetTile(0,0,0, viewport, new Planet("",null), null, null);
    private Tile tile1 = new EmptyTile(0,1,-1, viewport, null, null);
    private Tile tile2 = new EmptyTile(-1,1,0, viewport, null, null);
    private Tile tile3 = new PlanetTile(-1,0,1, viewport, new Planet("",null), null, null);
    private Tile tile4 = new EmptyTile(0,-1,1, viewport, null, null);
    private Tile tile5 = new PlanetTile(1,-1,0, viewport, new Planet("",null), null, null);
    private Tile tile6 = new EmptyTile(1,0,-1, viewport, null, null);

    HashSet<Tile> tiles = new HashSet<>(Set.of(tile0,tile1,tile2,tile3,tile4,tile5,tile6));
    private GameStateHandler gameStateHandler = new GameStateHandler(viewport, new AssetManager(), tiles);

    private GameLogicHandler gameLogicHandler = new GameLogicHandler(gameStateHandler, viewport);
    private RouteLogic routeLogic = gameLogicHandler.getRouteLogic();

    @BeforeEach
    public void setHandlers(){
        gameStateHandler = new GameStateHandler(viewport, new AssetManager(), tiles);
        gameLogicHandler = new GameLogicHandler(gameStateHandler, viewport);
        routeLogic = gameLogicHandler.getRouteLogic();
    }


    @Test
    public void routeLogicTest1(){
       assertTrue(gameStateHandler.getRoutes().isEmpty());
    }

    @Test
    public void routeLogicTest2(){
        Vector2 tilePosition = new Vector2(tile0.getBoundsCoordinates(viewport).x(),tile0.getBoundsCoordinates(viewport).y());
        routeLogic.updateRouteList(tilePosition, false);
        assertThat(gameStateHandler.getRoutes().size()).isEqualTo(1);
        routeLogic.endRoute();
        assertTrue(gameStateHandler.getRoutes().isEmpty());


    }

    @Test
    public void routeLogicTest3(){
        Vector2 tilePosition = new Vector2(tile0.getBoundsCoordinates(viewport).x(),tile0.getBoundsCoordinates(viewport).y());
        routeLogic.updateRouteList(tilePosition, false);
        Vector2 nextTilePosition = new Vector2(tile1.getBoundsCoordinates(viewport).x(),tile1.getBoundsCoordinates(viewport).y());
        routeLogic.updateRouteList(nextTilePosition, false);
        assertThat(gameStateHandler.getRoutes().size()).isEqualTo(1);
        routeLogic.endRoute();
        assertTrue(gameStateHandler.getRoutes().isEmpty());


    }

    @Test
    public void routeLogicTest4(){
        Vector2 tilePosition = new Vector2(tile0.getBoundsCoordinates(viewport).x(),tile0.getBoundsCoordinates(viewport).y());
        routeLogic.updateRouteList(tilePosition, false);
        Vector2 nextTilePosition = new Vector2(tile3.getBoundsCoordinates(viewport).x(),tile3.getBoundsCoordinates(viewport).y());
        routeLogic.updateRouteList(nextTilePosition, false);
        assertThat(gameStateHandler.getRoutes().size()).isEqualTo(1);
        assertThat(gameStateHandler.getRoutes().get(tile0).size()).isEqualTo(1);
        routeLogic.endRoute();
        assertThat(gameStateHandler.getRoutes().size()).isEqualTo(1);
        assertThat(gameStateHandler.getRoutes()).containsAllEntriesOf(new HashMap<>(Map.of((PlanetTile)tile0, new ArrayList<>(List.of(new Route((PlanetTile) tile0, false).addTile(tile0).addTile(tile3))))));


    }




}
