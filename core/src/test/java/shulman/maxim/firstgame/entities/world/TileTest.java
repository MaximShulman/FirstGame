package shulman.maxim.firstgame.entities.world;

import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.utils.viewport.FitViewport;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;


class TileTest{

    @Test
    public void tileTest1() {
        Tile tile1 = new EmptyTile(0, 0, 0, new FitViewport(1920, 1080), null, null);
        Tile tile2 = new EmptyTile(0, 1, 0, new FitViewport(1920, 1080), null, null);
        assertNotEquals(tile1, tile2);
    }

    @Test
    public void tileTest2() {
        Tile tile1 = new EmptyTile(0, 0, 0, new FitViewport(1920, 1080), null, null);
        Tile tile2 = new EmptyTile(0, 0, 0, new FitViewport(1920, 1080), null, null);
        assertEquals(tile1, tile2);
    }

    @Test
    public void tileTest3() {
        Tile tile1 = new EmptyTile(0, 0, 0, new FitViewport(1920, 1080), null, null);
        Tile tile2 = new PlanetTile(0, 0, 0, new FitViewport(1920, 1080), new Planet("placholder", null), null, null);
        assertEquals(tile1, tile2);
    }

    @Test
    public void tileTest4() {
        Tile tile1 = new EmptyTile(0, 0, 0, new FitViewport(1920, 1080), null, null);
        Tile tile2 = new EmptyTile(0, 0, 0, new FitViewport(1920, 1080), null, null);
        HashSet<Tile> tileSet = new HashSet<>();
        tileSet.add(tile1);
        assertEquals(1, tileSet.size());
        tileSet.add(tile2);
        assertEquals(1, tileSet.size());
    }


}
