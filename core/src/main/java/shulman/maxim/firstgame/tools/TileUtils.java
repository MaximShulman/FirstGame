package shulman.maxim.firstgame.tools;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.text.View;
import shulman.maxim.firstgame.Main;
import shulman.maxim.firstgame.entities.world.EmptyTile;
import shulman.maxim.firstgame.entities.world.Planet;
import shulman.maxim.firstgame.entities.world.PlanetTile;
import shulman.maxim.firstgame.entities.world.Tile;

public class TileUtils {

    // A tile is 200 x 170 px large, with a 3px border
    public static float TILE_WIDTH = 20;
    public static float TILE_HEIGHT = TILE_WIDTH * 0.85f;
    public static float LINE_SIZE = TILE_WIDTH * 0.5f;
    public static float BORDER_SIZE_COEFFICIENT = TILE_WIDTH * 0.015f * 0.5f;

    private static int limit = 0;


    private record ViewportCoordinates(float x, float y) {
        public ViewportCoordinates{

        }
    }

    private static final int[][] neighborOffsets = {
        {0, 1, -1}, {0, 1, 0}, {0, 0, 1},
        {0, -1, 1}, {0, -1, 0}, {0, 0, -1},
        {1, 0, 0}, {-1, 0, 0}
    };

    /*
    Draws 2 * size + 1 rows of tiles, and as many "columns" as can fit the screen given the resulting dimensions.
    10% of them should be populated, the rest empty.
     */

    public static HashSet<Tile> populateWorld(int size, Viewport viewport) {
        if (TILE_HEIGHT * size * 2 + TILE_HEIGHT > viewport.getWorldHeight()) {
            TILE_WIDTH =
                (viewport.getWorldHeight() - TILE_HEIGHT) / (size * 2 * 0.85f);
            TILE_HEIGHT = TILE_WIDTH * 0.85f;
            LINE_SIZE = TILE_WIDTH * 0.5f;
            BORDER_SIZE_COEFFICIENT = TILE_WIDTH * 0.015f * 0.5f;
        }
//        int sizeX = (int) ((game.getGameViewport().getWorldWidth() / 2 - TILE_WIDTH / 2) / (
//            TILE_WIDTH + LINE_SIZE));
//
//        size = (double) size / 2 % 2 == 0 ? size / 2 : size / 2 + 1;
//        sizeX = (double) size / 2 % 2 == 0 ? sizeX / 2 : sizeX / 2 + 1;

        HashSet<Tile> result = new HashSet<>();

        createNeighbors(new EmptyTile(0, 0, 0, viewport), result, viewport);

        return result;
    }

    private static boolean canBeAdded(Tile tile, Viewport viewport, Set<Tile> tileSet){
        return !(getViewportCoordinates(tile, viewport).x + TILE_WIDTH >= viewport
            .getWorldWidth())
            && !(getViewportCoordinates(tile, viewport).x <= 0)
            && !(getViewportCoordinates(tile, viewport).y + TILE_HEIGHT >= viewport
            .getWorldHeight())
            && !(getViewportCoordinates(tile, viewport).y <= 0) && !(tileSet.contains(tile));
    }

    private static void createNeighbors(Tile tile, HashSet<Tile> tileSet, Viewport viewport) {

        tileSet.add(tile);
        for (int[] offset : neighborOffsets) {
            Tile neighbor = createNeighbor(tile, offset[0], offset[1], offset[2], viewport);

            if (canBeAdded(neighbor, viewport, tileSet) && limit <= 100) {
                limit++;
                createNeighbors(neighbor, tileSet, viewport);
            }
        }
    }

    private static Tile createNeighbor(Tile tile, int modX, int modY, int modZ, Viewport viewport){
        return new EmptyTile(tile.getX() + modX, tile.getY() + modY, tile.getZ() + modZ, viewport);
    }

    /*
        INSTRUCTIONS FOR COORDINATE CONVERSION

        A hexagonal pattern is created along three axis:
        - x (horizontal)
        - y (bottom left to top right)
        - z (top left to bottom right)

        A tile in the center has the coordinates 0,0,0
        The tile to the upper right is 0,1,0
        To the bottom right is 0,0,1
        To the top left is 0,0,-1
        To the bottom left is 0,-1,0
        To the left -1,0,0
        To the right 1,0,0
        To the top 0,1,-1
        To the bottom 0,-1,1

        The tiles are drawn starting from the center. The conversion from hex coordinates
        to batch parameters is difficult to explain.
     */
    public static <T extends Tile> void drawTileUnselected(T tile, Viewport viewport, SpriteBatch batch) {

        ViewportCoordinates coordinates = getViewportCoordinates(tile, viewport);

        batch.draw(tile.getTileTextureUnselected(), coordinates.x(), coordinates.y(), TILE_WIDTH,
                TILE_HEIGHT);
    }

    public static <T extends Tile> void drawTileSelected(T tile, Viewport viewport, SpriteBatch batch) {

        ViewportCoordinates coordinates = getViewportCoordinates(tile, viewport);

        batch.draw(tile.getTileTextureSelected(), coordinates.x(), coordinates.y(), TILE_WIDTH, TILE_HEIGHT);
    }

    public static <T extends Tile> ViewportCoordinates getViewportCoordinates(T tile, Viewport viewport) {
        float horizontalYCoefficient =
            tile.getY() * (LINE_SIZE + LINE_SIZE / 2 - BORDER_SIZE_COEFFICIENT);
        float horizontalZCoefficient =
            tile.getZ() * (LINE_SIZE + LINE_SIZE / 2 - BORDER_SIZE_COEFFICIENT);
        float verticalYCoefficient = tile.getY() * (TILE_HEIGHT / 2 - BORDER_SIZE_COEFFICIENT);
        float verticalZCoefficient = -tile.getZ() * (TILE_HEIGHT / 2 - BORDER_SIZE_COEFFICIENT);

        float x = tile.getX() * (TILE_WIDTH + TILE_WIDTH / 2 - BORDER_SIZE_COEFFICIENT)
            + viewport.getWorldWidth() / 2 + horizontalYCoefficient
            + horizontalZCoefficient - TILE_WIDTH / 2;
        float y = verticalYCoefficient + verticalZCoefficient
            + viewport.getWorldHeight() / 2 - TILE_HEIGHT / 2;
        ViewportCoordinates coordinates = new ViewportCoordinates(x, y);
        return coordinates;
    }


    public static <T extends Tile> float[] getVertices(T tile, Viewport viewport) {
        ViewportCoordinates coordinates = getViewportCoordinates(tile, viewport);
        float[] verticeArray = new float[]{coordinates.x, coordinates.y + TILE_HEIGHT / 2,
            coordinates.x + TILE_WIDTH / 4, coordinates.y + TILE_HEIGHT,
            coordinates.x + TILE_WIDTH / 4 + TILE_WIDTH / 2, coordinates.y + TILE_HEIGHT,
            coordinates.x + TILE_WIDTH, coordinates.y + TILE_HEIGHT / 2,
            coordinates.x + TILE_WIDTH / 4 + TILE_WIDTH / 2, coordinates.y,
            coordinates.x + TILE_WIDTH / 4, coordinates.y, coordinates.x,
            coordinates.y + TILE_HEIGHT / 2};
        return verticeArray;
    }

    public static void drawAllTiles(HashSet<Tile> list, Viewport viewport, SpriteBatch batch) {
        list.stream().forEach(tile -> drawTileUnselected(tile, viewport, batch));
    }

}
