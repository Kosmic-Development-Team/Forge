/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.components.Component;
import java.util.ArrayList;
import java.util.List;
import util.Vec2;

/**
 *
 * @author Kosmic
 */
public class GUI {

    private List<Panel> panels;
    private boolean selectFirst;

    public GUI(boolean selectFirst) {

        panels = new ArrayList();
        this.selectFirst = selectFirst;
    }

    public boolean isSelectFirst() {

        return selectFirst;
    }

    public void setSelectFirst(boolean selectFirst) {

        this.selectFirst = selectFirst;
    }

    public boolean containsClick(Vec2 click) {

        for (Panel p : panels) {

            if (p.containsClick(click)) {

                return true;
            }
        }

        return false;
    }
    
    public Component getClickedComponent(Vec2 click){
        
        for (int i = panels.size() - 1; i >= 0; i--) {
            
            Component c = panels.get(i).getClickedComponent(click);
            
            if(c != null){
                
                return c;
            }
        }
        
        return null;
    }

    public GUI addPanel(Panel p) {

        panels.add(p);
        p.setGUI(this);
        return this;
    }

    public GUI removePanel(Panel p) {

        panels.remove(p);
        p.setGUI(this);
        return this;
    }

    public void pushToTop(Panel p) {

        panels.remove(p);
        panels.add(p);
    }

    public void update() {

        panels.forEach(Panel::update);
    }

    public void draw() {

        panels.forEach(p -> {

            p.draw(Vec2.ZERO);
        });
    }
}
