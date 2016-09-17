/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import graphics.data.Texture;
import graphics.loading.SpriteContainer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author grant
 */
public class GUIFont {
    
    private String name;
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
    
}
