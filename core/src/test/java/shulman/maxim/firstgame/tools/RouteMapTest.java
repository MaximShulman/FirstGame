package shulman.maxim.firstgame.tools;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.junit.jupiter.api.Test;
import shulman.maxim.firstgame.entities.routes.Route;
import shulman.maxim.firstgame.entities.world.Planet;
import shulman.maxim.firstgame.entities.world.PlanetTile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RouteMapTest {

    private Viewport viewport = new FitViewport(1920, 1080);

    @Test
    public void routeMapTest1() {
        PlanetTile origin = new PlanetTile(0, 0, 0, viewport, new Planet("placeholder", null), null, null);
        Route route = new Route(origin);
        RouteMap routeMap = new RouteMap();
        routeMap.put(origin, route);
        assertThat(routeMap.size()).isEqualTo(1);
        assertThat(routeMap).containsAllEntriesOf(Map.of(origin, new ArrayList<Route>(List.of(route))));
    }

    @Test
    public void routeMapTest2() {
        PlanetTile origin = new PlanetTile(0, 0, 0, viewport, new Planet("placeholder", null), null, null);
        Route route = new Route(origin);
        RouteMap routeMap = new RouteMap();
        routeMap.put(origin, route);
        Route newRoute = new Route(origin);
        routeMap.put(origin, newRoute);
        assertThat(routeMap.size()).isEqualTo(1);
        assertThat(routeMap.get(origin).size()).isEqualTo(2);
        assertThat(routeMap).containsAllEntriesOf(Map.of(origin, new ArrayList<Route>(List.of(route, newRoute))));
    }

    @Test
    public void routeMapTest3() {
        PlanetTile origin = new PlanetTile(0, 0, 0, viewport, new Planet("placeholder", null), null, null);
        PlanetTile originNew = new PlanetTile(1, 0, 0, viewport, new Planet("placeholder", null), null, null);
        Route route = new Route(origin);
        RouteMap routeMap = new RouteMap();
        routeMap.put(origin, route);
        Route newRoute = new Route(origin);
        routeMap.put(originNew, newRoute);
        assertThat(routeMap.size()).isEqualTo(2);
        assertThat(routeMap).containsAllEntriesOf(Map.of(origin, new ArrayList<Route>(List.of(route)), originNew, new ArrayList<Route>(List.of(newRoute))));

    }
}
