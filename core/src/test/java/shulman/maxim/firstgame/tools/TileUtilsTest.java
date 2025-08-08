package shulman.maxim.firstgame.tools;

import static org.junit.jupiter.api.Assertions.*;


import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.junit.jupiter.api.Test;
import shulman.maxim.firstgame.entities.world.EmptyTile;
import shulman.maxim.firstgame.entities.world.Tile;

class TileUtilsTest {
    private Viewport viewport = new FitViewport(1920, 1080);

    @Test
    void populateWorld() {
    }

//    @Test
//    void getViewportCoordinates1() {
//        Tile tile = new EmptyTile(0,0,0,viewport, null, null);
//        TileUtils.ViewportCoordinates coordinates = TileUtils.getViewportCoordinates(tile, viewport);
//        assertEquals(new ViewportCoordinates(1920/2 - TILE_WIDTH/2, 1080/2 - TILE_HEIGHT/2),coordinates);
//    }
//    @Test
//    void getViewportCoordinates2() {
//        Tile tile = new EmptyTile(0,1,0,viewport, null, null);
//        TileUtils.ViewportCoordinates coordinates = TileUtils.getViewportCoordinates(tile, viewport);
//        assertEquals(new ViewportCoordinates(1920/2 + LINE_SIZE/2 - BORDER_SIZE_COEFFICIENT, 1080/2 - BORDER_SIZE_COEFFICIENT),coordinates);
//    }
//
//
//    @Test
//    void getViewportCoordinates3() {
//        Tile tile = new EmptyTile(-22,22,22,viewport, null, null);
//        TileUtils.ViewportCoordinates coordinates = TileUtils.getViewportCoordinates(tile, viewport);
//        assertEquals(new ViewportCoordinates(1920/2 + LINE_SIZE/2 - BORDER_SIZE_COEFFICIENT, 1080/2 - BORDER_SIZE_COEFFICIENT),coordinates);
//    }

    @Test
    void getVertices() {
    }
}
