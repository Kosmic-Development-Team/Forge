/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import graphics.Graphics2D;
import graphics.data.Texture;
import gui.GUIFont;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import util.Color4;
import util.Vec2;

/**
 *
 * @author grant
 */
public class Label extends Component {

    String text;
    GUIFont font;
    int size;
    int format;
    
    public static final int ALIGN_CENTER = 0;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;

    public Label(Vec2 pos, int size, GUIFont font, String t, int format) {

        position = pos;
        this.size = size;
        this.font = font;
        this.format = format;
        text = t;
        dimension = new Vec2(t.length() * font.getTextures().get(0).getImageWidth(), font.getTextures().get(0).getImageHeight());
    }

    public Label(Vec2 pos, int size, GUIFont font, int format) {

        this(pos, size, font, "", format);
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
        
        for (int i = 0; i < text.length(); i++) {
            
            Texture s = font.getTextures().get(text.charAt(i));
            int height = s.getImageHeight() * size;
            int width = s.getImageWidth() * size;
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            
            switch(format){
                
                case ALIGN_CENTER:
                    Graphics2D.drawSprite(s, pos.add(position).add(new Vec2(width * i + width / 2 - width * text.length()/2,height / 2)), new Vec2(size), 0, Color4.BLACK);
                    break;
                case ALIGN_LEFT:
                    Graphics2D.drawSprite(s, pos.add(position).add(new Vec2(width * i + width / 2,height)), new Vec2(size), 0, Color4.BLACK);
                    break;
                case ALIGN_RIGHT:
                    Graphics2D.drawSprite(s, pos.add(position).add(new Vec2(width * i + width / 2 - width * text.length(),height / 2)), new Vec2(size), 0, Color4.BLACK);
                    break;
            }
        }
    }

    public Label setText(String t) {

        text = t;
        dimension = new Vec2(t.length() * font.getTextures().get(0).getImageWidth(), font.getTextures().get(0).getImageHeight());
        return this;
    }

    public String getText() {

        return text;
    }
}
