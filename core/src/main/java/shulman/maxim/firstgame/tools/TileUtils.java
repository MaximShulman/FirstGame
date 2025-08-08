package shulman.maxim.firstgame.tools;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.*;

import shulman.maxim.firstgame.entities.world.EmptyTile;
import shulman.maxim.firstgame.entities.world.Planet;
import shulman.maxim.firstgame.entities.world.PlanetTile;
import shulman.maxim.firstgame.entities.world.Tile;

import static shulman.maxim.firstgame.entities.world.Tile.TILE_HEIGHT;
import static shulman.maxim.firstgame.entities.world.Tile.TILE_WIDTH;

public class TileUtils {


    private static final int[][] neighborOffsets = {{0, -1, 1}, {1, -1, 0}, {0, 1, -1}, {-1, 1, 0}, {-1, 0, 1}, {1, 0, -1},};

    /*
    Draws 2 * size + 1 rows of tiles, and as many "columns" as can fit the screen given the resulting dimensions.
    10% of them should be populated, the rest empty.
     */

    public static HashSet<Tile> populateWorld(int size, Viewport viewport, AssetManager assetManager) {
        TILE_WIDTH = (viewport.getWorldHeight() - TILE_HEIGHT) / (size * 2 * 0.85f);
        TILE_HEIGHT = TILE_WIDTH * 0.85f;
        Tile.LINE_SIZE = TILE_WIDTH * 0.5f;
        Tile.BORDER_SIZE_COEFFICIENT = TILE_WIDTH * 0.015f * 0.5f;

        HashSet<Tile> result = new HashSet<>();

        createNeighbors(new EmptyTile(0, 0, 0, viewport, assetManager), result, viewport, assetManager);
        return result;
    }

    private static boolean canBeAdded(Tile tile, Viewport viewport, Set<Tile> tileSet) {
        return !(tile.getViewportCoordinates(viewport).x() + TILE_WIDTH >= viewport.getWorldWidth()) && !(tile.getViewportCoordinates(viewport).x() <= 0) && !(tile.getViewportCoordinates(viewport).y() + TILE_HEIGHT >= viewport.getWorldHeight()) && !(tile.getViewportCoordinates(viewport).y() <= 0) && !tileSet.contains(tile);
    }

    private static void createNeighbors(Tile tile, HashSet<Tile> tileSet, Viewport viewport, AssetManager assetManager) {

        tileSet.add(tile);
        for (int[] offset : neighborOffsets) {
            if (canBeAdded(new EmptyTile(tile.getX() + offset[0], tile.getY() + offset[1], tile.getZ() + offset[2], viewport, assetManager), viewport, tileSet)) {
                Tile neighbor = createNeighbor(tile, offset[0], offset[1], offset[2], viewport, assetManager);
                createNeighbors(neighbor, tileSet, viewport, assetManager);
            }

        }
    }

    private static Tile createNeighbor(Tile tile, int modX, int modY, int modZ, Viewport viewport, AssetManager assetManager) {
        double random = Math.random();
        if (random > 0.05) {
            return new EmptyTile(tile.getX() + modX, tile.getY() + modY, tile.getZ() + modZ, viewport, assetManager);
        } else
            return new PlanetTile(tile.getX() + modX, tile.getY() + modY, tile.getZ() + modZ, viewport, new Planet("placeholder"), assetManager);
    }


    public static Tile tileFromCoordinates(Vector2 touchPos, HashSet<Tile> set) {
        return set.stream().filter(tile -> tile.getBoundsHexagon().contains(touchPos)).findFirst().orElseThrow(NoSuchElementException::new);
    }

    public static void drawAllTiles(HashSet<Tile> list, Viewport viewport, SpriteBatch batch, boolean isSelected) {
        list.forEach(tile -> tile.drawTile(viewport, batch, isSelected));
    }

    public static void drawAllRoutes(RouteMap routes, Viewport viewport, SpriteBatch batch) {
        routes.values().stream().flatMap(Collection::stream)
        .forEach(route -> route.drawRoute(viewport, batch));
    }


}
