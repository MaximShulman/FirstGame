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



    public record ViewportCoordinates(float x, float y) {

    }

    private static final int[][] neighborOffsets = {
        {0, -1, 1}, {1, -1, 0}, {0, 1, -1},
        {-1, 1, 0}, {-1, 0, 1}, {1, 0, -1},
    };

    /*
    Draws 2 * size + 1 rows of tiles, and as many "columns" as can fit the screen given the resulting dimensions.
    10% of them should be populated, the rest empty.
     */

    public static HashSet<Tile> populateWorld(int size, Viewport viewport) {
        TILE_WIDTH =
                (viewport.getWorldHeight() - TILE_HEIGHT) / (size * 2 * 0.85f);
            TILE_HEIGHT = TILE_WIDTH * 0.85f;
            LINE_SIZE = TILE_WIDTH * 0.5f;
            BORDER_SIZE_COEFFICIENT = TILE_WIDTH * 0.015f * 0.5f;

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
            && !(getViewportCoordinates(tile, viewport).y <= 0) && !tileSet.contains(tile);
    }

    private static void createNeighbors(Tile tile, HashSet<Tile> tileSet, Viewport viewport) {

        tileSet.add(tile);
        for (int[] offset : neighborOffsets) {
            if(canBeAdded(new EmptyTile(tile.getX() + offset[0], tile.getY() + offset[1], tile.getZ() + offset[2],viewport),viewport,tileSet)){
                Tile neighbor = createNeighbor(tile, offset[0], offset[1], offset[2], viewport);
                createNeighbors(neighbor, tileSet, viewport);
            }

        }
    }
    private static Tile createNeighbor(Tile tile, int modX, int modY, int modZ, Viewport viewport){
        double random = Math.random();
        if(random > 0.05) {
            return new EmptyTile(tile.getX() + modX, tile.getY() + modY, tile.getZ() + modZ,
                viewport);
        } else
            return new PlanetTile(tile.getX() + modX, tile.getY() + modY, tile.getZ() + modZ,
                viewport, new Planet("placeholder"));
    }


    public static <T extends Tile> void drawTileUnselected(T tile, Viewport viewport, SpriteBatch batch) {

        ViewportCoordinates coordinates = getViewportCoordinates(tile, viewport);

        batch.draw(tile.getTileTextureUnselected(), coordinates.x(), coordinates.y(), TILE_WIDTH,
                TILE_HEIGHT);
    }

    public static <T extends Tile> void drawTileSelected(T tile, Viewport viewport, SpriteBatch batch) {

        ViewportCoordinates coordinates = getViewportCoordinates(tile, viewport);

        batch.draw(tile.getTileTextureSelected(), coordinates.x(), coordinates.y(), TILE_WIDTH, TILE_HEIGHT);
    }

    /*
        INSTRUCTIONS FOR COORDINATE CONVERSION

        A hexagonal pattern is created along two axis:
        - q
        - r (radius)

        A tile in the center has the coordinates 0,0
        The tile to the upper right is 101, -1
        To the bottom right is -1,1,0
        To the bottom is -1,0,1
        To the bottom left is 0,-1,1
        To the top left 1,-1,0
        To the top 1,0,-1

        The tiles are drawn starting from the center
     */
    public static <T extends Tile> ViewportCoordinates getViewportCoordinates(T tile, Viewport viewport) {
        if(tile.getX() + tile.getZ() + tile.getY() != 0){
            return new ViewportCoordinates(Float.MAX_VALUE, Float.MAX_VALUE);
        }
        int q = tile.getX();
        int r = tile.getY();
//        float horizontalYCoefficient =
//            tile.getY() * (LINE_SIZE + LINE_SIZE / 2 - BORDER_SIZE_COEFFICIENT);
//        float horizontalZCoefficient =
//            tile.getZ() * (LINE_SIZE + LINE_SIZE / 2 - BORDER_SIZE_COEFFICIENT);
//        float verticalYCoefficient = tile.getY() * (TILE_HEIGHT / 2 - BORDER_SIZE_COEFFICIENT);
//        float verticalZCoefficient = -tile.getZ() * (TILE_HEIGHT / 2 - BORDER_SIZE_COEFFICIENT);
//
//        float x = tile.getX() * (TILE_WIDTH + TILE_WIDTH / 2 - BORDER_SIZE_COEFFICIENT)
//            + viewport.getWorldWidth() / 2 + horizontalYCoefficient
//            + horizontalZCoefficient - TILE_WIDTH / 2;
//        float y = verticalYCoefficient + verticalZCoefficient
//            + viewport.getWorldHeight() / 2 - TILE_HEIGHT / 2;
        float x = q * (TILE_WIDTH / 4 + TILE_WIDTH / 2 - BORDER_SIZE_COEFFICIENT) + viewport.getWorldWidth() / 2f - TILE_WIDTH / 2;
        float y = (TILE_HEIGHT / (float)Math.sqrt(3) - BORDER_SIZE_COEFFICIENT)  * ((float)Math.sqrt(3) * (r + q / 2f)) + viewport.getWorldHeight() / 2f - TILE_HEIGHT / 2;
      return new ViewportCoordinates(x, y);
    }


    public static <T extends Tile> float[] getVertices(T tile, Viewport viewport) {
        ViewportCoordinates coordinates = getViewportCoordinates(tile, viewport);
        float[] verticeArray = new float[]{coordinates.x + BORDER_SIZE_COEFFICIENT * 2, coordinates.y + TILE_HEIGHT / 2,
            coordinates.x + TILE_WIDTH / 4 + BORDER_SIZE_COEFFICIENT, coordinates.y + TILE_HEIGHT - BORDER_SIZE_COEFFICIENT,
            coordinates.x + TILE_WIDTH / 4 + TILE_WIDTH / 2 - BORDER_SIZE_COEFFICIENT, coordinates.y + TILE_HEIGHT - BORDER_SIZE_COEFFICIENT,
            coordinates.x + TILE_WIDTH - BORDER_SIZE_COEFFICIENT * 2, coordinates.y + TILE_HEIGHT / 2,
            coordinates.x + TILE_WIDTH / 4 + TILE_WIDTH / 2 - BORDER_SIZE_COEFFICIENT, coordinates.y + BORDER_SIZE_COEFFICIENT,
            coordinates.x + TILE_WIDTH / 4 + BORDER_SIZE_COEFFICIENT, coordinates.y + BORDER_SIZE_COEFFICIENT,
            coordinates.x + BORDER_SIZE_COEFFICIENT * 2, coordinates.y + TILE_HEIGHT / 2};
        return verticeArray;
    }

    public static void drawAllTiles(HashSet<Tile> list, Viewport viewport, SpriteBatch batch) {
        list.stream().forEach(tile -> drawTileUnselected(tile, viewport, batch));
    }

}
