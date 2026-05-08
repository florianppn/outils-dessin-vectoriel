package com.drawing.model.builder;

import com.drawing.model.shape.Drawable;
import com.drawing.model.shape.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation de DrawingBuilder qui construit une liste de Drawable.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class NormalDrawingBuilder implements DrawingBuilder {

    /**
     * Liste des éléments dessinables construits. Les éléments sont ajoutés à la liste ou au groupe en cours.
     */
    private List<Drawable> drawables = new ArrayList<>();

    /** {@inheritDoc} */
    @Override
    public void reset() {
        drawables.clear();
    }

    private void addDrawable(Drawable d) {
        drawables.add(d);
    }

    /** {@inheritDoc} */
    @Override
    public void setRectangle(double x0, double y0, double x1, double y1, Color c) {
        addDrawable(new Rectangle(x0, y0, x1, y1, c));
    }

    /** {@inheritDoc} */
    @Override
    public void setCircle(double cx, double cy, double rad, Color c) {
        addDrawable(new Circle(cx, cy, rad, c));
    }

    /** {@inheritDoc} */
    @Override
    public void setLine(double x0, double y0, double x1, double y1, Color c) {
        addDrawable(new Line(x0, y0, x1, y1, c));
    }

    /** {@inheritDoc} */
    @Override
    public void setEllipse(double x, double y, double rx, double ry, Color c) {
        addDrawable(new Ellipse(x, y, rx, ry, c));
    }

    /** {@inheritDoc} */
    @Override
    public void setGroup(List<Drawable> drawables, String label) {
        addDrawable(new Group(drawables, label));
    }

    /** {@inheritDoc} */
    @Override
    public List<Drawable> getResult() {
        return new ArrayList<>(drawables);
    }

}
