/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author grant
 */

public class GUIFontController {
    
    private static boolean suppressKeys = false;
    private static Map<String, GUIFont> fonts = new HashMap();
    
    static void setSuppressed(boolean sprst){
        
        suppressKeys = sprst;
    }
    
    public static boolean isSuppressed(){
        
        return suppressKeys;
    }
    
    public static GUIFont getFont(String name) {
        
        return fonts.get(name);
    }
    
    public static void registerFont(String name, GUIFont font) {
        
        fonts.put(name, font);
    }
    
    public static Map<String, GUIFont> getAllFonts() {
        
        return fonts;
    }
    
    public static void registerFonts(Map<String, GUIFont> fonts) {
        
        fonts.putAll(fonts);
    }
}
