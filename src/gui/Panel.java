/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import graphics.Graphics2D;
import gui.components.Component;
import java.util.ArrayList;
import java.util.List;
import util.Color4;
import util.Vec2;

/**
 *
 * @author Kosmic
 */
public class Panel implements PanelAppend {

    private List<PanelAppend> components;
    private Panel parent;
    private GUI gui;

    private Vec2 position;
    private Vec2 dimension;

    private Color4 borderCol;
    private Color4 fillCol;

    public Panel(Vec2 pos, Vec2 dim, Color4 border, Color4 fill) {

        components = new ArrayList();
        position = pos;
        dimension = dim;
        borderCol = border;
        fillCol = fill;
    }

    @Override
    public Vec2 getAbsolutePos() {

        Vec2 ap = position;

        if (parent != null) {

            ap.add(parent.getAbsolutePos());
        }

        return ap;
    }

    void setGUI(GUI gui) {

        this.gui = gui;
    }

    public GUI getGUI() {

        return gui;
    }

    @Override
    public void setParent(Panel p) {

        parent = p;
    }

    public Panel add(PanelAppend c) {

        components.add(c);
        c.setParent(this);
        return this;
    }

    public Panel remove(PanelAppend c) {

        components.remove(c);
        c.setParent(null);
        return this;
    }

    public void pushToTop(PanelAppend c) {

        components.remove(c);
        components.add(c);
    }

    @Override
    public void update() {

        components.forEach(PanelAppend::update);
    }
    
    @Override
    public boolean containsClick(Vec2 click){
        
        Vec2 vec = getAbsolutePos();
        return click.containedBy(vec, vec.add(dimension));
    }
    
    public Component getClickedComponent(Vec2 click){
        
        for (int i = components.size() - 1; i >= 0; i--) {
            
            PanelAppend pa = components.get(i);
            
            if(pa instanceof Panel){
                
                pa = ((Panel) pa).getClickedComponent(click);
            }
            
            if(pa != null && pa instanceof Component && pa.containsClick(click)){
                
                return (Component) pa;
            }
        }
        
        return null;
    }

    @Override
    public void draw(Vec2 rPos) {

        Graphics2D.fillRect(position.add(rPos), dimension, fillCol);
        Graphics2D.drawRect(position.add(rPos), dimension, borderCol);

        components.forEach(pa -> {

            pa.draw(position.add(rPos));
        });
    }
}
