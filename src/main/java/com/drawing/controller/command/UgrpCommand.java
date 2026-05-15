package com.drawing.controller.command;

import com.drawing.model.DrawingModel;
import com.drawing.controller.validation.Validator;

/**
 * Dissout un groupe : replace le groupe par ses enfants dans l'historique si l'élément ciblé est un groupe.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class UgrpCommand implements EditorCommand {

    private DrawingModel drawingModel;
    private Validator validator;

    public UgrpCommand(DrawingModel drawingModel, Validator validator) {
        this.drawingModel = drawingModel;
        this.validator = validator;
    }

    /** {@inheritDoc} */
    @Override
    public String execute(String[] params) {
        if (!validator.validate(params)) return "Les paramètres ne peuvent pas être traité.";
        boolean res = drawingModel.deleteGroup(
            Integer.parseInt(params[0])-1
        );
        return res ? "Le groupe a bien été supprimé." : "Ce n'est pas un groupe.";
    }

}
