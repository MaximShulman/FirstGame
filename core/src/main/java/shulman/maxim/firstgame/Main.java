package shulman.maxim.firstgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import shulman.maxim.firstgame.input.GameInputHandler;
import shulman.maxim.firstgame.logic.GameLogicHandler;
import shulman.maxim.firstgame.screens.GameScreen;
import shulman.maxim.firstgame.state.GameStateHandler;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    private GameStateHandler gameStateHandler;
    private GameLogicHandler gameLogicHandler;

    public InputAdapter getGameInputAdapter() {
        return gameInputAdapter;
    }

    private InputAdapter gameInputAdapter;

    private BitmapFont defaultTextFont;
    private InputProcessor inputProcessor;

    private AssetManager assetManager;
    private FitViewport gameViewport;

    public AssetManager getAssetManager() {
        return assetManager;
    }

    private OrthographicCamera camera;
    public SpriteBatch batch;

    public GameStateHandler getGameState() {
        return gameStateHandler;
    }
    public GameLogicHandler getGameLogic() {
        return gameLogicHandler;
    }

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
        assetManager = new AssetManager();
        camera = new OrthographicCamera();
        gameViewport = new FitViewport(SCREEN_WIDTH / 10, SCREEN_HEIGHT / 10, camera);
        gameViewport.setCamera(camera);
        camera.position.set(gameViewport.getWorldWidth()/2, gameViewport.getWorldHeight()/2,0);
        batch = new SpriteBatch();

        assetManager.load("empty_hexagon_unselected.png", Texture.class);
        assetManager.load("empty_hexagon_selected.png", Texture.class);
        assetManager.load("planet_hexagon_unselected.png", Texture.class);
        assetManager.load("planet_hexagon_selected.png", Texture.class);
        assetManager.finishLoading();
        gameStateHandler = new GameStateHandler(this);
        gameLogicHandler = new GameLogicHandler(gameStateHandler);
        gameInputAdapter = new GameInputHandler(gameLogicHandler).createGameInputAdapter(this.gameViewport, this.camera);
        setScreen(new GameScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        getGameViewport().update(width, height, false);
        camera.update();
    }

    @Override
    public void dispose(){
        batch.dispose();
        defaultTextFont.dispose();
    }
}
