package com.drawing.controller.command;

import com.drawing.util.ColorDecode;
import com.drawing.model.DrawingModel;

/**
 * Ajoute un cercle au modèle (centre, rayon, couleur).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class CircCommand implements EditorCommand {

    private DrawingModel drawingModel;
    private String[] params;

    public CircCommand(DrawingModel drawingModel, String[] params) {
        this.drawingModel = drawingModel;
        this.params = params;
    }

    /** {@inheritDoc} */
    @Override
    public String execute() {
        drawingModel.createCircle(
            Double.parseDouble(params[0]),
            Double.parseDouble(params[1]),
            Double.parseDouble(params[2]),
            ColorDecode.decode(params[3].toUpperCase())
        );
        return "Le cercle a bien été crée.";
    }

}
