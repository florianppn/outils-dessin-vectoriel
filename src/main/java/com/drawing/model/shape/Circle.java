package com.drawing.model.shape;

import com.drawing.util.ColorDecode;

import java.awt.*;

public class Circle implements Drawable {

    private double cx, cy, rad;
    private Color c;

    public Circle(double cx, double cy, double rad, Color c) {
        this.cx = cx;
        this.cy = cy;
        this.rad = rad;
        this.c = c;
    }

    public double getCx() {
        return cx;
    }

    public double getCy() {
        return cy;
    }

    public double getRad() {
        return rad;
    }

    public Color getColor() {
        return c;
    }

    public double getRadius() {
        return rad;
    }

    @Override
    public void accept(DrawingVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return
            "circle "+
            cx+" "+
            cy+" "+
            rad+" "+
            ColorDecode.getName(c)
        ;
    }

}