package com.drawing.model.builder;

import com.drawing.model.shape.Drawable;

import java.awt.Color;
import java.util.List;

public interface DrawingBuilder {

    void reset();

    void setRectangle(double x0, double y0, double x1, double y1, Color c);

    void setCircle(double cx, double cy, double rad, Color c);

    void setLine(double x0, double y0, double x1, double y1, Color c);

    void setEllipse(double x, double y, double rx, double ry, Color c);

    void beginGroup(String label);

    void endGroup();

    List<Drawable> getResult();

}
