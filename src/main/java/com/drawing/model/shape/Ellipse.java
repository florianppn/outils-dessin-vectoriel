package com.drawing.model.shape;

import com.drawing.util.ColorDecode;

import java.awt.*;

/**
 * Ellipse (centre, demi-axes rx/ry, couleur).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class Ellipse implements Drawable {

    private double x, y, rx, ry;
    private Color c;

    /**
     * @param x abscisse du centre
     * @param y ordonnée du centre
     * @param rx demi-grand axe horizontal
     * @param ry demi-grand axe vertical
     * @param c couleur du contour
     */
    public Ellipse(double x, double y, double rx, double ry, Color c) {
        this.x = x;
        this.y = y;
        this.rx = rx;
        this.ry = ry;
        this.c = c;
    }

    /** @return abscisse du centre */
    public double getX() {
        return x;
    }

    /** @return ordonnée du centre */
    public double getY() {
        return y;
    }

    /** @return demi-grand axe horizontal */
    public double getRx() {
        return rx;
    }

    /** @return demi-grand axe vertical */
    public double getRy() {
        return ry;
    }

    /** @return couleur du contour */
    public Color getColor() {
        return c;
    }

    /** {@inheritDoc} */
    @Override
    public void accept(DrawingVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * @return représentation texte pour la console (centre, demi-axes, couleur)
     */
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