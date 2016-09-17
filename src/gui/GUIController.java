/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import engine.Core;
import static engine.Core.is3D;
import graphics.Window3D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import util.Vec2;

/**
 *
 * @author Kosmic
 */
public abstract class GUIController {

    private static boolean suppressKeys = false;
    private final static List<GUI> GUIS = new ArrayList();

    public static void setSuppressed(boolean sprst) {

        suppressKeys = sprst;
    }

    public static boolean isSuppressed() {

        return suppressKeys;
    }

    public static void init() {

        Core.update.onEvent(() -> update());
        Core.renderLayer(100).onEvent(() -> draw());
        InputManager.init();
    }

    public static void addGUIs(GUI... guis) {

        GUIS.addAll(Arrays.asList(guis));
    }

    public static void update() {

        GUIS.forEach(GUI::update);
    }

    public static void draw() {

        Window3D.guiProjection();
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        GUIS.forEach(GUI::draw);

        if (is3D) {

            Window3D.resetProjection();
        }
    }

    public static GUI containsClick(Vec2 click) {

        for (GUI g : GUIS) {

            if (g.containsClick(click)) {

                return g;
            }
        }

        return null;
    }

    public static void pushToTop(GUI gui) {

        GUIS.remove(gui);
        GUIS.add(gui);
    }
}
