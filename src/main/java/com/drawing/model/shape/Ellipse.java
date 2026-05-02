package com.drawing.model.shape;

import com.drawing.util.ColorDecode;

import java.awt.*;

public class Ellipse implements Drawable {

    private double x, y, rx, ry;
    private Color c;

    public Ellipse(double x, double y, double rx, double ry, Color c) {
        this.x = x;
        this.y = y;
        this.rx = rx;
        this.ry = ry;
        this.c = c;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRx() {
        return rx;
    }

    public double getRy() {
        return ry;
    }

    public Color getColor() {
        return c;
    }

    @Override
    public void accept(DrawingVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return
            "ellipse "+
            x+" "+
            y+" "+
            rx+" "+
            ry+" "+
            ColorDecode.getName(c)
        ;
    }

}