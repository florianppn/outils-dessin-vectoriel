package com.drawing.model.builder;

import com.drawing.model.shape.Drawable;
import com.drawing.model.shape.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class NormalDrawingBuilder implements DrawingBuilder {

    List<Drawable> drawables = new ArrayList<>();

    @Override
    public void reset() {
        drawables.clear();
    }

    @Override
    public void setRectangle(double x0, double y0, double x1, double y1, Color c) {
        drawables.add(new Rectangle(x0, y0, x1, y1, c));
    }

    @Override
    public void setCircle(double cx, double cy, double rad, Color c) {
        drawables.add(new Circle(cx, cy, rad, c));
    }

    @Override
    public void setLine(double x0, double y0, double x1, double y1, Color c) {
        drawables.add(new Line(x0, y0, x1, y1, c));
    }

    @Override
    public void setEllipse(double x, double y, double rx, double ry, Color c) {
        drawables.add(new Ellipse(x, y, rx, ry, c));
    }

    @Override
    public void setGroup(List<Drawable> drawables, String label) {
        Group g = new Group(label);
        g.setDrawables(drawables);
        drawables.add(g);
    }

    public List<Drawable> getResult() {
        return drawables;
    }

}
