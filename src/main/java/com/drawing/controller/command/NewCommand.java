package com.drawing.controller.command;

import com.drawing.model.DrawingModel;
import com.drawing.controller.validation.Validator;

/**
 * Vide le modèle (nouveau dessin vierge).
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class NewCommand implements EditorCommand {

    private DrawingModel drawingModel;
    private Validator validator;

    public NewCommand(DrawingModel drawingModel, Validator validator) {
        this.drawingModel = drawingModel;
        this.validator = validator;
    }

    /** {@inheritDoc} */
    @Override
    public String execute(String[] params) {
        if (!validator.validate(params)) return "Les paramètres ne peuvent pas être traité.";
        drawingModel.reset();
        return "Nouveau dessin.";
    }

}
