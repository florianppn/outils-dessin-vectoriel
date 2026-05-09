package com.drawing.controller.command;

import com.drawing.util.ColorDecode;
import com.drawing.model.DrawingModel;

/**
 * Ajoute une ligne (deux points et une couleur) au modèle.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class LineCommand implements EditorCommand {

    private DrawingModel drawingModel;
    private String[] params;

    public LineCommand(DrawingModel drawingModel, String[] params) {
        this.drawingModel = drawingModel;
        this.params = params;
    }

    /** {@inheritDoc} */
    @Override
    public String execute() {
        drawingModel.createLine(
            Double.parseDouble(params[0]),
            Double.parseDouble(params[1]),
            Double.parseDouble(params[2]),
            Double.parseDouble(params[3]),
            ColorDecode.decode(params[4].toUpperCase())
        );
        return "La ligne a bien été crée.";
    }

}
