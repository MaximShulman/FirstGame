package shulman.maxim.firstgame.ui.gamescreenui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import shulman.maxim.firstgame.Main;

public class PlanetTileUI {

    private Skin skin;
    private Stage stage;
    private FitViewport viewport;
    private Main game;

    public PlanetTileUI(Main game) {
        this.game = game;
        this.skin = game.getAssetManager().get("ui/metal-ui.json");
        this.viewport = game.getUIViewport();
        this.stage = new Stage(viewport);
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        TextButton makeShip = new TextButton("Create Ship", skin);
        root.add(makeShip);
        makeShip.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setGameScreen();
            }
        });
        Gdx.input.setInputProcessor(stage);
    }

}
