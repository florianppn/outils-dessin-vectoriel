package com.drawing.model.shape;

import com.drawing.util.ColorDecode;

import java.awt.*;

/**
 * Segment défini par deux points et une couleur.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class Line implements Drawable {

    private double x0, y0, x1, y1;
    private Color c;

    /**
     * @param x0 abscisse du premier point
     * @param y0 ordonnée du premier point
     * @param x1 abscisse du second point
     * @param y1 ordonnée du second point
     * @param c couleur
     */
    public Line(double x0, double y0, double x1, double y1, Color c) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.c = c;
    }

    /** @return abscisse du premier point */
    public double getX0() {
        return x0;
    }

    /** @return ordonnée du premier point */
    public double getY0() {
        return y0;
    }

    /** @return abscisse du second point */
    public double getX1() {
        return x1;
    }

    /** @return ordonnée du second point */
    public double getY1() {
        return y1;
    }

    /** @return couleur du segment */
    public Color getColor() {
        return c;
    }

    /** @return longueur euclidienne du segment */
    public double getLength() {
        double dx = x1 - x0;
        double dy = y1 - y0;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /** {@inheritDoc} */
    @Override
    public void accept(DrawingVisitor visitor) {
        visitor.visitLine(this);
    }

    /**
     * @return représentation texte pour la console (coordonnées + nom de couleur)
     */
    @Override
    public String toString() {
        return
            "line "+
            x0+" "+
            y0+" "+
            x1+" "+
            y1+" "+
            ColorDecode.getName(c)
        ;
    }

}