package shulman.maxim.firstgame.tools;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Hexagon extends Polygon {

    public Hexagon(float[] vertices){
        super(vertices);
    }

    @Override
    public boolean contains(float x, float y) {
        float[] vertices = this.getTransformedVertices();
        int numFloats = vertices.length;
        int intersects = 0;

        for(int i = 0; i < numFloats; i += 2) {
            float x1 = vertices[i];
            float y1 = vertices[i + 1];
            float x2 = vertices[(i + 2) % numFloats];
            float y2 = vertices[(i + 3) % numFloats];
            if ((y1 <= y && y < y2 || y2 <= y && y < y1) && x < (x2 - x1) / (y2 - y1) * (y - y1) + x1) {
                ++intersects;
            }
        }

        return (intersects & 1) == 1;
    }

    @Override
    public boolean contains(Vector2 point) {
        return contains(point.x, point.y);
    }

}
