package com.drawing.model.shape;

/**
 * Visiteur pour la hiérarchie de formes : une opération par type concret.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public interface DrawingVisitor {

    /** @param line segment à traiter */
    void visit(Line line);

    /** @param rectangle rectangle à traiter */
    void visit(Rectangle rectangle);

    /** @param circle cercle à traiter */
    void visit(Circle circle);

    /** @param ellipse ellipse à traiter */
    void visit(Ellipse ellipse);

    /** @param group groupe (composite) à traiter ; implémentations typiques parcourent les enfants */
    void visit(Group group);

}