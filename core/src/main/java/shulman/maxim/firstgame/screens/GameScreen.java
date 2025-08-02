package shulman.maxim.firstgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import shulman.maxim.firstgame.Main;
import shulman.maxim.firstgame.entities.tools.TileUtils;
import shulman.maxim.firstgame.entities.world.EmptyTile;
import shulman.maxim.firstgame.entities.world.Planet;
import shulman.maxim.firstgame.entities.world.PlanetTile;

public class GameScreen implements Screen {

    private Vector2 touchPos;
    private Main game;
    private ArrayList<EmptyTile> tiles;

    public GameScreen(Main game){
        this.game = game;
        tiles = TileUtils.populateWorld(2, game);
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
        //tiles.stream().filter(tile -> tile.getBoundsHexagon().contains(touchPos)).forEach(tile -> TileUtils.drawTile(tile, game));
        TileUtils.drawTile(new PlanetTile(0,0,0,game,new Planet("earth")), game);
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
