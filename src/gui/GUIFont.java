/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import graphics.data.Texture;
import graphics.loading.TextureLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import util.Vec2;

/**
 *
 * @author grant
 */
public class GUIFont {
    
    private String name;
    public List<Texture> textures = new ArrayList();
    
    public GUIFont(String name, String filepath) throws IOException {
        
        this.name = name;
        textures = TextureLoader.getTextures(filepath, 32, 8);
    }
    
    public GUIFont register() {
        
        GUIFontController.registerFont(name, this);
        return this;
    }
    
}
