package com.drawing.controller.command;

import com.drawing.util.ColorDecode;
import com.drawing.model.DrawingModel;

/**
 * Ajoute une ellipse au modèle (centre, demi-axes, couleur).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class ElliCommand implements EditorCommand {

    private DrawingModel drawingModel;
    private String[] params;

    public ElliCommand(DrawingModel drawingModel, String[] params) {
        this.drawingModel = drawingModel;
        this.params = params;
    }

    /** {@inheritDoc} */
    @Override
    public String execute() {
        drawingModel.createEllipse(
            Double.parseDouble(params[0]),
            Double.parseDouble(params[1]),
            Double.parseDouble(params[2]),
            Double.parseDouble(params[3]),
            ColorDecode.decode(params[4].toUpperCase())
        );
        return "L'Ellipse a bien été crée.";
    }

}
