package shulman.maxim.firstgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import shulman.maxim.firstgame.Main;

public class MainMenuScreen implements Screen {
    private Skin skin;
    private Stage stage;
    private FitViewport viewport;
    private Main game;

    public MainMenuScreen(Main game) {
        this.game = game;
        this.skin = game.getAssetManager().get("ui/metal-ui.json");
        this.viewport = game.getMainMenuViewport();
        this.stage = new Stage(viewport);
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        TextButton start = new TextButton("Start", skin);
        root.add(start);
        root.row();
        TextButton exit = new TextButton("Exit", skin);
        root.add(exit);
        root.row().pad(10);
        start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setGameScreen();
            }
        });

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                Gdx.app.exit();
            }
        });
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {}

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.WHITE);
        viewport.apply(true);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        game.resize(width,height);
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
        stage.dispose();
    }
}
