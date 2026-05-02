package com.drawing.model.shape;

import com.drawing.util.ColorDecode;

import java.awt.*;

public class Rectangle implements Drawable {

    private double x0, y0, x1, y1;
    private Color c;

    public Rectangle(double x0, double y0, double x1, double y1, Color c) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.c = c;
    }

    public double getX0() {
        return x0;
    }

    public double getY1() {
        return y1;
    }

    public double getX1() {
        return x1;
    }

    public double getY0() {
        return y0;
    }

    public double getWidth() {
        return x1-x0;
    }

    public Color getColor() {
        return c;
    }

    public double getHeight() {
        return y1-y0;
    }

    @Override
    public void accept(DrawingVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return
            "rectangle "+
            x0+" "+
            y0+" "+
            x1+" "+
            y1+" "+
            ColorDecode.getName(c)
        ;
    }


}