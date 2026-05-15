package com.drawing.model.builder;

import com.drawing.model.shape.Drawable;
import com.drawing.model.shape.*;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Implémentation de {@link DrawingBuilder} : construction par étapes avec {@link ShapeKind}
 * et pile de groupes imbriqués.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class NormalDrawingBuilder implements DrawingBuilder {

    private ShapeKind currentKind = ShapeKind.NONE;
    /** Pile de listes pour les groupes imbriqués (racine en bas de pile). */
    private final Deque<List<Drawable>> groupStack = new ArrayDeque<>();
    private double x0, y0, x1, y1;
    private double cx, cy, radius;
    private double centerX, centerY, rx, ry;
    private Color color;

    public NormalDrawingBuilder() {
        groupStack.push(new ArrayList<>());
    }

    /** {@inheritDoc} */
    @Override
    public void reset() {
        currentKind = ShapeKind.NONE;
        groupStack.clear();
        groupStack.push(new ArrayList<>());
    }

    private void ensureNoShapeInProgress() {
        if (currentKind != ShapeKind.NONE) {
            throw new IllegalStateException("Forme en cours de construction : " + currentKind);
        }
    }

    private void require(ShapeKind... allowed) {
        for (ShapeKind kind : allowed) {
            if (kind == currentKind) return;
        }
        throw new IllegalStateException("Méthode non autorisée pour " + currentKind + " (attendu : " + Arrays.toString(allowed) + ")");
    }

    private void addDrawable(Drawable d) {
        groupStack.peek().add(d);
    }

    /** {@inheritDoc} */
    @Override
    public void beginRectangle() {
        ensureNoShapeInProgress();
        currentKind = ShapeKind.RECT;
    }

    /** {@inheritDoc} */
    @Override
    public void beginCircle() {
        ensureNoShapeInProgress();
        currentKind = ShapeKind.CIRCLE;
    }

    /** {@inheritDoc} */
    @Override
    public void beginLine() {
        ensureNoShapeInProgress();
        currentKind = ShapeKind.LINE;
    }

    /** {@inheritDoc} */
    @Override
    public void beginEllipse() {
        ensureNoShapeInProgress();
        currentKind = ShapeKind.ELLIPSE;
    }

    /** {@inheritDoc} */
    @Override
    public void setX0(double x0) {
        require(ShapeKind.RECT, ShapeKind.LINE);
        this.x0 = x0;
    }

    /** {@inheritDoc} */
    @Override
    public void setY0(double y0) {
        require(ShapeKind.RECT, ShapeKind.LINE);
        this.y0 = y0;
    }

    /** {@inheritDoc} */
    @Override
    public void setX1(double x1) {
        require(ShapeKind.RECT, ShapeKind.LINE);
        this.x1 = x1;
    }

    /** {@inheritDoc} */
    @Override
    public void setY1(double y1) {
        require(ShapeKind.RECT, ShapeKind.LINE);
        this.y1 = y1;
    }

    /** {@inheritDoc} */
    @Override
    public void setCx(double cx) {
        require(ShapeKind.CIRCLE);
        this.cx = cx;
    }

    /** {@inheritDoc} */
    @Override
    public void setCy(double cy) {
        require(ShapeKind.CIRCLE);
        this.cy = cy;
    }

    /** {@inheritDoc} */
    @Override
    public void setRadius(double radius) {
        require(ShapeKind.CIRCLE);
        this.radius = radius;
    }

    /** {@inheritDoc} */
    @Override
    public void setCenterX(double x) {
        require(ShapeKind.ELLIPSE);
        this.centerX = x;
    }

    /** {@inheritDoc} */
    @Override
    public void setCenterY(double y) {
        require(ShapeKind.ELLIPSE);
        this.centerY = y;
    }

    /** {@inheritDoc} */
    @Override
    public void setRx(double rx) {
        require(ShapeKind.ELLIPSE);
        this.rx = rx;
    }

    /** {@inheritDoc} */
    @Override
    public void setRy(double ry) {
        require(ShapeKind.ELLIPSE);
        this.ry = ry;
    }

    /** {@inheritDoc} */
    @Override
    public void setColor(Color color) {
        require(ShapeKind.RECT, ShapeKind.CIRCLE, ShapeKind.LINE, ShapeKind.ELLIPSE);
        this.color = color;
    }

    /** {@inheritDoc} */
    @Override
    public void build() {
        Drawable drawable = switch (currentKind) {
            case RECT -> new Rectangle(x0, y0, x1, y1, color);
            case CIRCLE -> new Circle(cx, cy, radius, color);
            case LINE -> new Line(x0, y0, x1, y1, color);
            case ELLIPSE -> new Ellipse(centerX, centerY, rx, ry, color);
            case NONE -> throw new IllegalStateException("Aucune forme à construire");
        };
        addDrawable(drawable);
        currentKind = ShapeKind.NONE;
    }

    /** {@inheritDoc} */
    @Override
    public void setGroup(List<Drawable> drawables, String label) {
        ensureNoShapeInProgress();
        addDrawable(new Group(drawables, label));
    }

    /** {@inheritDoc} */
    @Override
    public void startGroup() {
        ensureNoShapeInProgress();
        groupStack.push(new ArrayList<>());
    }

    /** {@inheritDoc} */
    @Override
    public void endGroup(String label) {
        ensureNoShapeInProgress();
        if (groupStack.size() > 1) {
            List<Drawable> children = groupStack.pop();
            addDrawable(new Group(children, label));
        }
    }

    /** {@inheritDoc} */
    @Override
    public List<Drawable> getResult() {
        if (currentKind != ShapeKind.NONE) {
            throw new IllegalStateException("Forme non finalisée : appelez build() avant getResult()");
        }
        if (groupStack.size() > 1) {
            throw new IllegalStateException("Groupes non fermés : endGroup() manquant");
        }
        return new ArrayList<>(groupStack.peek());
    }

}
