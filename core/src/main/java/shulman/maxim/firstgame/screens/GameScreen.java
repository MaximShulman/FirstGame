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
        Tile tileA = new EmptyTile(0,1,0,game.getGameViewport());
        Tile tileB = new EmptyTile(1,0,0,game.getGameViewport());
        System.out.println("Tile A: " + tileA.getX() + "," + tileA.getY() + "," + tileA.getZ());
        System.out.println("Tile B: " + tileB.getX() + "," + tileB.getY() + "," + tileB.getZ());
        System.out.println("Equal? " + tileA.equals(tileB));
        tiles = TileUtils.populateWorld(1, game.getGameViewport());
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
        //TileUtils.drawTileUnselected(new PlanetTile(0,0,0,game,new Planet("earth")), game);
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
