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
import gui.components.DualButton;
import util.Color4;
import util.Vec2;

/**
 *
 * @author Kosmic
 */
public class DualButtonTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Core.init();
        
        GUI gant = new GUI(true);
        Panel grant = new Panel(new Vec2(100, 50), new Vec2(1000), Color4.BLACK, Color4.gray(0.5));
        gant.addPanel(grant);
        Sprite un = new Sprite("footstep");
        Sprite pr = new Sprite("footstep_white");
        DualButton db = new DualButton(un, pr, Vec2.ZERO, false);
        
        db.getStream().filter(p ->  p).onEvent(() -> {
            
            db.setPressedDraw(false);
        });
        
        db.getStream().filter(p ->  !p).onEvent(() -> {
            
            db.setPressedDraw(true);
        });
        
        grant.add(db);
        
        GUIController.addGUIs(gant);
        
        Core.run();
    }
}
