package com.drawing.controller.command;

import com.drawing.model.DrawingModel;

/**
 * Retourne la liste numérotée des formes du modèle (texte pour la console).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class ListCommand implements EditorCommand {

    private DrawingModel drawingModel;

    public ListCommand(DrawingModel drawingModel) {
        this.drawingModel = drawingModel;
    }

    /** {@inheritDoc} */
    @Override
    public String execute() {
        return drawingModel.toString();
    }

}
