package shulman.maxim.firstgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.HashSet;
import shulman.maxim.firstgame.Main;
import shulman.maxim.firstgame.entities.world.EmptyTile;
import shulman.maxim.firstgame.entities.world.Tile;
import shulman.maxim.firstgame.tools.TileUtils;

public class GameScreen implements Screen {

    private Vector2 touchPos;
    private Main game;
    private HashSet<Tile> tiles;

    public GameScreen(Main game){
        this.game = game;
        tiles = TileUtils.populateWorld(10, game.getGameViewport());
        touchPos = new Vector2();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.WHITE);
        game.getSpriteBatch().setProjectionMatrix(game.getCamera().combined);
        game.getSpriteBatch().begin();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY());
        game.getGameViewport().unproject(touchPos);

        TileUtils.drawAllTiles(tiles, game.getGameViewport(), game.getSpriteBatch());
        tiles.stream().filter(tile -> tile.getBoundsHexagon().contains(touchPos)).forEach(tile -> TileUtils.drawTileSelected(tile, game.getGameViewport(), game.getSpriteBatch()));
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
