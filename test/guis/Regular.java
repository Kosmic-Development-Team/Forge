/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import engine.Core;
import graphics.data.Sprite;
import graphics.data.Texture;
import gui.GUI;
import gui.GUIController;
import gui.Panel;
import gui.components.Button;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.glTexParameteri;
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
        Panel grant = new Panel(new Vec2(100, 50), new Vec2(1000), Color4.BLACK, Color4.gray(0.5));
        gant.addPanel(grant);
        Texture s0 = new Sprite("footstep").getTexture();
        Texture s1 = new Sprite("footstep_white").getTexture();
        Texture s2 = new Sprite("yucky").getTexture();
        Button tb = new Button(s0, s1, s2, new Vec2(50), new Vec2(200), false);

        tb.getHovering().filter(p -> !p).onEvent((() -> {

            tb.setDrawMode(0);
        }));

        tb.getHovering().filter(p -> p).onEvent(() -> {

            tb.getStream().sendEvent();
            if (tb.getStream().get()) {

                tb.setDrawMode(1);
            } else {

                tb.setDrawMode(2);
            }
        });

        grant.add(tb);
        GUIController.addGUIs(gant);

        Core.run();
    }
}
