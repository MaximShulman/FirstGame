package shulman.maxim.firstgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import shulman.maxim.firstgame.input.GameInputHandler;
import shulman.maxim.firstgame.logic.GameLogicHandler;
import shulman.maxim.firstgame.screens.GameScreen;
import shulman.maxim.firstgame.screens.MainMenuScreen;
import shulman.maxim.firstgame.state.GameStateHandler;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static final int SCREEN_WIDTH = 128;
    private static final int SCREEN_HEIGHT = 72;
    private GameScreen gameScreen;
    private GameStateHandler gameStateHandler;
    private GameLogicHandler gameLogicHandler;

    public GameInputHandler getGameInputHandler() {
        return gameInputHandler;
    }

    private GameInputHandler gameInputHandler;

    private InputAdapter gameInputAdapter;
    public InputAdapter getGameInputAdapter() {
        return gameInputAdapter;
    }

    private BitmapFont defaultTextFont;
    private InputProcessor inputProcessor;

    private static AssetManager assetManager;
    private FitViewport gameViewport;
    private FitViewport mainMenuViewport;
    public FitViewport getUIViewport() {
        return mainMenuViewport;
    }

    public static AssetManager getAssetManager() {
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

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameScreen() {
        this.setScreen(gameScreen);
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
        gameViewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);
        mainMenuViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameViewport.setCamera(camera);
        camera.position.set(gameViewport.getWorldWidth()/2, gameViewport.getWorldHeight()/2,0);
        batch = new SpriteBatch();

        assetManager.load("ui/metal-ui.json", Skin.class);
        assetManager.load("empty_hexagon_unselected.png", Texture.class);
        assetManager.load("empty_hexagon_selected.png", Texture.class);
        assetManager.load("planet_hexagon_unselected.png", Texture.class);
        assetManager.load("planet_hexagon_selected.png", Texture.class);
        assetManager.load("arrow.png", Texture.class);
        assetManager.finishLoading();
        gameStateHandler = new GameStateHandler(gameViewport, assetManager);
        gameLogicHandler = new GameLogicHandler(gameStateHandler, this.gameViewport);
        gameInputHandler = new GameInputHandler(gameLogicHandler,this.gameViewport, this.camera);
        gameInputAdapter = gameInputHandler.createGameInputAdapter();
        gameScreen = new GameScreen(this);
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        getGameViewport().update(width, height, false);
        getUIViewport().update(width,height,true);
        camera.update();
    }



    @Override
    public void dispose(){
        batch.dispose();
        defaultTextFont.dispose();
    }
}
