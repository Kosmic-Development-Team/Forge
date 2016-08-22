/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import engine.Core;
import graphics.data.Sprite;
import gui.components.TButton;
import util.Color4;
import util.Vec2;

/**
 *
 * @author Kosmic
 */
public class Testiness {
    
    public static void main(String[] args) {
        
        Core.init();
        
        GUI gant = new GUI(true);
        Panel grant = new Panel(new Vec2(100, 50), new Vec2(1000), Color4.BLACK, Color4.WHITE);
        gant.addPanel(grant);
        Sprite s = new Sprite("yucky");
        TButton tb = new TButton(s, Vec2.ZERO, false);
        tb.getStream().filter(p ->  p).onEvent(() -> System.out.println("SwitchCars Start"));
        tb.getStream().filter(p ->  !p).onEvent(() -> System.out.println("Out Clicked"));
        grant.add(tb);
        
        GUIController.addGUIs(gant);
        
        Core.run();
    }
}
