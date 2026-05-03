package com.drawing.model.shape;

import com.drawing.util.ColorDecode;

import java.awt.*;

/**
 * Cercle (centre, rayon, couleur).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class Circle implements Drawable {

    private double cx, cy, rad;
    private Color c;

    /**
     * @param cx abscisse du centre
     * @param cy ordonnée du centre
     * @param rad rayon
     * @param c couleur du contour
     */
    public Circle(double cx, double cy, double rad, Color c) {
        this.cx = cx;
        this.cy = cy;
        this.rad = rad;
        this.c = c;
    }

    /** @return abscisse du centre */
    public double getCx() {
        return cx;
    }

    /** @return ordonnée du centre */
    public double getCy() {
        return cy;
    }

    /** @return rayon */
    public double getRad() {
        return rad;
    }

    /** @return couleur du contour */
    public Color getColor() {
        return c;
    }

    /** @return rayon (alias de {@link #getRad()}) */
    public double getRadius() {
        return rad;
    }

    /** {@inheritDoc} */
    @Override
    public void accept(DrawingVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * @return représentation texte pour la console (centre, rayon, couleur)
     */
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