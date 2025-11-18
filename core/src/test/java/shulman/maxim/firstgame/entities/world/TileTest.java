package shulman.maxim.firstgame.entities.world;

import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import shulman.maxim.firstgame.tools.ViewportCoordinates;
import static org.assertj.core.api.Assertions.assertThat;


class TileTest{

    private FitViewport viewport = new FitViewport(1920, 1080);

    @Test
    public void tileTest1() {
        Tile tile1 = new EmptyTile(0, 0, 0, viewport, null, null);
        Tile tile2 = new EmptyTile(0, 1, 0, viewport, null, null);
        assertNotEquals(tile1, tile2);
    }

    @Test
    public void tileTest2() {
        Tile tile1 = new EmptyTile(0, 0, 0, viewport, null, null);
        Tile tile2 = new EmptyTile(0, 0, 0, viewport, null, null);
        assertEquals(tile1, tile2);
    }

    @Test
    public void tileTest3() {
        Tile tile1 = new EmptyTile(0, 0, 0, viewport, null, null);
        Tile tile2 = new PlanetTile(0, 0, 0, viewport, new Planet("placholder", null), null, null);
        assertEquals(tile1, tile2);
    }

    @Test
    public void tileTest4() {
        Tile tile1 = new EmptyTile(0, 0, 0, viewport, null, null);
        Tile tile2 = new EmptyTile(0, 0, 0, viewport, null, null);
        HashSet<Tile> tileSet = new HashSet<>();
        tileSet.add(tile1);
        assertEquals(1, tileSet.size());
        tileSet.add(tile2);
        assertEquals(1, tileSet.size());
    }

    @Test
    public void tileTest5(){
        Tile tile = new EmptyTile(0, 0, 0, viewport, null, null);
        ViewportCoordinates boundsCoordinates = tile.getBoundsCoordinates(viewport);
        Polygon bounds = tile.getBoundsHexagon();
        assertTrue(bounds.contains(boundsCoordinates.x(), boundsCoordinates.y()));
    }

}
