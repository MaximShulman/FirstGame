package shulman.maxim.firstgame.entities.routes;

import shulman.maxim.firstgame.entities.world.PlanetTile;
import shulman.maxim.firstgame.entities.world.Tile;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Route {
    private PlanetTile origin;
    private ArrayList<Tile> list;

    public Route(PlanetTile origin) {
        this.origin = origin;
        this.list = new ArrayList<>();
    }

}
