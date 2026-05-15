package com.drawing.controller.command;

import com.drawing.util.ColorDecode;
import com.drawing.model.DrawingModel;
import com.drawing.controller.validation.Validator;

/**
 * Ajoute un rectangle au modèle à partir de coordonnées et d'une couleur nommée.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class RectCommand implements EditorCommand {

    private DrawingModel drawingModel;
    private Validator validator;

    public RectCommand(DrawingModel drawingModel, Validator validator) {
        this.drawingModel = drawingModel;
        this.validator = validator;
    }

    /** {@inheritDoc} */
    @Override
    public String execute(String[] params) {
        if (!validator.validate(params)) return "Les paramètres ne peuvent pas être traité.";
        drawingModel.createRectangle(
            Double.parseDouble(params[0]),
            Double.parseDouble(params[1]),
            Double.parseDouble(params[2]),
            Double.parseDouble(params[3]),
            ColorDecode.decode(params[4].toUpperCase())
        );
        return "Le rectangle a bien été crée.";
    }

}
