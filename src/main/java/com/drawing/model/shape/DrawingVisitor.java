package com.drawing.model.shape;

/**
 * Visiteur pour la hiérarchie de formes : une opération par type concret.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public interface DrawingVisitor {

    /** @param line segment à traiter */
    void visitLine(Line line);

    /** @param rectangle rectangle à traiter */
    void visitRect(Rectangle rectangle);

    /** @param circle cercle à traiter */
    void visitCirc(Circle circle);

    /** @param ellipse ellipse à traiter */
    void visitElli(Ellipse ellipse);

    /** @param group groupe (composite) à traiter */
    void visitGroup(Group group);

}