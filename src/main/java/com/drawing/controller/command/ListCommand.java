package com.drawing.controller.command;

import com.drawing.model.DrawingModel;
import com.drawing.controller.validation.Validator;

/**
 * Retourne la liste numérotée des formes du modèle (texte pour la console).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class ListCommand implements EditorCommand {

    private DrawingModel drawingModel;
    private Validator validator;

    public ListCommand(DrawingModel drawingModel, Validator validator) {
        this.drawingModel = drawingModel;
        this.validator = validator;
    }

    /** {@inheritDoc} */
    @Override
    public String execute(String[] params) {
        if (!validator.validate(params)) return "Les paramètres ne peuvent pas être traité.";
        return drawingModel.toString();
    }

}
