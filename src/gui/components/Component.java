/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import engine.Signal;
import gui.Panel;
import util.Vec2;
import gui.PanelAppend;

/**
 *
 * @author Kosmic
 * @param <T>
 */
public abstract class Component<T> implements PanelAppend {

    protected Vec2 position, dimension;
    protected Signal<T> stream;
    protected Panel parent;

    @Override
    public void setParent(Panel p) {

        parent = p;
    }

    @Override
    public Vec2 getAbsolutePos() {

        return position.add(parent.getAbsolutePos());
    }

    @Override
    public boolean containsClick(Vec2 click) {

        Vec2 vec = getAbsolutePos();
        return click.containedBy(vec, vec.add(dimension));
    }

    @Override
    public void update() {

    }

    public void onKey(int key) {

    }

    public void onClick() {

    }

    public void onRelease() {

    }
}
