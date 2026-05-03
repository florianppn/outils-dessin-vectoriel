package com.drawing.model.shape;

/**
 * Élément géométrique du modèle, parcourable par un {@link DrawingVisitor} (patron Visitor).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public interface Drawable {

    /**
     * @param visitor visiteur appliquant une opération polymorphe (rendu, export XML, etc.)
     */
    void accept(DrawingVisitor visitor);

}