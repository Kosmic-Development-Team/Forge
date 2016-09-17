/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import graphics.Graphics2D;
import gui.GUIController;
import gui.GUIFont;
import util.Color4;
import util.Vec2;

/**
 *
 * @author Kosmic
 */
public class TextField extends Component<Integer> {

    private Label text;
    private Color4 borderCol;
    private Color4 fillCol;
    
    private Color4 cursorCol;
    private int cursorPos;
    
    public TextField(Vec2 pos, Vec2 dim, int scale, GUIFont font, int format, Color4 border, Color4 fill, Color4 cursorColor){
        
        position = pos;
        dimension = dim;
        borderCol = border;
        fillCol = fill;
        cursorCol = cursorCol;
        Vec2 txtDraw = dim.multiply(new Vec2(format == Label.ALIGN_CENTER ? 0.5 : (format == Label.ALIGN_LEFT ? 0.0 : 1.0), 0.5))
                .subtract(new Vec2(0, font.getTextures().get(0).getImageHeight() / 2.0 * scale));
        text = new Label(txtDraw, scale, font, "Grant Vandomelen", format);
    }
    
    public TextField(Vec2 pos, Vec2 dim, int scale, GUIFont font, int format, Color4 border, Color4 fill){
        
        this(pos, dim, scale, font, format, border, fill, Color4.BLACK);
    }
    
    @Override
    public void onKey(int key) {

        stream.set(key);
    }

    @Override
    public void onClick() {

        GUIController.setSuppressed(true);
        //cursor placer
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void draw(Vec2 rPos) {

        Graphics2D.fillRect(position.add(rPos), dimension, fillCol);
        Graphics2D.drawRect(position.add(rPos), dimension, borderCol);
        text.draw(position.add(rPos));
    }
}
