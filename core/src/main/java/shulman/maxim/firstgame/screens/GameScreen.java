package shulman.maxim.firstgame.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import shulman.maxim.firstgame.Main;
import shulman.maxim.firstgame.input.GameInputHandler;
import shulman.maxim.firstgame.tools.TileUtils;
import shulman.maxim.firstgame.state.GameStateHandler;

public class GameScreen implements Screen {

    private Vector2 touchPos;
    private Main game;
    private GameInputHandler gameInputHandler;
    private InputAdapter getGameInputAdapter;
    private GameStateHandler gameStateHandler;
    private InputMultiplexer inputMultiplexer;

    public GameScreen(Main game) {
        this.game = game;
        this.gameStateHandler = game.getGameState();
        this.getGameInputAdapter = game.getGameInputAdapter();
        this.gameInputHandler = game.getGameInputHandler();
        this.inputMultiplexer = new InputMultiplexer();
        touchPos = new Vector2();
    }

    @Override
    public void show() {
        inputMultiplexer.addProcessor(new GestureDetector(gameInputHandler.DoubleClickDetector()));
        inputMultiplexer.addProcessor(getGameInputAdapter);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float v) {
        gameInputHandler.input();
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MainMenuScreen(game));
        }
        game.getGameViewport().apply();
        draw();

    }

    private void draw() {
        ScreenUtils.clear(Color.WHITE);
        game.getSpriteBatch().setProjectionMatrix(game.getCamera().combined);
        game.getSpriteBatch().begin();
        TileUtils.drawAllTiles(gameStateHandler.getTiles(), game.getGameViewport(), game.getSpriteBatch(), false);
        TileUtils.drawAllTiles(gameStateHandler.getSelectedTiles(), game.getGameViewport(), game.getSpriteBatch(), true);
        TileUtils.drawAllRoutes(gameStateHandler.getRoutes(), game.getGameViewport(), game.getSpriteBatch());
        game.getSpriteBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        game.resize(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
