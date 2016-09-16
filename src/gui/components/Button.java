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

    public static final int UNPRESSED_MODE = 0;
    public static final int PRESSED_MODE = 1;
    public static final int HOVER_MODE = 2;

    protected Sprite unpressedTex;
    protected Sprite pressedTex;
    protected Sprite hoverTex;
    protected boolean toggle;
    protected int drawMode;

    public Button(Sprite unpressed, Sprite pressed, Sprite hover, Vec2 pos, boolean tog) {

        unpressedTex = unpressed;
        pressedTex = pressed;
        hoverTex = hover;

        position = pos;
        dimension = new Vec2(unpressed.getTexture().getImageWidth() * unpressed.scale.x,
                unpressed.getTexture().getImageHeight() * unpressed.scale.y);
        stream = new Signal(false);
        toggle = tog;
        drawMode = 0;

    }

    public Sprite getUnpressedTex() {
        return unpressedTex;
    }

    public void setUnpressedTex(Sprite unpressedTex) {
        this.unpressedTex = unpressedTex;
    }

    public Sprite getHoverTex() {
        return hoverTex;
    }

    public void setHoverTex(Sprite hoverTex) {
        this.hoverTex = hoverTex;
    }

    public int getDrawMode() {
        return drawMode;
    }

    public void setDrawMode(int drawMode) {

        if(drawMode < 0 && drawMode > 2){
            
            throw new IllegalArgumentException();
        }
        
        Sprite bs = null;

        switch (drawMode) {

            case 0:

                bs = unpressedTex;
                break;
            case 1:

                bs = pressedTex;
                break;
            case 2:

                bs = hoverTex;
                break;
        }

        dimension = new Vec2(bs.getTexture().getImageWidth() * bs.scale.x,
                bs.getTexture().getImageHeight() * bs.scale.y);
        this.drawMode = drawMode;
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

    public Sprite getTex() {

        return unpressedTex;
    }

    public Sprite getPressedTex() {

        return pressedTex;
    }

    public void setPressedTex(Sprite pressedTex) {

        this.pressedTex = pressedTex;
    }

    @Override
    public void draw(Vec2 rPos) {

        switch (drawMode) {

            case 0:

                pressedTex.draw(position.add(new Vec2(pressedTex.getTexture().getImageWidth() / 2.0,
                        pressedTex.getTexture().getImageHeight() / 2.0)).add(rPos), 0);
                break;
            case 1:

                unpressedTex.draw(position.add(new Vec2(unpressedTex.getTexture().getImageWidth() / 2.0,
                        unpressedTex.getTexture().getImageHeight() / 2.0)).add(rPos), 0);
                break;
            case 2:

                hoverTex.draw(position.add(new Vec2(hoverTex.getTexture().getImageWidth() / 2.0,
                        hoverTex.getTexture().getImageHeight() / 2.0)).add(rPos), 0);
                break;
        }
    }

    @Override
    public void onKey(int key) {
    }
}
