package shulman.maxim.firstgame.state;

import shulman.maxim.firstgame.Main;
import shulman.maxim.firstgame.entities.world.Tile;
import shulman.maxim.firstgame.tools.TileUtils;

import java.util.HashSet;

public class GameStateHandler {
    private HashSet<Tile> tiles;

    public HashSet<Tile> getTiles() {
        return tiles;
    }

    public GameStateHandler(Main game){
        tiles = TileUtils.populateWorld(15, game.getGameViewport(), game.getAssetManager());
    }
}
