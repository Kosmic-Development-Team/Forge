/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import engine.Core;
import graphics.data.Sprite;
import gui.GUI;
import gui.GUIController;
import gui.Panel;
import gui.components.Button;
import util.Color4;
import util.Vec2;

/**
 *
 * @author Kosmic
 */
public class Regular {
    
    public static void main(String[] args) {
        
        Core.init();
        
        GUI gant = new GUI(true);
        Panel grant = new Panel(new Vec2(100, 50), new Vec2(1000), Color4.BLACK, Color4.WHITE);
        gant.addPanel(grant);
        Sprite s = new Sprite("yucky");
        Button tb = new Button(s, Vec2.ZERO, false);
        tb.getStream().filter(p ->  p).onEvent(() -> System.out.println("SwitchCars Start"));
        tb.getStream().filter(p ->  !p).onEvent(() -> System.out.println("Out Clicked"));
        grant.add(tb);
        
        GUIController.addGUIs(gant);
        
        Core.run();
    }
}
