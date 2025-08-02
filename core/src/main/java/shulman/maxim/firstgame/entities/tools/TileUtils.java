package shulman.maxim.firstgame.entities.tools;

import java.util.ArrayList;
import java.util.List;
import shulman.maxim.firstgame.Main;
import shulman.maxim.firstgame.entities.world.EmptyTile;
import shulman.maxim.firstgame.entities.world.Tile;

public class TileUtils {

    // A tile is 200 x 170 px large, with a 3px border
    public static float TILE_WIDTH = 20;
    public static float TILE_HEIGHT = TILE_WIDTH * 0.85f;
    public static float LINE_SIZE = TILE_WIDTH * 0.5f;
    public static float BORDER_SIZE = TILE_WIDTH * 0.0015f;
    private record ViewportCoordinates(float x, float y) {

    }

    public static ArrayList<EmptyTile> populateWorld(int size, Main game) {
        ArrayList<EmptyTile> result = new ArrayList<>();
        size /= 2;
        for(int x = -size; x <= size; x++){
            for(int y = -size; y <= size; y++){
                for(int z = -size; z <= size; z++){
                    result.add(new EmptyTile(x, y, z, game));
                }
            }
        }
        return result;
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
    public static <T extends Tile> void drawTile(T tile, Main game) {

        ViewportCoordinates coordinates = getViewportCoordinates(tile, game);

        game.getSpriteBatch().draw(tile.getTileTexture(), coordinates.x(), coordinates.y(), TILE_WIDTH, TILE_HEIGHT);
    }

    public static <T extends Tile> ViewportCoordinates getViewportCoordinates(T tile, Main game) {
        float horizontalYCoefficient = tile.getY() * (LINE_SIZE + LINE_SIZE / 2);
        float horizontalZCoefficient = -tile.getZ() * (LINE_SIZE + LINE_SIZE / 2);
        float verticalYCoefficient = tile.getY() * TILE_HEIGHT / 2;
        float verticalZCoefficient = tile.getZ() * TILE_HEIGHT / 2;

        float x = tile.getX() * (TILE_WIDTH + TILE_WIDTH / 2) + game.getGameViewport().getWorldWidth() / 2
            + horizontalYCoefficient
            + horizontalZCoefficient - TILE_WIDTH / 2;
        float y = verticalYCoefficient + verticalZCoefficient
            + game.getGameViewport().getWorldHeight() / 2 - TILE_HEIGHT / 2;
        ViewportCoordinates coordinates = new ViewportCoordinates(x, y);
        return coordinates;
    }



    public static <T extends Tile> float[] getVertices(T tile, Main game){
        ViewportCoordinates coordinates = getViewportCoordinates(tile, game);
        float[] verticeArray = new float[]{coordinates.x, coordinates.y + TILE_HEIGHT / 2,
            coordinates.x + TILE_WIDTH / 4, coordinates.y + TILE_HEIGHT, coordinates.x + TILE_WIDTH / 4 + TILE_WIDTH / 2,
            coordinates.y + TILE_HEIGHT, coordinates.x + TILE_WIDTH, coordinates.y + TILE_HEIGHT / 2,
            coordinates.x + TILE_WIDTH / 4 + TILE_WIDTH / 2,
            coordinates.y, coordinates.x + TILE_WIDTH / 4,
            coordinates.y, coordinates.x, coordinates.y + TILE_HEIGHT / 2};
        return verticeArray;
    }

    public static void drawAllTiles(List<? extends Tile> list, Main game) {
        for(Tile tile : list){
            drawTile(tile, game);
        }
    }

}
