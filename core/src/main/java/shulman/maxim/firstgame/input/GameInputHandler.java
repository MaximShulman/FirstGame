package shulman.maxim.firstgame.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import shulman.maxim.firstgame.entities.world.PlanetTile;
import shulman.maxim.firstgame.entities.world.Tile;
import shulman.maxim.firstgame.logic.GameLogicHandler;
import shulman.maxim.firstgame.tools.TileUtils;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static shulman.maxim.firstgame.entities.world.Tile.TILE_WIDTH;

public class GameInputHandler {

    private GameLogicHandler gameLogicHandler;
    private Viewport viewport;
    private OrthographicCamera camera;

    Vector2 touchPos;


    public GameInputHandler(GameLogicHandler gameLogicHandler, Viewport viewport, OrthographicCamera camera) {
        this.gameLogicHandler = gameLogicHandler;
        this.camera = camera;
        this.viewport = viewport;
        this.touchPos = new Vector2();
    }

    public void input() {
        touchPos.set(Gdx.input.getX(), Gdx.input.getY());
        viewport.unproject(touchPos);
        try {
            gameLogicHandler.updateSelectedTiles(touchPos);
        } catch (NoSuchElementException e) {
            System.out.println("No tiles selected");
        }


    }

    public GestureDetector.GestureListener DoubleClickDetector() {
        /*
         return new ClickListener(){

           @Override
           public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
               {
                   if(getTapCount() == 2){
                       System.out.println("Boom");
                       return true;
                   }
               }
               return false;
           }
       };
         */
        return new GestureDetector.GestureListener() {
            @Override
            public boolean touchDown(float v, float v1, int i, int i1) {
                return false;
            }

            @Override
            public boolean tap(float x, float y, int count, int button) {
//                if (count == 2) {
//                    System.out.println("Double tap!");
//                    Tile clickedTile = gameLogicHandler.tileFromCoordinatesDefault(viewport.unproject(new Vector2(x, y)));
//                    camera.translate(clickedTile.getX() + TILE_WIDTH / 2, clickedTile.getY());
//                    camera.zoom = 0.1f;
//                    return true;
//                }
                return false;
            }

            @Override
            public boolean longPress(float v, float v1) {
                return false;
            }

            @Override
            public boolean fling(float v, float v1, int i) {
                return false;
            }

            @Override
            public boolean pan(float v, float v1, float v2, float v3) {
                return false;
            }

            @Override
            public boolean panStop(float v, float v1, int i, int i1) {
                return false;
            }

            @Override
            public boolean zoom(float v, float v1) {
                return false;
            }

            @Override
            public boolean pinch(Vector2 vector2, Vector2 vector21, Vector2 vector22, Vector2 vector23) {
                return false;
            }

            @Override
            public void pinchStop() {

            }
        };


    }

    public InputAdapter createGameInputAdapter() {
        return new InputAdapter() {
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                if (button == Input.Buttons.RIGHT) {
                    lastDragPosition = null;
                }
                if (button == Input.Buttons.LEFT) {
                    gameLogicHandler.getRouteLogic().endRoute();
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
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    gameLogicHandler.getRouteLogic().updateRouteList(touchPos, true);
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
