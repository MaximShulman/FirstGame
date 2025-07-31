package shulman.maxim.firstgame.entities.world;

import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import shulman.maxim.firstgame.Main;

public class EmptyTile extends Tile {

    private float x, y;
    private boolean offset;
    private static Texture emptyTileTexture;


    public static Texture getEmptyTileTexture() {
        return emptyTileTexture;
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isOffset() {
        return offset;
    }

    public EmptyTile(float x, float y, boolean offset) {
        emptyTileTexture = new Texture("empty_hexagon_tile.png");
        this.x = x;
        this.y = y;
        this.offset = offset;
    }



    public static ArrayList<EmptyTile> populateWorld(int size) {
        ArrayList<EmptyTile> result = new ArrayList<>();
            for(int y = 0; y < size; y++) {
                int finalY = y;
                result.addAll(Stream.iterate(0, x -> x < size, x -> x + 1)
                    .map(x -> new EmptyTile(x * TILE_SIZE, finalY * TILE_SIZE, finalY%2 == 0))
                    .toList());
            }
            return result;
    }

    public static void drawTile(float x, float y, boolean offset, Main game){
        if(offset){
            game.getSpriteBatch().draw(EmptyTile.getEmptyTileTexture(), x, y, TILE_SIZE, TILE_SIZE);

        }
        game.getSpriteBatch().draw(EmptyTile.getEmptyTileTexture(), x, y, TILE_SIZE, TILE_SIZE);
    }

    public static void drawAllTiles(ArrayList<EmptyTile> list, Main game){
        list.forEach(s -> drawTile(s.getX(), s.getY(), game));
    }

    public void dispose() {
        emptyTileTexture.dispose();
    }
}
