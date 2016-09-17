/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import engine.Core;
import engine.Input;
import gui.components.Component;
import util.Vec2;

/**
 *
 * @author Kosmic
 */
public abstract class InputManager {

    private static GUI selectedG;
    private static Component selectedC;
    private static boolean blocked;

    public static void init() {

        Input.mouseSignal(0).filter(p -> !p).onEvent(() -> clicked(false));
        Input.mouseSignal(0).filter(p -> p).onEvent(() -> clicked(true));
        Input.keyPressesSignal().onEvent(() -> keyPressed(Input.getCurrentKey()));
    }
    
    private static void keyPressed(int key){
        
        if (selectedC != null) {
            
            selectedC.onKey(key);
        }
    }

    private static void clicked(boolean pushed) {

        Vec2 shifted = Input.getMouse().add(new Vec2(Core.screenWidth / 2.0, Core.screenHeight / 2.0));
        
        if (!blocked) {

            GUI g = GUIController.containsClick(shifted);
            boolean next = true;

            if(!pushed && g != null && g.isSelectFirst()){
                
                next = g == selectedG;
            }
            
            selectedG = g;
            
            if (selectedG != null && next) {

                selectedC = selectedG.getClickedComponent(shifted);
                clickEvent(pushed);
            }
        }
    }

    private static void clickEvent(boolean pushed) {

        if (selectedC != null) {
            
            if (pushed) {

                selectedC.onClick();
            } else {

                selectedC.onRelease();
            }
        }
    }
}
