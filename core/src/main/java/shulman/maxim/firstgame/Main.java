package shulman.maxim.firstgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import shulman.maxim.firstgame.screens.GameScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;

    private BitmapFont defaultTextFont;
    private InputProcessor inputProcessor;


    private FitViewport gameViewport;
    private OrthographicCamera camera;
    public SpriteBatch batch;

    public SpriteBatch getSpriteBatch() {
        return this.batch;
    }

    public FitViewport getGameViewport() {
        return gameViewport;
    }

    public BitmapFont getFont() {
        return defaultTextFont;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    @Override
    public void create() {
        //defaultTextFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        //defaultTextFont.getData().setScale(gameViewport.getWorldHeight() / Gdx.graphics.getHeight());
        camera = new OrthographicCamera();
        gameViewport = new FitViewport(SCREEN_WIDTH / 10, SCREEN_HEIGHT / 10, camera);
        gameViewport.setCamera(camera);
        batch = new SpriteBatch();
        setScreen(new GameScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        getGameViewport().update(width, height, true);
        camera.update();
    }

    @Override
    public void dispose(){
        batch.dispose();
        defaultTextFont.dispose();
    }
}
