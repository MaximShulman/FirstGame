package shulman.maxim.firstgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.HashSet;
import shulman.maxim.firstgame.Main;
import shulman.maxim.firstgame.entities.world.EmptyTile;
import shulman.maxim.firstgame.entities.world.Tile;
import shulman.maxim.firstgame.tools.InputHandler;
import shulman.maxim.firstgame.tools.TileUtils;

public class GameScreen implements Screen {

    private Vector2 touchPos;
    private Main game;
    private HashSet<Tile> tiles;
    private InputAdapter myGameAdapter;

    public GameScreen(Main game) {
        this.game = game;
        tiles = TileUtils.populateWorld(4, game.getGameViewport());
        touchPos = new Vector2();
        myGameAdapter = InputHandler.createGameInputAdapter(touchPos, game.getGameViewport(), game.getCamera());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(myGameAdapter);
    }

    @Override
    public void render(float v) {
        input();

        draw();

    }

    private void input() {
        touchPos.set(Gdx.input.getX(), Gdx.input.getY());
        game.getGameViewport().unproject(touchPos);

    }

    private void draw() {
        ScreenUtils.clear(Color.WHITE);
        game.getSpriteBatch().setProjectionMatrix(game.getCamera().combined);
        game.getSpriteBatch().begin();
        TileUtils.drawAllTiles(tiles, game.getGameViewport(), game.getSpriteBatch());
        tiles.stream().filter(tile -> tile.getBoundsHexagon().contains(touchPos)).forEach(
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
