package com.drawing.controller.command;

import com.drawing.util.ColorDecode;
import com.drawing.model.DrawingModel;
import com.drawing.controller.validation.Validator;

/**
 * Ajoute un cercle au modèle (centre, rayon, couleur).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class CircCommand implements EditorCommand {

    private DrawingModel drawingModel;
    private Validator validator;

    public CircCommand(DrawingModel drawingModel, Validator validator) {
        this.drawingModel = drawingModel;
        this.validator = validator;
    }

    /** {@inheritDoc} */
    @Override
    public String execute(String[] params) {
        if (!validator.validate(params)) return "Les paramètres ne peuvent pas être traité.";
        drawingModel.createCircle(
            Double.parseDouble(params[0]),
            Double.parseDouble(params[1]),
            Double.parseDouble(params[2]),
            ColorDecode.decode(params[3].toUpperCase())
        );
        return "Le cercle a bien été crée.";
    }

}
