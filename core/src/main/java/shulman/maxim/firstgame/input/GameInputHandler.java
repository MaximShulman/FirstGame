package shulman.maxim.firstgame.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import shulman.maxim.firstgame.entities.world.PlanetTile;
import shulman.maxim.firstgame.entities.world.Tile;
import shulman.maxim.firstgame.logic.GameLogicHandler;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GameInputHandler {

    private GameLogicHandler gameLogicHandler;
    private Viewport viewport;
    private OrthographicCamera camera;
    private ArrayList<Tile> routeList;

    Vector2 touchPos;


    public GameInputHandler(GameLogicHandler gameLogicHandler, Viewport viewport, OrthographicCamera camera) {
        this.gameLogicHandler = gameLogicHandler;
        this.camera = camera;
        this.viewport = viewport;
        this.routeList = new ArrayList<>();
        this.touchPos = new Vector2();
    }

    public void input() {
        touchPos.set(Gdx.input.getX(), Gdx.input.getY());
        viewport.unproject(touchPos);
        gameLogicHandler.updateSelectedTiles(touchPos);

    }

    public InputAdapter createGameInputAdapter() {
        return new InputAdapter() {
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                if (button == Input.Buttons.RIGHT) {
                    lastDragPosition = null;
                }
                if (button == Input.Buttons.LEFT){
                    if(!routeList.isEmpty()) {
                        if (!(routeList.get(routeList.size() - 1) instanceof PlanetTile)) {
                            gameLogicHandler.cancelRoute((PlanetTile) routeList.get(0));
                        }
                        routeList.clear();
                    }
                }
                return true;
            }

            private Vector2 lastDragPosition;
            private Vector2 newDragPosition = new Vector2();

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                    float offsetX = 0;
                    float offsetY = 0;
                    if (lastDragPosition == null) {
                        lastDragPosition = new Vector2(screenX, screenY);
                    } else {
                        newDragPosition.set(screenX, screenY);
                        viewport.unproject(newDragPosition);
                        viewport.unproject(lastDragPosition);

                        offsetX = lastDragPosition.x - newDragPosition.x;
                        offsetY = lastDragPosition.y - newDragPosition.y;
                        lastDragPosition.set(screenX, screenY);
                    }
                    Vector2 centerPosition = viewport.unproject(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2));
                    if (offsetX + centerPosition.x + viewport.getWorldWidth() * camera.zoom / 2 <= viewport.getWorldWidth() &&
                        offsetX + centerPosition.x - viewport.getWorldWidth() * camera.zoom / 2 >= 0) {
                        camera.translate(offsetX, 0, 0);
                    }
                    if (offsetY + centerPosition.y + viewport.getWorldHeight() * camera.zoom / 2 <= viewport.getWorldHeight() &&
                        offsetY + centerPosition.y - viewport.getWorldHeight() * camera.zoom / 2 >= 0) {
                        camera.translate(0, offsetY, 0);
                    }
                    camera.update();
                }
                if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    if (routeList.isEmpty()) {
                        try {
                            Tile originTile = gameLogicHandler.updateRouteList(touchPos);
                            if(originTile instanceof PlanetTile){
                                gameLogicHandler.createNewRoute((PlanetTile) originTile);
                                routeList.add(originTile);
                            }

                        } catch (NoSuchElementException error) {
                            System.out.println("Nothing happened");
                        }
                    } else {
                        try {
                            Tile nextTile = gameLogicHandler.updateRouteList(touchPos);
                            if(!routeList.contains(nextTile) && !(nextTile instanceof PlanetTile)){
                                gameLogicHandler.continueRoute(nextTile, (PlanetTile) routeList.get(0));
                                routeList.add(nextTile);
                            } else if (!routeList.contains(nextTile)){
                                gameLogicHandler.continueRoute(nextTile, (PlanetTile) routeList.get(0));
                                routeList.add(nextTile);
                                touchUp(Gdx.input.getX(), Gdx.input.getY(), Input.Buttons.LEFT, Input.Buttons.LEFT);
                            }
                        } catch (NoSuchElementException error) {
                            System.out.println("Nothing happened");
                        }
                    }
                }
                return true;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
                Vector2 mousePosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                Vector2 currentPosition = viewport.unproject(mousePosition);
                camera.zoom += amountY * 0.1f;
                camera.zoom = MathUtils.clamp(camera.zoom, 0.1f, 1f);
                camera.update();

                Vector2 newPosition = viewport.unproject(new Vector2().set(Gdx.input.getX(), Gdx.input.getY()));
                Vector2 centerPosition = viewport.unproject(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2));


                float centerOffsetBiggerX = viewport.getWorldWidth() - Math.max(centerPosition.x + viewport.getWorldWidth() * camera.zoom / 2, viewport.getWorldWidth());
                float centerOffsetSmallerX = Math.min(centerPosition.x - viewport.getWorldWidth() * camera.zoom / 2, 0);

                float centerOffsetBiggerY = viewport.getWorldHeight() - Math.max(centerPosition.y + viewport.getWorldHeight() * camera.zoom / 2, viewport.getWorldHeight());
                float centerOffsetSmallerY = Math.min(centerPosition.y - viewport.getWorldHeight() * camera.zoom / 2, 0);


                if (!(centerOffsetSmallerX >= 0)) {
                    camera.translate(-centerOffsetSmallerX, 0, 0);
                } else if (!(centerOffsetBiggerX >= 0)) {
                    camera.translate(centerOffsetBiggerX, 0, 0);
                } else {
                    camera.translate(currentPosition.x - newPosition.x, 0, 0);
                }
                if (!(centerOffsetSmallerY >= 0)) {
                    camera.translate(0, -centerOffsetSmallerY, 0);
                } else if (!(centerOffsetBiggerY >= 0)) {
                    camera.translate(0, centerOffsetBiggerY, 0);
                } else {
                    camera.translate(0, currentPosition.y - newPosition.y, 0);
                }
                camera.update();
                return true;
            }
        };
    }
}
