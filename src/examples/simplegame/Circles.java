/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.simplegame;

import examples.Premade2D;
import engine.AbstractEntity;
import engine.Core;
import engine.Input;
import engine.Signal;
import graphics.Graphics2D;
import graphics.Window2D;
import static org.lwjgl.opengl.GL11.*;
import util.Color4;
import static util.Color4.BLACK;
import util.Mutable;
import util.Util;
import util.Vec2;

/**
 *
 * @author RSoiffer16
 */
public class Circles {

    public static void main(String[] args) {
        Core.init();
        Window2D.background = BLACK;

        glBlendFunc(GL_SRC_ALPHA, GL_ONE);

        Runnable newCircle = () -> new AbstractEntity.LAE(c -> {
            Signal<Vec2> position = Premade2D.makePosition(c);
            position.set(Input.getMouse());
            Signal<Vec2> velocity = Premade2D.makeVelocity(c);
            velocity.set(Vec2.randomCircle(50).add(new Vec2(0, 150)));

            Mutable<Color4> color = new Mutable(new Color4(1, .2, .05, .1));
            c.onUpdate(dt -> color.o = color.o.withA(color.o.a - Math.random() * dt / 10));
            c.onRender(() -> Graphics2D.fillEllipse(position.get(), new Vec2(10, 10), color.o, 20));

            Core.update.filter(dt -> color.o.a < 0).onEvent(c::destroy);

            c.onUpdate(dt -> {
                if (Math.abs(position.get().x) + 20 > Window2D.viewSize.x / 2) {
                    velocity.edit(new Vec2(-1, 1)::multiply);
                }
                if (Math.abs(position.get().y) + 20 > Window2D.viewSize.y / 2) {
                    velocity.edit(new Vec2(1, -1)::multiply);
                }
                position.edit(p -> p.clamp(Window2D.viewSize.multiply(-.5).add(new Vec2(20, 20)),
                        Window2D.viewSize.multiply(.5).subtract(new Vec2(20, 20))));
            });

            //Core.timer(20, c::destroy);
        }).create();

        Input.whileMouseDown(1).onEvent(() -> Util.repeat(30, newCircle));

        Input.whenMouse(1, true).onEvent(() -> {
            for (int i = 0; i < 20; i++) {
                newCircle.run();
            }
        });
        Core.interval(.1).filter(Input.mouseSignal(0)).onEvent(newCircle);

        Core.run();
    }
}
