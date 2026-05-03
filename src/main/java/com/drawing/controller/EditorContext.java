package com.drawing.controller;

import com.drawing.model.DrawingModel;
import com.drawing.view.GraphicViewer;

/**
 * Regroupe les dépendances partagées par les commandes : modèle de dessin et fenêtre graphique.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class EditorContext {

    private DrawingModel drawingModel;
    private GraphicViewer graphicViewer;

    /**
     * @param drawingModel modèle contenant l'historique des formes
     * @param graphicViewer fenêtre d'affichage du canvas
     */
    public EditorContext(DrawingModel drawingModel, GraphicViewer graphicViewer) {
        this.drawingModel = drawingModel;
        this.graphicViewer = graphicViewer;
    }

    /** @return le modèle de dessin */
    public DrawingModel getDrawingModel() {
        return drawingModel;
    }

    /** @return la vue graphique Swing */
    public GraphicViewer getGraphicViewer() {
        return graphicViewer;
    }

}
