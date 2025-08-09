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
    private ArrayList<Tile> routeList;
    private PlanetTile origin;


    public GameLogicHandler(GameStateHandler gameStateHandler, Viewport viewport) {
        this.viewport = viewport;
        this.routeList = new ArrayList<>();
        this.gameStateHandler = gameStateHandler;
    }


    public void updateSelectedTiles(Vector2 touchPos) {
        gameStateHandler.setSelectedTiles(new HashSet<>(gameStateHandler.getTiles().stream().filter(tile -> tile.getBoundsHexagon().contains(touchPos)).toList()));
    }

    public void updateRouteList(Vector2 touchPos){
        if (routeList.isEmpty()) {
            try {
                Tile originTile = TileUtils.tileFromCoordinates(touchPos, gameStateHandler.getTiles());
                if(originTile instanceof PlanetTile){
                    routeList =  new ArrayList<>();
                    Route route = new Route((PlanetTile) originTile);
                    gameStateHandler.getRoutes().put((PlanetTile) originTile, route);
                    gameStateHandler.getRoutes().get(originTile).get(gameStateHandler.getRoutes().get(originTile).size() - 1).addTile(originTile);
                    routeList.add(originTile);
                    this.origin = (PlanetTile) originTile;

                }

            } catch (NoSuchElementException error) {
                System.out.println("Nothing happened");
            }
        } else {
            try {
                Tile nextTile = TileUtils.tileFromCoordinates(touchPos, gameStateHandler.getTiles());
                PlanetTile origin = (PlanetTile) routeList.get(0);
                if(!routeList.contains(nextTile) && !(nextTile instanceof PlanetTile)){
                    gameStateHandler.getRoutes().get(origin).get(gameStateHandler.getRoutes().get(origin).size() - 1).addTile(nextTile);
                    routeList.add(nextTile);
                } else if (!routeList.contains(nextTile)){
                    gameStateHandler.getRoutes().get(origin).get(gameStateHandler.getRoutes().get(origin).size() - 1).addTile(nextTile);
                    routeList.add(nextTile);
                    endRoute();
                }
            } catch (NoSuchElementException error) {
                System.out.println("Nothing happened");
            }
        }

    }
    public void endRoute(){
        if(!routeList.isEmpty()) {
            if (!(routeList.get(routeList.size() - 1) instanceof PlanetTile) || routeList.size() == 1) {
                gameStateHandler.getRoutes().get(origin).remove(gameStateHandler.getRoutes().get(origin).size() - 1);
            }
            routeList.clear();

        }
            System.out.println(gameStateHandler.getRoutes());

    }

}
