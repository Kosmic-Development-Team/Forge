/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import util.Vec2;

/**
 *
 * @author Kosmic
 */
public interface PanelAppend {
    
    void update();
    
    void draw(Vec2 rPos);
    
    void setParent(Panel p);
    
    Vec2 getAbsolutePos();
    
    boolean containsClick(Vec2 click);
}
