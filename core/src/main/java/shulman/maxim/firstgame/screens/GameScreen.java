package shulman.maxim.firstgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import shulman.maxim.firstgame.Main;
import shulman.maxim.firstgame.input.GameInputHandler;
import shulman.maxim.firstgame.tools.TileUtils;
import shulman.maxim.firstgame.state.GameStateHandler;

public class GameScreen implements Screen {

    private Vector2 touchPos;
    private Main game;
    private InputAdapter getGameInputAdapter;
    private GameStateHandler gameStateHandler;

    public GameScreen(Main game) {
        this.game = game;
        this.gameStateHandler = game.getGameState();
        this.getGameInputAdapter = game.getGameInputAdapter();
        touchPos = new Vector2();
        //myGameAdapter = GameInputHandler.createGameInputAdapter(touchPos, game.getGameViewport(), game.getCamera());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(getGameInputAdapter);
    }

    @Override
    public void render(float v) {
        draw();

    }

    private void draw() {
        ScreenUtils.clear(Color.WHITE);
        game.getSpriteBatch().setProjectionMatrix(game.getCamera().combined);
        game.getSpriteBatch().begin();
        TileUtils.drawAllTiles(gameStateHandler.getTiles(), game.getGameViewport(), game.getSpriteBatch());
        gameStateHandler.getTiles().stream().filter(tile -> tile.getBoundsHexagon().contains(touchPos)).forEach(
            tile -> TileUtils.drawTileSelected(tile, game.getGameViewport(),
                game.getSpriteBatch()));
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
