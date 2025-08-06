package shulman.maxim.firstgame.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;

public class InputHandler {


    public static InputAdapter createGameInputAdapter(Vector2 mousePosition, Viewport viewport, OrthographicCamera camera) {
        return new InputAdapter() {
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                if (button == Input.Buttons.LEFT) {
                    lastDragPosition = null;
                }
                return true;
            }

            private Vector2 lastDragPosition;
            private Vector2 newDragPosition = new Vector2();

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    float offsetX = 0;
                    float offsetY = 0;
                    if (lastDragPosition == null) {
                        lastDragPosition = new Vector2(screenX, screenY);
                    } else {
                        newDragPosition.set(screenX, screenY);
                        viewport.unproject(newDragPosition);
                        viewport.unproject(lastDragPosition);

                        System.out.println("Old " + lastDragPosition);
                        System.out.println("New " + newDragPosition);
                        offsetX = lastDragPosition.x - newDragPosition.x;
                        offsetY = lastDragPosition.y - newDragPosition.y;
                        lastDragPosition.set(screenX, screenY);
                    }
                    System.out.println(offsetX);
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
                return true;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {

                mousePosition.set(Gdx.input.getX(), Gdx.input.getY());
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
