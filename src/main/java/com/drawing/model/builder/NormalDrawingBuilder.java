package com.drawing.model.builder;

import com.drawing.model.shape.Drawable;
import com.drawing.model.shape.*;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
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
    private final List<Drawable> drawables = new ArrayList<>();
    /**
     * Pile pour gérer les groupes imbriqués. Chaque fois que beginGroup() est appelé, un nouveau groupe est poussé sur la pile.
     */
    private final Deque<Group> groupStack = new ArrayDeque<>();

    /** {@inheritDoc} */
    @Override
    public void reset() {
        drawables.clear();
        groupStack.clear();
    }

    private void addDrawable(Drawable d) {
        if (groupStack.isEmpty()) {
            drawables.add(d);
        } else {
            groupStack.peek().add(d);
        }
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
    public void beginGroup(String label) {
        groupStack.push(new Group(label));
    }

    /** {@inheritDoc} */
    @Override
    public void endGroup() {
        if (groupStack.isEmpty()) {
            throw new IllegalStateException("endGroup() appelé sans beginGroup()");
        }
        Group finished = groupStack.pop();
        addDrawable(finished);
    }

    /** {@inheritDoc} */
    @Override
    public List<Drawable> getResult() {
        if (!groupStack.isEmpty()) {
            throw new IllegalStateException("Groupes non fermés: endGroup() manquant");
        }
        return new ArrayList<>(drawables);
    }

}
