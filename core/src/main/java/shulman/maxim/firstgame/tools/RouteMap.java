package shulman.maxim.firstgame.tools;

import shulman.maxim.firstgame.entities.routes.Route;
import shulman.maxim.firstgame.entities.world.PlanetTile;

import java.util.*;
import java.util.stream.Collectors;


public class RouteMap extends HashMap<PlanetTile, ArrayList<Route>> {
    @Override
    public ArrayList<Route> put(PlanetTile key, ArrayList<Route> value) {
        ArrayList<Route> route = super.get(key);
        if(route == null){
            super.put(key, value);
            return null;
        }
        ArrayList<Route> previousValue = route;
        route.addAll(value);
        return previousValue;
    }

    public ArrayList<Route> put(PlanetTile key, Route value) {
        return this.put(key, new ArrayList<>(List.of(value)));
    }

    @Override public String toString(){
        return entrySet().stream().map(entry -> entry.toString()).collect(Collectors.joining(", ", "{", "}"));
    }
}
