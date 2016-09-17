/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import graphics.Graphics2D;
import graphics.data.Texture;
import graphics.loading.SpriteContainer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
public class GUIFont {

    private final String name;
    private List<Texture> textures = new ArrayList();

    public GUIFont(String name, String filepath) throws IOException {

        this.name = name;
        textures = SpriteContainer.loadSprite(filepath, 32, 8);
    }

    public GUIFont register() {

        GUIFontController.registerFont(name, this);
        return this;
    }

    public List<Texture> getTextures() {

        return textures;
    }

    public String getName() {

        return name;
    }

    public static void drawString(String s, GUIFont f, Vec2 pos, double size, Color4 color) {
        
        int width = (int) (f.getTextures().get(0).getImageWidth() * size);
        int height = (int) (f.getTextures().get(0).getImageHeight() * size);
        
        for (int i = 0; i < s.length(); i++) {
            
            Texture tex = f.getTextures().get(s.charAt(i));
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            Graphics2D.drawSprite(tex, pos.add(new Vec2(width * i, 0)).add(new Vec2(width / 2,height / 2)), new Vec2(size), 0, color);
        }
    }

}
