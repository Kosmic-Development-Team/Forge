/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import engine.Signal;
import graphics.data.Sprite;
import util.Vec2;

/**
 *
 * @author Kosmic
 */
public class Button extends Component<Boolean> {

    protected Sprite texture;
    protected boolean toggle;

    public Button(Sprite tex, Vec2 pos, boolean tog) {

        texture = tex;
        position = pos;
        dimension = new Vec2(tex.getTexture().getImageWidth() * tex.scale.x,
                tex.getTexture().getImageHeight() * tex.scale.y);
        stream = new Signal(false);
        toggle = tog;
    }

    @Override
    public void onRelease() {

        if (!toggle) {

            stream.set(false);
        }
    }

    @Override
    public void onClick() {

        stream.set(toggle ? !stream.get() : true);
    }

    public Signal<Boolean> getStream() {

        return stream;
    }

    public Sprite getTex() {

        return texture;
    }

    public Vec2 getPos() {

        return position;
    }

    public Vec2 getDim() {

        return dimension;
    }

    @Override
    public void draw(Vec2 rPos) {
        
        texture.draw(position.add(new Vec2(texture.getTexture().getImageWidth() / 2.0, texture.getTexture().getImageHeight() / 2.0)).add(rPos), 0);
    }

    @Override
    public void onKey(int key) {}

    @Override
    public void update() {}
}
