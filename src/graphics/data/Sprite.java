package graphics.data;

import graphics.Graphics2D;
import graphics.Graphics3D;
import graphics.loading.SpriteContainer;
import java.util.ArrayList;
import util.Color4;
import util.Vec2;
import util.Vec3;

public class Sprite {

    public ArrayList<Texture> textureArray;
    public String name;
    public double imageIndex;
    public double imageSpeed;
    public boolean visible;
    public Vec2 scale;
    public Color4 color;

    public Sprite(String name) {
        this(name, 1, 1);
    }

    public Sprite(String name, int x, int y) {
        setSprite(name, x, y);
        imageIndex = 0;
        imageSpeed = 0;
        visible = true;
        scale = new Vec2(1);
        color = Color4.WHITE;
    }

    public void draw(Vec2 pos, double rot) {
        if (visible) {
            Graphics2D.drawSprite(getTexture(), pos, scale, rot, color);
        }
    }

    public void draw(Vec3 pos, double tilt, double rot) {
        if (visible) {
            Graphics3D.drawSprite(getTexture(), pos, scale, tilt, rot, color);
        }
    }

    public Texture getTexture() {
        return textureArray.get((int) imageIndex % textureArray.size());
    }

    public void setSprite(String name) {
        setSprite(name, 1, 1);
    }

    public void setSprite(String name, int x, int y) {
        this.name = name;
        textureArray = SpriteContainer.loadSprite(name, x, y);
    }
}
