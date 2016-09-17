/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import engine.Signal;
import graphics.Graphics2D;
import graphics.data.Sprite;
import graphics.data.Texture;
import util.Color4;
import util.Vec2;

/**
 *
 * @author Kosmic
 */
public class Button extends Component<Boolean> {
    
    public static final int UNPRESSED_MODE = 0;
    public static final int PRESSED_MODE = 1;
    public static final int HOVER_MODE = 2;
    
    protected Texture unpressedTex;
    protected Texture pressedTex;
    protected Texture hoverTex;
    protected boolean toggle;
    protected int drawMode;
    
    public Button(Texture unpressed, Texture pressed, Texture hover, Vec2 pos, boolean tog) {
        
        unpressedTex = unpressed;
        pressedTex = pressed;
        hoverTex = hover;
        
        position = pos;
        dimension = new Vec2(unpressed.getImageWidth(),
                unpressed.getImageHeight());
        stream = new Signal(false);
        toggle = tog;
        drawMode = 0;
        
    }
    
    public Button(Texture unpressed, Texture pressed, Texture hover, Vec2 pos, Vec2 dim, boolean tog) {
        
        unpressedTex = unpressed;
        pressedTex = pressed;
        hoverTex = hover;
        
        position = pos;
        dimension = dim;
        stream = new Signal(false);
        toggle = tog;
        drawMode = 0;
        
    }
    
    public Texture getUnpressedTex() {
        return unpressedTex;
    }
    
    public void setUnpressedTex(Texture unpressedTex) {
        this.unpressedTex = unpressedTex;
    }
    
    public Texture getHoverTex() {
        return hoverTex;
    }
    
    public void setHoverTex(Texture hoverTex) {
        this.hoverTex = hoverTex;
    }
    
    public int getDrawMode() {
        return drawMode;
    }
    
    public void setDrawMode(int drawMode) {
        
        if (drawMode < 0 && drawMode > 2) {
            
            throw new IllegalArgumentException();
        }
        
        Texture bs = null;
        
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
    
    public Texture getTex() {
        
        return unpressedTex;
    }
    
    public Texture getPressedTex() {
        
        return pressedTex;
    }
    
    public void setPressedTex(Texture pressedTex) {
        
        this.pressedTex = pressedTex;
    }
    
    @Override
    public void draw(Vec2 rPos) {
        
        Texture tex = null;
        switch (drawMode) {
            
            case 0:
                
                tex = pressedTex;
                break;
            case 1:
                
                tex = unpressedTex;
                break;
            case 2:
                
                tex = hoverTex;
                break;
        }
        
        Graphics2D.drawSprite(tex, rPos.add(position).add(dimension.divide(2)), dimension.divide(new Vec2(tex.getImageWidth(),
                                tex.getImageHeight())), 0, Color4.WHITE);
    }
    
    @Override
    public void onKey(int key) {
    }
}
