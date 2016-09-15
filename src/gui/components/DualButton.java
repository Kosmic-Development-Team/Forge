/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import graphics.data.Sprite;
import util.Vec2;

/**
 *
 * @author Kosmic
 */
public class DualButton extends Button {

    protected Sprite pressedTex;
    protected boolean pressedDraw;

    public DualButton(Sprite unpressed, Sprite pressed, Vec2 pos, boolean tog) {

        super(unpressed, pos, tog);
        pressedTex = pressed;
        pressedDraw = false;
    }

    public boolean isPressedDraw() {

        return pressedDraw;
    }

    public void setPressedDraw(boolean pressedDraw) {

        this.pressedDraw = pressedDraw;
    }

    public Sprite getPressedTex() {

        return pressedTex;
    }

    public void setPressedTex(Sprite pressedTex) {

        this.pressedTex = pressedTex;
    }

    @Override
    public void draw(Vec2 rPos) {

        if (pressedDraw) {
            
            texture.draw(position.add(new Vec2(texture.getTexture().getImageWidth() / 2.0, 
                    texture.getTexture().getImageHeight() / 2.0)).add(rPos), 0);
        }else{
            
            pressedTex.draw(position.add(new Vec2(texture.getTexture().getImageWidth() / 2.0, 
                    texture.getTexture().getImageHeight() / 2.0)).add(rPos), 0);
        }
    }

}
