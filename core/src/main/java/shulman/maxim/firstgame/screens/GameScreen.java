package shulman.maxim.firstgame.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.ScreenUtils;
import shulman.maxim.firstgame.Main;

public class GameScreen implements Screen {
    public static float SPEED = 40;

    private Texture image;
    private Texture planetTexture;
    private Circle planetCollision;
    private Sprite planetSprite;
    float x;
    float y;
    Main game;

    public GameScreen(Main game){
        this.game = game;
    }
    @Override
    public void show() {

        image = new Texture("libgdx.png");
        planetTexture = new Texture("circle.png");
        planetCollision = new Circle();
        planetSprite = new Sprite(planetTexture);
        x = 0;
        y = 0;
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.WHITE);

        if(Gdx.input.isKeyPressed(Keys.UP)){
            y += SPEED * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN)){
            y -= SPEED * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Keys.RIGHT)){
            x += SPEED * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Keys.LEFT)){
            x -= SPEED * Gdx.graphics.getDeltaTime();
        }
        game.getSpriteBatch().begin();
        game.getSpriteBatch().draw(planetTexture, x, y);
        game.getSpriteBatch().end();
    }



    @Override
    public void resize(int i, int i1) {

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
