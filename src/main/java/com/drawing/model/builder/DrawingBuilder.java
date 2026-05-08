package com.drawing.model.builder;

import com.drawing.model.shape.Drawable;

import java.awt.Color;
import java.util.List;

/**
 * Interface pour construire un dessin composé de différentes formes (rectangle, cercle, ligne, ellipse) et de groupes de formes.
 * Le builder permet de définir les propriétés de chaque forme (position, taille, couleur) et de regrouper des formes sous une étiquette commune.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public interface DrawingBuilder {

    /**
     * Réinitialise le builder pour construire un nouveau dessin à partir de zéro.
     */
    void reset();

    /**
     * Définit un rectangle par ses coins (x0, y0) et (x1, y1) et sa couleur.
     * @param x0 abscisse du premier coin
     * @param y0 ordonnée du premier coin
     * @param x1 abscisse du coin opposé
     * @param y1 ordonnée du coin opposé
     * @param c couleur du contour
     */
    void setRectangle(double x0, double y0, double x1, double y1, Color c);

    /**
     * Définit un cercle par son centre (cx, cy), son rayon et sa couleur.
     * @param cx abscisse du centre
     * @param cy ordonnée du centre
     * @param rad rayon
     * @param c couleur du contour
     */
    void setCircle(double cx, double cy, double rad, Color c);

    /**
     * Définit une ligne par ses extrémités (x0, y0) et (x1, y1) et sa couleur.
     * @param x0 abscisse du premier point
     * @param y0 ordonnée du premier point
     * @param x1 abscisse du second point
     * @param y1 ordonnée du second point
     * @param c couleur
     */
    void setLine(double x0, double y0, double x1, double y1, Color c);

    /**
     * Définit une ellipse par son centre (x, y), ses rayons rx et ry et sa couleur.
     * @param x abscisse du centre
     * @param y ordonnée du centre
     * @param rx demi-grand axe horizontal
     * @param ry demi-grand axe vertical
     * @param c couleur du contour
     */
    void setEllipse(double x, double y, double rx, double ry, Color c);

    /**
     * Définit un groupe de formes avec une étiquette.
     * @param drawables liste des formes du groupe
     * @param label étiquette du groupe
     */
    void setGroup(List<Drawable> drawables, String label);

    /**
     * Débute la construction d'un nouveau groupe. Les formes suivantes seront ajoutées à ce groupe.
     */
    void startGroup();

    /**
     * Termine la construction du groupe courant et l'ajoute au niveau parent avec une étiquette.
     * @param label étiquette du groupe
     */
    void endGroup(String label);

    /**
     * @return Retourne la liste des formes dessinables construites par le builder.
     */
    List<Drawable> getResult();

}
