package shulman.maxim.firstgame.entities.world;

import com.badlogic.gdx.graphics.Texture;

public class Planet {
    private String name;
    private Texture texture;

    public Planet(String name) {
        this.name = name;
        texture = new Texture("planet_texture.png");
    }

    public String getName() {
        return name;
    }

    public void drawPlanet() {

    }
}
