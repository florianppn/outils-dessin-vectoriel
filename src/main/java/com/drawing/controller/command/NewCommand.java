package com.drawing.controller.command;

import com.drawing.model.DrawingModel;

/**
 * Vide le modèle (nouveau dessin vierge).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class NewCommand implements EditorCommand {

    private DrawingModel drawingModel;

    public NewCommand(DrawingModel drawingModel) {
        this.drawingModel = drawingModel;
    }

    /** {@inheritDoc} */
    @Override
    public String execute() {
        drawingModel.reset();
        return "Nouveau dessin.";
    }

}
