/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import engine.Core;
import gui.GUI;
import gui.GUIController;
import gui.GUIFont;
import gui.GUIFontController;
import gui.Panel;
import gui.components.Label;
import java.io.IOException;
import util.Color4;
import util.Vec2;

/**
 *
 * @author grant
 */
public class LabelTest {
    
    public static void main(String[] args) {
        
        Core.init();
        
        GUI theGUI = new GUI(true);
        Panel panel1 = new Panel(new Vec2(50), new Vec2(500), Color4.TRANSPARENT, Color4.BLUE);
        
        try {
            new GUIFont("simple","font_0").register();
        } catch (IOException e) {
            System.out.println("Missing file.");
        }
        
        Label label = new Label(new Vec2(250), 12, GUIFontController.getFont("simple"), "Hello, world!", Label.ALIGN_CENTER);
        
        panel1.add(label);
        theGUI.addPanel(panel1);
        
        GUIController.addGUIs(theGUI);
        
        Core.run();
    }
}
