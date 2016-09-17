/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import gui.GUIFont;
import util.Color4;
import util.Vec2;

/**
 *
 * @author grant
 */
public class Label extends Component {

    private String text;
    private GUIFont font;
    private double size;
    public int format;
    
    private Color4 color;
    
    public static final int ALIGN_CENTER = 0;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;

    public Label(Vec2 pos, int size, GUIFont font, String t, int format, Color4 color) {

        position = pos;
        this.size = size;
        this.font = font;
        this.format = format;
        this.color = color;
        text = t;
        dimension = new Vec2(t.length() * font.getTextures().get(0).getImageWidth(), font.getTextures().get(0).getImageHeight());
    }

    public Label(Vec2 pos, int size, GUIFont font, int format, Color4 color) {

        this(pos, size, font, "", format, color);
    }

    @Override
    public void onKey(int key) {

    }

    @Override
    public void onClick() {

    }

    @Override
    public void onRelease() {

    }

    @Override
    public void update() {
        
    }

    @Override
    public void draw(Vec2 pos) {
        
        Vec2 aPos = Vec2.ZERO;
        
        switch(format) {
            
            case ALIGN_CENTER:
                
                aPos = pos.add(position).subtract(new Vec2(text.length() / 2.0 * size * font.getTextures().get(0).getImageWidth(),0));
                break;
                
            case ALIGN_LEFT:
                
                aPos = pos.add(position);
                break;
                
            case ALIGN_RIGHT:
                
                aPos = pos.add(position).subtract(new Vec2(text.length() * size * font.getTextures().get(0).getImageWidth(),0));
                break;
        }
        
        GUIFont.drawString(text, font, aPos, size, color);
    }

    public Label setText(String t) {

        text = t;
        dimension = new Vec2(t.length() * font.getTextures().get(0).getImageWidth(), font.getTextures().get(0).getImageHeight());
        return this;
    }

    public String getText() {

        return text;
    }
    
    public GUIFont getFont() {
        
        return font;
    }
    
    public Label setSize(double s) {
        
        size = s;
        dimension = new Vec2(text.length() * font.getTextures().get(0).getImageWidth(), font.getTextures().get(0).getImageHeight());
        return this;
    }
    
    public double getSize() {
        
        return size;
    }
    
    public Label setColor(Color4 c) {
        
        color = c;
        return this;
    }
    
    public Color4 getColor() {
        
        return color;
    }
    
}
