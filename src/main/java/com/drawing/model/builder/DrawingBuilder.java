package com.drawing.model.builder;

import com.drawing.model.shape.Drawable;

import java.awt.Color;
import java.util.List;

/**
 * Interface pour construire un dessin composé de différentes formes (rectangle, cercle, ligne, ellipse) et de groupes.
 * La construction d'une forme se fait par étapes : {@code begin*} → setters → {@link #build()}.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public interface DrawingBuilder {

    /** Réinitialise le builder pour construire un nouveau dessin à partir de zéro. */
    void reset();

    /** Débute la construction d'un rectangle. */
    void beginRectangle();

    /** Débute la construction d'un cercle. */
    void beginCircle();

    /** Débute la construction d'une ligne. */
    void beginLine();

    /** Débute la construction d'une ellipse. */
    void beginEllipse();

    /**
     * @param x0 abscisse du premier point (rectangle ou ligne)
     */
    void setX0(double x0);

    /**
     * @param y0 ordonnée du premier point (rectangle ou ligne)
     */
    void setY0(double y0);

    /**
     * @param x1 abscisse du second point (rectangle ou ligne)
     */
    void setX1(double x1);

    /**
     * @param y1 ordonnée du second point (rectangle ou ligne)
     */
    void setY1(double y1);

    /**
     * @param cx abscisse du centre (cercle)
     */
    void setCx(double cx);

    /**
     * @param cy ordonnée du centre (cercle)
     */
    void setCy(double cy);

    /**
     * @param radius rayon (cercle)
     */
    void setRadius(double radius);

    /**
     * @param x abscisse du centre (ellipse)
     */
    void setCenterX(double x);

    /**
     * @param y ordonnée du centre (ellipse)
     */
    void setCenterY(double y);

    /**
     * @param rx demi-grand axe horizontal (ellipse)
     */
    void setRx(double rx);

    /**
     * @param ry demi-grand axe vertical (ellipse)
     */
    void setRy(double ry);

    /**
     * @param color couleur du contour de la forme en cours
     */
    void setColor(Color color);

    /** Finalise la forme en cours et l'ajoute au dessin (ou au groupe courant). */
    void build();

    /**
     * Définit un groupe de formes déjà construites avec une étiquette.
     * @param drawables liste des formes du groupe
     * @param label étiquette du groupe
     */
    void setGroup(List<Drawable> drawables, String label);

    /** Débute la construction d'un nouveau groupe. Les formes suivantes seront ajoutées à ce groupe. */
    void startGroup();

    /**
     * Termine la construction du groupe courant et l'ajoute au niveau parent.
     * @param label étiquette du groupe
     */
    void endGroup(String label);

    /** @return liste des formes dessinables construites par le builder */
    List<Drawable> getResult();

}
