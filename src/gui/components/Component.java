/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import engine.Core;
import engine.Input;
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
    private final Signal<Boolean> hovering = new Signal(false);
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

    public void setPosition(Vec2 position) {
        
        this.position = position;
    }

    public Vec2 getPosition() {
        
        return position;
    }

    public Vec2 getDimension() {
        
        return dimension;
    }

    public Signal<T> getStream() {
        
        return stream;
    }

    public Signal<Boolean> getHovering() {

        return hovering;
    }

    @Override
    public void update() {

        Vec2 shifted = Input.getMouse().add(new Vec2(Core.screenWidth / 2.0, Core.screenHeight / 2.0));
        hovering.set(containsClick(shifted));
    }

    public abstract void onKey(int key);

    public abstract void onClick();

    public abstract void onRelease();
}
