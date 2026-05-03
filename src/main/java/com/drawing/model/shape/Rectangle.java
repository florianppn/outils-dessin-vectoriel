package com.drawing.model.shape;

import com.drawing.util.ColorDecode;

import java.awt.*;

/**
 * Rectangle aligné sur les axes, défini par deux coins opposés et une couleur.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class Rectangle implements Drawable {

    private double x0, y0, x1, y1;
    private Color c;

    /**
     * @param x0 abscisse du premier coin
     * @param y0 ordonnée du premier coin
     * @param x1 abscisse du coin opposé
     * @param y1 ordonnée du coin opposé
     * @param c couleur du contour
     */
    public Rectangle(double x0, double y0, double x1, double y1, Color c) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.c = c;
    }

    /** @return abscisse du premier coin */
    public double getX0() {
        return x0;
    }

    /** @return ordonnée du second coin (selon convention du modèle) */
    public double getY1() {
        return y1;
    }

    /** @return abscisse du second coin */
    public double getX1() {
        return x1;
    }

    /** @return ordonnée du premier coin */
    public double getY0() {
        return y0;
    }

    /** @return largeur (différence d'abscisses) */
    public double getWidth() {
        return x1-x0;
    }

    /** @return couleur du contour */
    public Color getColor() {
        return c;
    }

    /** @return hauteur (différence d'ordonnées) */
    public double getHeight() {
        return y1-y0;
    }

    /** {@inheritDoc} */
    @Override
    public void accept(DrawingVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * @return représentation texte pour la console (coins + nom de couleur)
     */
    @Override
    public String toString() {
        return
            "rectangle "+
            x0+" "+
            y0+" "+
            x1+" "+
            y1+" "+
            ColorDecode.getName(c)
        ;
    }


}