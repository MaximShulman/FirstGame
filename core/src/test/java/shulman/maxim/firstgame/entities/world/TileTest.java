package shulman.maxim.firstgame.entities.world;

import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import org.junit.jupiter.api.Test;
import shulman.maxim.firstgame.GdxTestBase;


class TileTest extends GdxTestBase{

    @Test
    public void tileTest1() {
        Tile tile1 = new EmptyTile(0, 0, 0, new FitViewport(1920, 1080));
        Tile tile2 = new EmptyTile(0, 1, 0, new FitViewport(1920, 1080));
        assertNotEquals(tile1, tile2);
    }

    @Test
    public void tileTest2() {
        Tile tile1 = new EmptyTile(0, 0, 0, new FitViewport(1920, 1080));
        Tile tile2 = new EmptyTile(0, 0, 0, new FitViewport(1920, 1080));
        assertEquals(tile1, tile2);
    }

    @Test
    public void tileTest3() {
        Tile tile1 = new EmptyTile(0, 0, 0, new FitViewport(1920, 1080));
        Tile tile2 = new PlanetTile(0, 0, 0, new FitViewport(1920, 1080), new Planet("placholder"));
        assertEquals(tile1, tile2);
    }

}
