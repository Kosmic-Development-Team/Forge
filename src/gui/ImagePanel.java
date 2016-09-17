/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import graphics.Graphics2D;
import graphics.data.Texture;
import graphics.loading.SpriteContainer;
import util.Color4;
import util.Vec2;

/**
 *
 * @author grant
 */
public class ImagePanel extends Panel {
    
    protected Texture background;
    
    public ImagePanel(Vec2 pos, Vec2 dim, String filepath) {
        
        super(pos, dim, Color4.TRANSPARENT, Color4.TRANSPARENT);
        background = SpriteContainer.loadSprite(filepath);
        
    }
    
    public ImagePanel(Vec2 pos, String filepath) {
        
        super(pos, null, Color4.TRANSPARENT, Color4.TRANSPARENT);
        background = SpriteContainer.loadSprite(filepath);
        dimension = new Vec2(background.getImageWidth(), background.getImageHeight());
        
    }
    
    @Override
    public void draw(Vec2 rPos) {
        
        super.draw(rPos);
        Graphics2D.drawSprite(background, rPos.add(position).add(dimension.divide(2)), dimension, 0, Color4.WHITE);
    }
    
    public Texture getBackground(){
        
        return background;
    }
    
    public ImagePanel setBackground(Texture t){
        
        background = t;
        return this;
    }
}
